package server;

import java.util.ArrayList;

import global.card.Card;
import global.relic.Relic;
import global.EntityListenerMessage;
import global.Enemy;
import global.Entity;
import global.Message;
import global.NotifyPayload;
import global.Player;
import global.PlayerClass;
import global.Reward;
import global.RewardChoice;
import server.managers.CardManager;
import server.managers.EnemyManager;
import server.managers.RelicManager;
import server.managers.RewardManager;

public class ServerDataHandler implements EntityListener {

	public static int ENEMY_ACTION_DELAY = 2;
	
	public static int SDH_PRI = 40;
	
	public ArrayList<Player> players;
	public ArrayList<Enemy> enemies;

	private RewardManager rewardManager;
	private RelicManager relicManager;
	private EnemyManager enemyManager;
	private CardManager cardManager;

	private boolean playersCanPlayCard;
	private ConnectionHandler connHandler;
	
	
	private int fightTracker;

	public ServerDataHandler() {
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		connHandler = new ConnectionHandler(this);
		cardManager = new CardManager(this);
		enemyManager = new EnemyManager(this);
		relicManager = new RelicManager();
		rewardManager = new RewardManager(cardManager, relicManager);
		new Thread(connHandler).start();
	}

	public Player createPlayer(S2CCommunicator clientHandler, Object data) {
		boolean nameTaken = false;
		for(Player p: players) {
			if(p.getName().equals(((Object[]) data)[0])) {
				nameTaken = true;
			}
		}
		if(!nameTaken) {
			Player player = new Player();
			player.setName((String) ((Object[]) data)[0]);
			player.playerClass = (PlayerClass) (((Object[]) data)[1]);
			player.setClientHandler(clientHandler);
			players.add(player);
			sendMessageToAll(new Message("players", players));
			return player;
		}
		return null;
	}

	public void removePlayer(Player player) {
		players.remove(player);
		sendMessageToAll(new Message("players", players));
	}

	public void readyPlayerToEndTurn(Player player) {
		player.setReadyToEndTurn(!player.getReadyToEndTurn());
		boolean notAllReady = false;
		for(Player p: players) {
			notAllReady = notAllReady || !p.getReadyToEndTurn();
		}
		sendMessageToAll(new Message("players", players));
		if(!notAllReady) {
			for(int i = 0; i < players.size(); i++) {
				players.get(i).setReadyToEndTurn(false);
			}
			doTurnCycle();
		}
	}

	public void readyPlayerToStartGame(Player player) {
		player.setReadyToStartGame(!player.getReadyToStartGame());
		boolean notAllReady = false;
		for(Player p: players) {
			notAllReady = notAllReady || !p.getReadyToStartGame();
		}
		if(!notAllReady) {
			connHandler.stopAccepting();
			for(int i = 0; i < players.size(); i++) {
				players.get(i).setReadyToStartGame(false);
			}
			//start game
			startGame();
			startFight(0);
		}else {
			sendMessageToAll(new Message("players", players));
		}
	}

	public void readyPlayerToStartFight(Player player) {
		player.setReadyToStartFight(!player.getReadyToStartFight());
		boolean notAllReady = false;
		for(Player p: players) {
			notAllReady = notAllReady || !p.getReadyToStartFight();
		}
		if(!notAllReady) {
			for(int i = 0; i < players.size(); i++) {
				players.get(i).setReadyToStartFight(false);
			}
			for(Player p: players) {
				p.setReadyToEndTurn(false);
			}
			fightTracker++;
			startFight(fightTracker);
		}
	}
	
	public void startGame() {
		for(Player p: players) {
			p.setDeck(cardManager.getStartingDeck(p.playerClass));
			p.addMaxHealth(p.playerClass.getMaxHealth());
			p.addRelic(p.playerClass.getStartingRelic().onAdd(p, this));
			p.setMaxEnergy(3);
		}
	}
	
	public void startFight(int fightNum) {
		playersCanPlayCard = true;
		enemies = enemyManager.getEnemiesForFight(fightNum);
		for(Enemy e: enemies) {
			e.healToFull();
			e.decideAction();
		}
		for(Player p: players) {
			p.removeHandAndDiscard();
			p.shuffleCardsFromDeck();
			p.drawCards(5);
			p.fightStartSubs();
			p.resetEnergy();
		}
		sendMessageToAll(new Message("startFight", null));
		sendMessageToAll(new Message("players", players));
		sendMessageToAll(new Message("enemies", enemies));
	}

