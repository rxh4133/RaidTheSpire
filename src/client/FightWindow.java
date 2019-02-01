package client;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import global.Enemy;
import global.Player;

public class FightWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel playerPanel;
	private JTextArea playerTextArea;
	private JPanel enemyPanel;
	private JTextArea enemyTextArea;

	public FightWindow() {
		this.setLayout(new GridLayout(1,2));
		playerPanel = new JPanel();
		enemyPanel = new JPanel();
		
		playerTextArea = new JTextArea();
		enemyTextArea = new JTextArea();
		
		playerPanel.add(playerTextArea);
		enemyPanel.add(enemyTextArea);
		
		this.add(playerPanel);
		this.add(enemyPanel);
		
	}
	
	public void setPlayers(ArrayList<Player> players) {
		playerTextArea.setText("");
		for(Player p: players) {
			playerTextArea.append(p.toString() + "\n");
		}
	}
	
	public void setEnemies(ArrayList<Enemy> enemies) {
		enemyTextArea.setText("");
		for(Enemy e: enemies) {
			enemyTextArea.append(e.toString() + "\n");
		}
	}
}
