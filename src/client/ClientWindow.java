package client;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import global.Player;

public class ClientWindow {
	
	private JFrame frame;
	
	private ClientDataHandler clientDataHandler;
	
	private HomeWindow homeWindow;
	private LobbyWindow lobbyWindow;
	
	public ClientWindow() {
		clientDataHandler = new ClientDataHandler(this);
		initFrame();
	}
	
	private void initFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 450);
		frame.setTitle("Raid The Spire");
		homeWindow = new HomeWindow(clientDataHandler);
		frame.add(homeWindow, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public void switchToLobby() {
		frame.remove(homeWindow);
		lobbyWindow = new LobbyWindow(clientDataHandler);
		frame.add(lobbyWindow, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void switchToHome() {
		frame.remove(lobbyWindow);
		frame.add(homeWindow, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void updateLobbyPlayers(ArrayList<Player> players) {
		lobbyWindow.setPlayers(players);
	}
	
	@Override
	public String toString() {
		return "I ain't got nothin to print yet";
	}
}
