package server;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.Message;
import global.Player;

public class ServerDataHandler {

	public static int ENEMY_ACTION_DELAY = 2;
	public ArrayList<Player> players;
	public ArrayList<Enemy> enemies;

	private EnemyHandler enemyHandler;

	private boolean playersCanPlayCard;
	private ConnectionHandler connHandler;

	public ServerDataHandler() {
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		connHandler = new ConnectionHandler(this);
		new Thread(connHandler).start();
	}

	public Player createPlayer(ClientHandler clientHandler, Object data) {
		boolean nameTaken = false;
		for(Player p: players) {
			if(p.getName().equals(data)) {
				nameTaken = true;
			}
		}
		if(!nameTaken) {
			Player player = new Player();
			player.setName((String) data);
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
			sendMessageToAll(new Message("startgame", null));
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
			enemies = enemyHandler.getEnemies();
			sendMessageToAll(new Message());
		}
	}

	public Message playCard(Object obj, Player play) {
		if(obj instanceof Integer[] && playersCanPlayCard) {
			Integer[] cardData = (Integer[]) obj;
			return play.playCard(cardData[0], cardData[1]);
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
			p.postTurnSE();
		}
		playersCanPlayCard = false;
		for(Enemy e: enemies) {
			e.preTurnSE();
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
			e.postTurnSE();
		}
		playersCanPlayCard = true;
		for(Player p: players) {
			p.preTurnSE();
			p.resetEnergy();
		}
	}

	public boolean playersCanPlayCard() {
		return playersCanPlayCard;
	}

}
