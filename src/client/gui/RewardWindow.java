package client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientDataHandler;
import global.Reward;
import global.RewardChoice;

public class RewardWindow extends JPanel{

	private ClientDataHandler dataHandler;
	
	private JTextArea rewardChoiceArea;
	private JTextField[] rewardIndexFields;
	
	private JButton sendChoiceButton;
	
	public RewardWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		
		rewardChoiceArea = new JTextArea();
		this.add(rewardChoiceArea, BorderLayout.CENTER);
		rewardIndexFields = new JTextField[5];
		for(int i = 0; i < 5; i++) {
			rewardIndexFields[i] = new JTextField(4);
		}
		JPanel selectPanel = new JPanel(new FlowLayout());
		for(int i = 0; i < 5; i++) {
			selectPanel.add(new JLabel("CR" + i + ":"));
			selectPanel.add(rewardIndexFields[i]);
		}
		sendChoiceButton = new JButton("Submit Choices");
		sendChoiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RewardChoice rc = new RewardChoice();
				rc.choice1Index = Integer.parseInt(rewardIndexFields[0].getText());
				rc.choice2Index = Integer.parseInt(rewardIndexFields[1].getText());
				rc.choice3Index = Integer.parseInt(rewardIndexFields[2].getText());
				rc.choice4Index = Integer.parseInt(rewardIndexFields[3].getText());
				rc.choice5Index = Integer.parseInt(rewardIndexFields[4].getText());

				dataHandler.submitChoice(rc);
			}
		});
		
		selectPanel.add(sendChoiceButton);
		this.add(selectPanel, BorderLayout.SOUTH);
	}
	
	public void showReward(Reward reward) {
		rewardChoiceArea.setText("");
		rewardChoiceArea.append(reward.cardReward1.toString() + "\n");
		rewardChoiceArea.append(reward.cardReward2.toString() + "\n");
		rewardChoiceArea.append(reward.cardReward3.toString() + "\n");
		rewardChoiceArea.append(reward.cardReward4.toString() + "\n");
		rewardChoiceArea.append(reward.cardReward5.toString() + "\n");
	}
}
