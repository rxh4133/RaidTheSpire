package client;

import java.util.ArrayList;

import client.gui.ClientWindow;
import global.Enemy;
import global.Message;
import global.Player;
import global.PlayerClass;
import global.Reward;
import global.RewardChoice;

public class ClientDataHandler {
	
	public ClientWindow window;
	public C2SCommunicator serverListener;
	public String playerName;
	public PlayerClass playerClass;
	
	public static final int LOBBY = 0;
	public static final int FIGHT = 1;
	
	
	private int state;
	
	public ClientDataHandler(ClientWindow window) {
		this.window = window;
		serverListener = new C2SCommunicator(this);
	}
	
	public void goToHome() {
		serverListener = new C2SCommunicator(this);
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
			@SuppressWarnings("unchecked")
			ArrayList<Player> players = (ArrayList<Player>) obj;
			switch(state) {
			case LOBBY: window.updateLobbyPlayers(players); break;
			case FIGHT: window.updateFightPlayers(players); break;
			}
		}
	}
	
	public void submitChoice(RewardChoice rc) {
		serverListener.sendMessage(new Message("rewardchoice", rc));
		serverListener.sendMessage(new Message("prfight", null));
	}
	
	public void displayReward(Object reward) {
		window.switchToReward();
		window.displayReward((Reward) reward);
	}
	
	public void endTurn() {
		serverListener.sendMessage(new Message("prend", null));
	}
	
	public void playCard(int index, int entityTarget, int cardTarget) {
		serverListener.sendMessage(new Message("pcard", new Integer[] {index, entityTarget, cardTarget}));
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
