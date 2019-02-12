package server.managers;

import java.util.ArrayList;

import global.card.Card;
import global.relic.Relic;
import global.PlayerClass;
import global.Reward;

public class RewardManager {
	private CardManager cardManager;
	private RelicManager relicManager;
	
	public RewardManager(CardManager cm, RelicManager rm) {
		cardManager = cm;
		relicManager = rm;
	}
	
	public ArrayList<Card> getOneCardReward(PlayerClass pc){
		ArrayList<Card> reward = new ArrayList<Card>();
		reward.add(cardManager.getRandomCard(pc));
		reward.add(cardManager.getRandomCard(pc));
		reward.add(cardManager.getRandomCard(pc));
		return reward;
	}
	
	public ArrayList<Relic> getOneRelicReward(PlayerClass pc){
		ArrayList<Relic> reward = new ArrayList<Relic>();
		reward.add(relicManager.getRandomRelic());
		reward.add(relicManager.getRandomRelic());
		return reward;
	}
	
	public Reward getReward(PlayerClass pc) {
		Reward reward = new Reward();
		reward.cardReward1 = getOneCardReward(pc);
		reward.cardReward2 = getOneCardReward(pc);
		reward.cardReward3 = getOneCardReward(pc);
		reward.cardReward4 = getOneCardReward(pc);
		reward.cardReward5 = getOneCardReward(pc);
		reward.relicReward1 = getOneRelicReward(pc);
		reward.relicReward2 = getOneRelicReward(pc);
		return reward;
	}
	
	
}
