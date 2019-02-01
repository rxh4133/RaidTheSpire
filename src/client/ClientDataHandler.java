package client;

import java.util.ArrayList;

import global.Enemy;
import global.Message;
import global.Player;
import global.PlayerClass;

public class ClientDataHandler {
	
	public ClientWindow window;
	public ServerListener serverListener;
	public String playerName;
	public PlayerClass playerClass;
	
	public static final int LOBBY = 0;
	public static final int FIGHT = 1;
	
	
	private int state;
	
	public ClientDataHandler(ClientWindow window) {
		this.window = window;
		serverListener = new ServerListener(this);
	}
	
	public void goToHome() {
		serverListener = new ServerListener(this);
		window.switchToHome();
	}
	
	public void goToFight() {
		state = FIGHT;
		window.switchToFight();
		
	}
	
	public void connect(String address, String pname, Object playerClass) {
		serverListener.connect(address);
		playerName = pname;
		this.playerClass = (PlayerClass) playerClass;
	}
	
	public void connected() {
		serverListener.sendMessage(new Message("pjoin", new Object[]{playerName, playerClass}));
		state = LOBBY;
		window.switchToLobby();
	}
	
	public void updatePlayers(Object obj) {
		if(obj instanceof ArrayList<?>) {
			System.out.println(obj);
			@SuppressWarnings("unchecked")
			ArrayList<Player> players = (ArrayList<Player>) obj;
			switch(state) {
			case LOBBY: window.updateLobbyPlayers(players); break;
			case FIGHT: window.updateFightPlayers(players); break;
			}
		}
	}
	
	public void updateEnemies(Object obj) {
		@SuppressWarnings("unchecked")
		ArrayList<Enemy> enemies = (ArrayList<Enemy>) obj;
		window.updateEnemies(enemies);
	}
	
	public void readyToStartGame() {
		serverListener.sendMessage(new Message("prgame", null));
	}
}
