package client.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientDataHandler;
import global.Enemy;
import global.Player;

public class FightWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ClientDataHandler dataHandler;
	
	private JPanel playerPanel;
	private JTextArea playerTextArea;
	private JPanel enemyPanel;
	private JTextArea enemyTextArea;
	private JTextField targetField;
	private JTextField cardIndexField;
	private JButton playCardButton;
	private JButton endTurnButton;

	public FightWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1,2));
		playerPanel = new JPanel();
		enemyPanel = new JPanel();
		
		playerTextArea = new JTextArea();
		enemyTextArea = new JTextArea();
		
		playerPanel.add(playerTextArea, BorderLayout.CENTER);
		enemyPanel.add(enemyTextArea, BorderLayout.CENTER);
		
		infoPanel.add(playerPanel);
		infoPanel.add(enemyPanel);
		
		this.add(infoPanel, BorderLayout.CENTER);
		
		cardIndexField = new JTextField(4);
		targetField = new JTextField(4);
		playCardButton = new JButton("Play Card");
		playCardButton.addActionListener(new PlayCardListener());
		endTurnButton = new JButton("End Turn");
		endTurnButton.addActionListener(new EndTurnListener());
		
		JPanel playPanel = new JPanel();
		playPanel.setLayout(new FlowLayout());
		playPanel.add(new JLabel("CardIndexToPlay: "));
		playPanel.add(cardIndexField);
		playPanel.add(new JLabel("TargetIndex: "));
		playPanel.add(targetField);
		playPanel.add(playCardButton);
		playPanel.add(endTurnButton);
		this.add(playPanel, BorderLayout.SOUTH);
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
	
	public void setSize(Component cmp, int w, int h){
		Dimension dim = new Dimension(w,h);
		cmp.setPreferredSize(dim);
		cmp.setMaximumSize(dim);
	}
	
	private class PlayCardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dataHandler.playCard(Integer.parseInt(cardIndexField.getText()), Integer.parseInt(targetField.getText()));
		}
	}
	
	private class EndTurnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dataHandler.endTurn();
		}
	}

}
