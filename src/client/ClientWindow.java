package client;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import global.Enemy;
import global.Player;

public class ClientWindow {
	
	private JFrame frame;
	
	private ClientDataHandler clientDataHandler;
	
	private HomeWindow homeWindow;
	private LobbyWindow lobbyWindow;
	private FightWindow fightWindow;
	
	public ClientWindow() {
		clientDataHandler = new ClientDataHandler(this);
		lobbyWindow = new LobbyWindow(clientDataHandler);
		homeWindow = new HomeWindow(clientDataHandler);
		fightWindow = new FightWindow(clientDataHandler);

		initFrame();
	}
	
	private void initFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 450);
		frame.setTitle("Raid The Spire");
		frame.add(homeWindow, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public void switchToLobby() {
		frame.remove(homeWindow);
		frame.add(lobbyWindow, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void switchToHome() {
		frame.remove(lobbyWindow);
		frame.add(homeWindow, BorderLayout.CENTER);
		frame.revalidate();
	}
	
	public void switchToFight() {
		frame.remove(lobbyWindow);
		frame.add(fightWindow);
		frame.revalidate();
	}
	
	public void updateLobbyPlayers(ArrayList<Player> players) {
		lobbyWindow.setPlayers(players);
	}
	
	public void updateFightPlayers(ArrayList<Player> players) {
		fightWindow.setPlayers(players);
	}
	
	public void updateEnemies(ArrayList<Enemy> enemies) {
		fightWindow.setEnemies(enemies);
	}
	
	@Override
	public String toString() {
		return "This is a client window";
	}
}
