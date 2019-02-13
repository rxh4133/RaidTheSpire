package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import client.ClientDataHandler;
import global.Reward;
import global.RewardChoice;
import global.card.Card;
import global.relic.Relic;

public class RemadeRewardWindow extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel rewardPanel;
	
	private ArrayList<CardRewardPanel> crps;
	private ArrayList<RelicRewardPanel> rrps;
	
	private ClientDataHandler dataHandler;

	public RemadeRewardWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		rewardPanel = new JPanel();
		rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.Y_AXIS));
		JScrollPane rewardPane = new JScrollPane(rewardPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(rewardPane);
		this.setBackground(RemadeFightWindow.DARK_RED);
	}

	public void showReward(Reward reward) {
		crps = new ArrayList<CardRewardPanel>();
		rrps = new ArrayList<RelicRewardPanel>();
		crps.add(new CardRewardPanel(reward.cardReward1));
		crps.add(new CardRewardPanel(reward.cardReward2));
		crps.add(new CardRewardPanel(reward.cardReward3));
		crps.add(new CardRewardPanel(reward.cardReward4));
		crps.add(new CardRewardPanel(reward.cardReward5));
		rrps.add(new RelicRewardPanel(reward.relicReward1));
		rrps.add(new RelicRewardPanel(reward.relicReward2));
		
		rewardPanel.removeAll();
		rewardPanel.revalidate();
		
		for(CardRewardPanel crp: crps) {
			rewardPanel.add(crp);
		}
		
		for(RelicRewardPanel rrp: rrps) {
			rewardPanel.add(rrp);
		}
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				RewardChoice rc = new RewardChoice();
				rc.choice1Index = crps.get(0).rewardChoice;
				rc.choice2Index = crps.get(1).rewardChoice;
				rc.choice3Index = crps.get(2).rewardChoice;
				rc.choice4Index = crps.get(3).rewardChoice;
				rc.choice5Index = crps.get(4).rewardChoice;
				rc.choice6Index = rrps.get(0).rewardChoice;
				rc.choice7Index = rrps.get(1).rewardChoice;

				dataHandler.submitChoice(rc);
				submitButton.setEnabled(false);
			}
			
		});
		
		rewardPanel.add(submitButton);
		rewardPanel.revalidate();
		rewardPanel.repaint();
	}
	
	private class RelicRewardPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public int rewardChoice = -1;
		private JTextField displayChoice;
		private ArrayList<Relic> relics;
		public RelicRewardPanel(ArrayList<Relic> relics) {
			this.relics = relics;
			JPanel choicePanel = new JPanel(new GridLayout(1,relics.size() + 1));
			this.setLayout(new BorderLayout());
			for(int i = 0; i < relics.size(); i++) {
				JButton relicChoice = new JButton(relics.get(i).toString());
				relicChoice.addActionListener(new RCL(i));
				relicChoice.setBackground(Color.DARK_GRAY);
				relicChoice.setForeground(Color.WHITE);
				relicChoice.setToolTipText(relics.get(i).getDesc());
				choicePanel.add(relicChoice);
			}
			JButton skipChoice = new JButton("Skip");
			skipChoice.addActionListener(new RCL(-1));
			skipChoice.setBackground(Color.GRAY);
			choicePanel.add(skipChoice);
			
			displayChoice = new JTextField();
			displayChoice.setEditable(false);
			displayChoice.setBackground(RemadeFightWindow.DARK_ORANGE);
			displayChoice.setForeground(Color.WHITE);
			displayChoice.setText("Skipping");
			this.add(choicePanel, BorderLayout.CENTER);
			this.add(displayChoice, BorderLayout.SOUTH);
		}


		private class RCL implements ActionListener{

			private int choice;

			public RCL(int c) {
				choice = c;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				rewardChoice = choice;
				if(choice < 0 || choice >= relics.size()) {
					displayChoice.setText("Skipping");
				}else {
					displayChoice.setText("Taking " + relics.get(choice).toString());
				}
			}

		}
	}

	private class CardRewardPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public int rewardChoice = -1;
		private JTextField displayChoice;
		private ArrayList<Card> cards;
		public CardRewardPanel(ArrayList<Card> cards) {
			this.cards = cards;
			JPanel choicePanel = new JPanel(new GridLayout(1,cards.size() + 1));
			this.setLayout(new BorderLayout());
			for(int i = 0; i < cards.size(); i++) {
				JButton cardChoice = new JButton(cards.get(i).toString());
				cardChoice.setBackground(Color.DARK_GRAY);
				cardChoice.setForeground(Color.WHITE);
				cardChoice.addActionListener(new RCL(i));
				cardChoice.setToolTipText(cards.get(i).getDesc());
				choicePanel.add(cardChoice);
			}
			JButton skipChoice = new JButton("Skip");
			skipChoice.addActionListener(new RCL(-1));
			skipChoice.setBackground(Color.GRAY);
			choicePanel.add(skipChoice);
			
			displayChoice = new JTextField();
			displayChoice.setEditable(false);
			displayChoice.setText("Skipping");
			displayChoice.setBackground(RemadeFightWindow.DARK_ORANGE);
			displayChoice.setForeground(Color.WHITE);
			displayChoice.setBorder(null);
			this.add(choicePanel, BorderLayout.CENTER);
			this.add(displayChoice, BorderLayout.SOUTH);
		}


		private class RCL implements ActionListener{

			private int choice;

			public RCL(int c) {
				choice = c;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				rewardChoice = choice;
				if(choice < 0 || choice >= cards.size()) {
					displayChoice.setText("Skipping");
				}else {
					displayChoice.setText("Taking " + cards.get(choice).toString());
				}
			}

		}
	}
}
