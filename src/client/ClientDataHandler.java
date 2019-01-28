package client;

import java.util.ArrayList;

import global.Message;
import global.Player;

public class ClientDataHandler {
	
	public ClientWindow window;
	public ServerListener serverListener;
	public String playerName;
	
	public ClientDataHandler(ClientWindow window) {
		this.window = window;
		serverListener = new ServerListener(this);
	}
	
	public void goToHome() {
		serverListener = new ServerListener(this);
		window.switchToHome();
	}

	
	public void connect(String address, String pname) {
		serverListener.connect(address);
		playerName = pname;
	}
	
	public void connected() {
		serverListener.sendMessage(new Message("pjoin", playerName));
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
