package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Player;

public class LobbyWindow extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel playersPanel;
	
	private JButton readyButton;
	
	private ClientDataHandler dataHandler;

	public LobbyWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Lobby"), BorderLayout.NORTH);
		playersPanel = new JPanel();
		playersPanel.setLayout(new FlowLayout());
		JPanel fug = new JPanel();
		fug.add(new JLabel("fug"));
		playersPanel.add(fug);
		
		readyButton = new JButton("Ready to start");
		readyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataHandler.readyToStartGame();
			}
			
		});
		
		this.add(playersPanel, BorderLayout.CENTER);
		this.add(readyButton, BorderLayout.SOUTH);
	}
	
	public void setPlayers(ArrayList<Player> players) {
		playersPanel.removeAll();
		playersPanel.revalidate();
		for(Player p: players) {
			playersPanel.add(new PlayerPanel(p));
		}
		playersPanel.revalidate();
		playersPanel.repaint();
	}
	
	private class PlayerPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public JLabel nameLabel;
		public JLabel readyLabel;
		
		public PlayerPanel(Player p) {
			this.setLayout(new GridLayout(2,1));
			nameLabel = new JLabel(p.getName());
			readyLabel = new JLabel(p.getReadyToStartGame()? "Ready" : "Not Ready");
			this.add(nameLabel);
			this.add(readyLabel);
		}
	}

}
