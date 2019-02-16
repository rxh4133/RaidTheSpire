package client.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import client.ClientDataHandler;
import global.Enemy;
import global.Player;
import global.Reward;

public class ClientWindow {
	
	private JFrame frame;
	
	private ClientDataHandler clientDataHandler;
	
	private HomeWindow homeWindow;
	private LobbyWindow lobbyWindow;
	private RemadeFightWindow fightWindow;
	private RemadeRewardWindow remadeRewardWindow;
	
	public ClientWindow() {
		clientDataHandler = new ClientDataHandler(this);
		lobbyWindow = new LobbyWindow(clientDataHandler);
		homeWindow = new HomeWindow(clientDataHandler);
		fightWindow = new RemadeFightWindow(clientDataHandler);
		remadeRewardWindow = new RemadeRewardWindow(clientDataHandler);

		initFrame();
	}
	
	private void initFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setSize(1600, 900);
		frame.setTitle("Raid The Spire");
		frame.add(homeWindow, BorderLayout.CENTER);
		//frame.add(new RemadeRewardWindow(clientDataHandler), BorderLayout.CENTER);
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
		frame.remove(remadeRewardWindow);
		frame.revalidate();
		frame.add(fightWindow);
		frame.revalidate();
		frame.repaint();

	}
	
	public void switchToReward() {
		frame.remove(fightWindow);
		remadeRewardWindow = new RemadeRewardWindow(clientDataHandler);
		frame.add(remadeRewardWindow);
		frame.revalidate();
		frame.repaint();
	}
	
	public void displayReward(Reward reward) {
		remadeRewardWindow.showReward(reward);
		remadeRewardWindow.revalidate();
		remadeRewardWindow.repaint();
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
