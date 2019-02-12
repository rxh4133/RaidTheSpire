package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.ClientDataHandler;
import global.Enemy;
import global.EnemyAction;
import global.Player;
import global.card.Card;
import global.statuseffect.StatusEffect;

public class RemadeFightWindow extends JPanel{
	private static final long serialVersionUID = 1L;

	private JPanel entityPanel;
	private JPanel playerPanel;
	private ArrayList<PlayerPanel> players;
	private JScrollPane playerPane;
	private JPanel enemyPanel;
	private JScrollPane enemyPane;
	private JPanel controlPanel;
	private JPanel cardPanel;

	private int target = 0;
	
	public static final Color DARK_YELLOW = new Color(63, 63, 21);
	public static final Color DARK_ORANGE = new Color(63, 48, 28);
	public static final Color DARK_RED = new Color(66, 26, 23);

	private ClientDataHandler dataHandler;

	public RemadeFightWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		players = new ArrayList<PlayerPanel>();
		this.setLayout(new BorderLayout());
		entityPanel = new JPanel(new GridLayout(1,2));

		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPane = new JScrollPane(playerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		playerPanel.setBackground(DARK_RED);

		enemyPanel = new JPanel();
		enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
		enemyPane = new JScrollPane(enemyPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		enemyPanel.setBackground(DARK_RED);

		this.add(entityPanel, BorderLayout.CENTER);
		entityPanel.add(playerPane);
		entityPanel.add(enemyPane);
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				doResizing();
			}
		});

		controlPanel = new JPanel(new BorderLayout());
		
		cardPanel = new JPanel(new FlowLayout());
		cardPanel.setBackground(DARK_YELLOW);
		controlPanel.add(cardPanel, BorderLayout.CENTER);
		
		JButton endTurnButton = new JButton("Vote to End Turn");
		endTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataHandler.endTurn();
			}
		});
		
		controlPanel.add(endTurnButton, BorderLayout.EAST);
		
		this.add(controlPanel, BorderLayout.SOUTH);
		
		
	}

	public void setPlayers(ArrayList<Player> player) {
		playerPanel.removeAll();
		playerPanel.revalidate();
		players.removeAll(players);
		for(int i = 0; i < player.size(); i++) {
			PlayerPanel pp = new PlayerPanel(player.get(i), i,(int) (getWidth()/2.3),250);
			setSize(pp, (int) (getWidth()/2.3), 250);

			players.add(pp);
			playerPanel.add(pp);
			playerPanel.add(Box.createRigidArea(new Dimension(0,75)));
			if(player.get(i).getName().equals(dataHandler.playerName)) {
				cardPanel.removeAll();
				for(int j = 0; j < player.get(i).getHand().size(); j++) {
					cardPanel.add(new CardPanel(player.get(i).getHand().get(j), j));
				}
			}
		}
		playerPanel.revalidate();
		playerPanel.repaint();
		cardPanel.revalidate();
		cardPanel.repaint();
	}

	public void setEnemies(ArrayList<Enemy> enemy) {
		enemyPanel.removeAll();
		for(int i = 0; i < enemy.size(); i++) {
			EnemyPanel ep = new EnemyPanel(enemy.get(i), i);
			enemyPanel.add(ep);
			setSize(ep, (int) (getWidth()/2.3), 250);
			enemyPanel.add(Box.createRigidArea(new Dimension(0,75)));
		}
		enemyPanel.revalidate();
		enemyPanel.repaint();
	}

	public void doResizing() {
		for(PlayerPanel c: players) {
			setSize(c, (int) (getWidth()/2.3), 250);
			c.doResizing();
			c.revalidate();
		}
		playerPanel.revalidate();
		for(Component c: enemyPanel.getComponents()) {
			if(c instanceof EnemyPanel) {
				setSize(c, (int) (getWidth()/2.3), 250);
				c.revalidate();
				((EnemyPanel) c).doResizing();
			}
		}
	}

	public static Component setSize(Component cmp, int w, int h){
		Dimension dim = new Dimension(w,h);
		cmp.setPreferredSize(dim);
		cmp.setMaximumSize(new Dimension(w,h));
		cmp.setMinimumSize(dim);
		cmp.revalidate();
		return cmp;
	}

	private class CardPanel extends JPanel{
		private static final long serialVersionUID = 1L;

		public CardPanel(Card c, int index) {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(new JLabel(c.cost + " " + c.name));
			JButton playButton = new JButton("Play");
			playButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dataHandler.playCard(index, target);
				}
			});
			this.add(playButton);

			JButton targetButton = new JButton("Target");
			targetButton.setAlignmentX(CENTER_ALIGNMENT);
			targetButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					target = index;
				}

			});
			this.add(targetButton);
		}
	}

	private class EnemyPanel extends JPanel{
		private static final long serialVersionUID = 1L;

		private HealthBar hb;

		public EnemyPanel(Enemy en, int index) {
			this.setAlignmentX(CENTER_ALIGNMENT);
			this.setBackground(DARK_ORANGE);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JLabel eName = new JLabel(en.getName());
			eName.setForeground(Color.WHITE);
			eName.setAlignmentX(RemadeFightWindow.LEFT_ALIGNMENT);
			this.add(eName);

			hb = new HealthBar(en.getCurHealth(), en.getMaxHealth(), en.getBlock());
			this.add(hb);

			this.add(Box.createRigidArea(new Dimension(0, 20)));

			JPanel statusPanel = new JPanel();
			statusPanel.setBackground(DARK_ORANGE);
			statusPanel.setLayout(new FlowLayout());
			for(StatusEffect se: en.getEffects()) {
				JLabel temp = new JLabel(se.toString());
				temp.setForeground(Color.WHITE);
				statusPanel.add(temp);
			}


			JButton targetButton = new JButton("Target");
			targetButton.setAlignmentX(CENTER_ALIGNMENT);
			targetButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					target = index;
				}

			});


			this.add(targetButton);
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			this.add(statusPanel);
			
			
			JPanel actionPanel = new JPanel(new FlowLayout());
			for(EnemyAction ea: en.getActions()) {
				JLabel temp = new JLabel(ea.toString());
				temp.setForeground(Color.white);
				actionPanel.add(temp);
			}
			actionPanel.setBackground(DARK_ORANGE);
			this.add(actionPanel);
		}

		public void doResizing() {
			RemadeFightWindow.setSize(hb, getWidth(), 80);
		}
	}

	private class PlayerPanel extends JPanel{
		private static final long serialVersionUID = 1L;

		private HealthBar hb;

		public PlayerPanel(Player play, int index, int width, int height) {
			this.setAlignmentX(CENTER_ALIGNMENT);
			this.setBackground(DARK_ORANGE);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JLabel playerInfo = new JLabel(play.getName() + " Energy: " + play.getCurEnergy() + "/" + play.getMaxEnergy());
			playerInfo.setAlignmentX(LEFT_ALIGNMENT);
			playerInfo.setForeground(Color.WHITE);
			this.add(playerInfo);

			hb = new HealthBar(play.getCurHealth(), play.getMaxHealth(), play.getBlock());
			this.add(hb);
			RemadeFightWindow.setSize(hb, width, 40);

			this.add(Box.createRigidArea(new Dimension(0, 20)));

			JPanel statusPanel = new JPanel();
			statusPanel.setBackground(DARK_ORANGE);
			statusPanel.setLayout(new FlowLayout());
			for(StatusEffect se: play.getEffects()) {
				JLabel temp = new JLabel(se.toString());
				temp.setForeground(Color.WHITE);
				statusPanel.add(temp);
			}

			JButton targetButton = new JButton("Target");
			targetButton.setAlignmentX(CENTER_ALIGNMENT);
			this.add(targetButton);
			targetButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					target = index;
				}

			});

			this.add(Box.createRigidArea(new Dimension(0, 20)));
			this.add(statusPanel);
			
		}

		public void doResizing() {
			RemadeFightWindow.setSize(hb, getWidth(), getHeight()/6);
			hb.revalidate();
			hb.repaint();
		}
	}

	private class HealthBar extends JPanel{
		private static final long serialVersionUID = 1L;
		private int cur;
		private int max;
		private int block;
		public HealthBar(int cur, int max, int block) {
			this.cur = cur;
			this.max = max;
			this.block = block;
		}

		public void paint(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.RED);
			int w = (int) (this.getWidth() *  (cur/(double)max));
			g.fillRect(0, 0, w, getHeight());
			g.setColor(new Color(152, 183, 234));
			w = (int) (this.getWidth() *  (block/(double)max));
			g.fillRect(0, 0, w, getHeight());
			g.setColor(Color.WHITE);
			g.drawString("(" + block + " B) " + cur + "/" + max, 00, 10);
		}
	}
}
