package client;

import java.util.ArrayList;

import global.Message;
import global.Player;
import global.PlayerClass;

public class ClientDataHandler {
	
	public ClientWindow window;
	public ServerListener serverListener;
	public String playerName;
	public PlayerClass playerClass;
	
	public ClientDataHandler(ClientWindow window) {
		this.window = window;
		serverListener = new ServerListener(this);
	}
	
	public void goToHome() {
		serverListener = new ServerListener(this);
		window.switchToHome();
	}

	
	public void connect(String address, String pname, Object playerClass) {
		serverListener.connect(address);
		playerName = pname;
		this.playerClass = (PlayerClass) playerClass;
	}
	
	public void connected() {
		serverListener.sendMessage(new Message("pjoin", new Object[]{playerName, playerClass}));
		window.switchToLobby();
	}
	
	public void updateLobbyPlayers(Object obj) {
		if(obj instanceof ArrayList<?>) {
			@SuppressWarnings("unchecked")
			ArrayList<Player> players = (ArrayList<Player>) obj;
			window.updateLobbyPlayers(players);
		}
	}
	
	public void readyToStartGame() {
		serverListener.sendMessage(new Message("prgame", null));
	}
}
