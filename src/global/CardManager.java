package global;

import java.util.ArrayList;

import server.ServerDataHandler;

public class CardManager {
	
	private ServerDataHandler sdh;
	private ArrayList<Card> retCards;
	
	public CardManager(ServerDataHandler sdh) {
		this.sdh = sdh;
		retCards = new ArrayList<Card>();
		generateCards();
	}
	
	public void generateCards() {
		retCards.add(new Card(1, "Strike", 0, new CardPlayCallBack(sdh) {
			public void play(Player play, int target) {
				Enemy targetedEnemy = sdh.enemies.get(target);
				if(targetedEnemy != null) {
					targetedEnemy.takeAttackDamage(6 + play.getStrength());
				}
			}
			
			public void playUpgraded(Player play, int target) {
				Enemy targetedEnemy = sdh.enemies.get(target);
				if(targetedEnemy != null) {
					targetedEnemy.takeAttackDamage(9 + play.getStrength());
				}
			}
		}));
		retCards.add(new Card(1, "Defend", 0, new CardPlayCallBack(sdh) {
			public void play(Player play, int target) {
				play.gainBlock(5 + play.getDex());
			}
			
			public void playUpgraded(Player play, int target) {
				play.gainBlock(8 + play.getDex());
			}
		}));
	}
	
	public Card getRetCard(String name) {
		return retCards.get(retCards.indexOf(new Card(0, name, 0, null))).copyCard();
	}
	
	public ArrayList<Card> getStartingDeck(PlayerClass pc){
		if(pc.equals(PlayerClass.RETRIBUTOR)) {
			return getRetStartingDeck();
		}
		return null;
	}
	
	public ArrayList<Card> getRetStartingDeck(){
		ArrayList<Card> starterDeck = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			starterDeck.add(getRetCard("Strike"));
			starterDeck.add(getRetCard("Defend"));
		}
		return starterDeck;
	}
}