	public Message playCard(Object obj, Player play) {
		if(obj instanceof Integer[] && playersCanPlayCard) {
			Integer[] cardData = (Integer[]) obj;
			Message result = play.playCard(cardData[0], cardData[1], cardData[2]);
			sendMessageToAll(new Message("players", players));
			sendMessageToAll(new Message("enemies", enemies));
			return result;
		}
		return new Message("pcfail", null);
	}

	public void sendMessageToAll(Message message) {
		for(Player p: players) {
			p.sendMessage(message);
		}
	}

	/**
	 * The main game logic loop. Starts after the players turn ends.
	 */
	public void doTurnCycle() {
		for(Player p: players) {
			p.postTurn();
		}
		sendMessageToAll(new Message("status", "Enemy Turn"));
		playersCanPlayCard = false;
		for(Enemy e: enemies) {
			e.removeAllBlock();
		}
		for(Enemy e: enemies) {
			e.preTurn();
		}
		sendMessageToAll(new Message("enemies", enemies));
		sleep();
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.takeAction();
			e.decideAction();
			sendMessageToAll(new Message("players", players));
			sendMessageToAll(new Message("enemies", enemies));
			sleep();
		}
		for(Enemy e: enemies) {
			e.postTurn();
		}
		playersCanPlayCard = true;
		for(Player p: players) {
			p.preTurn();
		}
		sendMessageToAll(new Message("players", players));
		sendMessageToAll(new Message("enemies", enemies));
		sendMessageToAll(new Message("status", "Player Turn"));
	}
	
	private void sleep() {
		try {
			Thread.sleep(ENEMY_ACTION_DELAY * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void handleRewardChoice(Object data, Player player) {
		RewardChoice rewardChoice = (RewardChoice) data;
		Reward reward = player.lastReward;
		addCard(rewardChoice.choice1Index, reward.cardReward1, player);
		addCard(rewardChoice.choice2Index, reward.cardReward2, player);
		addCard(rewardChoice.choice3Index, reward.cardReward3, player);
		addCard(rewardChoice.choice4Index, reward.cardReward4, player);
		addCard(rewardChoice.choice5Index, reward.cardReward5, player);
		addRelic(rewardChoice.choice6Index, reward.relicReward1, player);
		addRelic(rewardChoice.choice7Index, reward.relicReward2, player);

	}
	
	public void addRelic(int selection, ArrayList<Relic> relics, Player player) {
		if(selection >= 0 && selection <= 1) {
			Relic rel = relics.remove(selection);
			player.addRelic(rel);
			rel.onAdd(player, this);
			for(Relic r: relics) {
				relicManager.returnRelic(r);
			}
		}
	}
	
	public void addCard(int selection, ArrayList<Card> cards, Player player) {
		if(selection >= 0 && selection <= 2) {
			player.addCardToDeck(cards.get(selection));
		}
	}

	public boolean playersCanPlayCard() {
		return playersCanPlayCard;
	}

	@Override
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
		if(entity instanceof Enemy) {
			if(message.is(EntityListenerMessage.DIED_ATTACK_DAMAGE) || message.is(EntityListenerMessage.DIED_TRUE_DAMAGE) || message.is(EntityListenerMessage.DIED_DAMAGE)) {
				boolean allDead = true;
				for(Enemy e: enemies) {
					if(!e.isDead()) {
						allDead = false;
					}
				}
				if(allDead) {
					for(Player p: players) {
						p.fightEndSubs();
						p.removeAllSE();
						Reward r = rewardManager.getReward(p.playerClass);
						p.lastReward = r;
						p.sendMessage(new Message("choosereward", r));
					}
				}
				enemies.remove(entity);
			}
		}
	}

	@Override
	public int compareTo(EntityListener el) {
		return SDH_PRI - el.getPriority();
	}

	@Override
	public int getPriority() {
		return SDH_PRI;
	}

}
