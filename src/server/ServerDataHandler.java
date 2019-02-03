package server;

import java.util.ArrayList;

import global.Card;
import global.Enemy;
import global.Entity;
import global.Message;
import global.Player;
import global.PlayerClass;
import global.Reward;
import global.RewardChoice;
import global.relics.AvengingEye;
import server.managers.CardManager;
import server.managers.EnemyManager;
import server.managers.RelicManager;
import server.managers.RewardManager;

public class ServerDataHandler implements EntityListener {

	public static int ENEMY_ACTION_DELAY = 2;
	public ArrayList<Player> players;
	public ArrayList<Enemy> enemies;

	private RewardManager rewardManager;
	private RelicManager relicManager;
	private EnemyManager enemyManager;

	private boolean playersCanPlayCard;
	private ConnectionHandler connHandler;
	
	private CardManager cardManager;
	
	private int fightTracker;

	public ServerDataHandler() {
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		connHandler = new ConnectionHandler(this);
		cardManager = new CardManager(this);
		enemyManager = new EnemyManager(this);
		relicManager = new RelicManager(this);
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
		if(!notAllReady) {
			for(int i = 0; i < players.size(); i++) {
				players.get(i).setReadyToEndTurn(false);
			}
			doTurnCycle();
		}
		sendMessageToAll(new Message("players", players));
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
			if(p.playerClass.equals(PlayerClass.RETRIBUTOR)) {
				p.addMaxHealth(60);
				p.addRelic(new AvengingEye(this).onAdd(p));
			}else if(p.playerClass.equals(PlayerClass.REVENANT)) {
				p.addMaxHealth(80);
			}else if(p.playerClass.equals(PlayerClass.RESIPISCENT)) {
				p.addMaxHealth(50);
			}
			p.setMaxEnergy(3);
		}
	}
	
	public void startFight(int fightNum) {
		playersCanPlayCard = true;
		enemies = enemyManager.getEnemiesForFight(fightNum);
		for(Enemy e: enemies) {
			e.healToFull();
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
		sendMessageToAll(new Message("enemies", getDisplayEnemies()));
	}
	
	public ArrayList<Enemy> getDisplayEnemies(){
		ArrayList<Enemy> disEns = new ArrayList<Enemy>();
		for(Enemy e: enemies) {
			disEns.add(e.copyForDisplay());
		}
		return disEns;
	}

	public Message playCard(Object obj, Player play) {
		if(obj instanceof Integer[] && playersCanPlayCard) {
			Integer[] cardData = (Integer[]) obj;
			Message result = play.playCard(cardData[0], cardData[1]);
			sendMessageToAll(new Message("players", players));
			sendMessageToAll(new Message("enemies", getDisplayEnemies()));
			return result;
		}
		return new Message("pcfail", null);
	}

	public void sendMessageToAll(Message message) {
		for(Player p: players) {
			p.sendMessage(message);
		}
	}

	public void doTurnCycle() {
		for(Player p: players) {
			p.postTurn();
		}
		playersCanPlayCard = false;
		for(Enemy e: enemies) {
			e.removeAllBlock();
		}
		for(Enemy e: enemies) {
			e.preTurn();
		}
		for(Enemy e: enemies) {
			e.takeAction().doAction();
			sendMessageToAll(new Message("players", players));
			sendMessageToAll(new Message("enemies", getDisplayEnemies()));
			try {
				Thread.sleep(ENEMY_ACTION_DELAY * 1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		for(Enemy e: enemies) {
			e.postTurn();
		}
		playersCanPlayCard = true;
		for(Player p: players) {
			p.removeAllBlock();
		}
		for(Player p: players) {
			p.endTurnDiscard();
			p.drawCards(5);
			p.preTurn();
			p.resetEnergy();
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
		//TODO handle relics
	}
	
	public void addCard(int selection, ArrayList<Card> cards, Player player) {
		if(selection >= 0 && selection <= 3) {
			player.addCardToDeck(cards.get(selection));
		}
	}

	public boolean playersCanPlayCard() {
		return playersCanPlayCard;
	}

	@Override
	public void notify(Entity entity, String message, Object data) {
		if(entity instanceof Enemy) {
			if(message.equals("diedtoattdamage") || message.equals("diedtotruedamage") || message.equals("diedtodamage")) {
				boolean allDead = true;
				for(Enemy e: enemies) {
					if(!e.isDead()) {
						allDead = false;
					}
				}
				if(allDead) {
					for(Player p: players) {
						Reward r = rewardManager.getReward(p.playerClass);
						p.lastReward = r;
						p.sendMessage(new Message("choosereward", r));
					}
				}
			}
		}
	}

}
