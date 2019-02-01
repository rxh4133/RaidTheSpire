package server;

import java.util.ArrayList;

import global.CardManager;
import global.Enemy;
import global.EnemyAction;
import global.EnemyManager;
import global.Message;
import global.Player;
import global.PlayerClass;

public class ServerDataHandler {

	public static int ENEMY_ACTION_DELAY = 2;
	public ArrayList<Player> players;
	public ArrayList<Enemy> enemies;

	private EnemyManager enemyManager;

	private boolean playersCanPlayCard;
	private ConnectionHandler connHandler;
	
	private CardManager carder;
	
	private int fightTracker;

	public ServerDataHandler() {
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		connHandler = new ConnectionHandler(this);
		carder = new CardManager(this);
		enemyManager = new EnemyManager(this);
		new Thread(connHandler).start();
	}

	public Player createPlayer(ClientHandler clientHandler, Object data) {
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
			takeEnemyTurn();
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
		player.setReadyToStartGame(!player.getReadyToStartFight());
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
			sendMessageToAll(new Message());
		}
	}
	
	public void startGame() {
		for(Player p: players) {
			p.setDeck(carder.getStartingDeck(p.playerClass));
		}
	}
	
	public void startFight(int fightNum) {
		enemies = enemyManager.getEnemiesForFight(fightNum);
		for(Player p: players) {
			p.removeHandAndDiscard();
			p.shuffleCardsFromDeck();
			p.fightStartSubs();
		}
		sendMessageToAll(new Message("startFight", null));
		sendMessageToAll(new Message("players", players));
		sendMessageToAll(new Message("enemies", enemies));
	}

	public Message playCard(Object obj, Player play) {
		if(obj instanceof Integer[] && playersCanPlayCard) {
			Integer[] cardData = (Integer[]) obj;
			Message result = play.playCard(cardData[0], cardData[1]);
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

	public void takeEnemyTurn() {
		for(Player p: players) {
			p.postTurn();
		}
		playersCanPlayCard = false;
		for(Enemy e: enemies) {
			e.preTurn();
		}
		for(Enemy e: enemies) {
			EnemyAction ea = e.takeAction();
			sendMessageToAll(new Message("eaction", ea));
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
			p.preTurn();
			p.resetEnergy();
			p.drawCards(2);
		}
	}

	public boolean playersCanPlayCard() {
		return playersCanPlayCard;
	}

}
