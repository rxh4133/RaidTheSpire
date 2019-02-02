package global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import server.ServerDataHandler;

public class CardManager implements Serializable{

	private transient ServerDataHandler sdh;
	private transient ArrayList<Card> retCards;
	private transient ArrayList<Card> retComCards;
	private transient ArrayList<Card> retUnCards;
	private transient ArrayList<Card> retRareCards;

	public CardManager(ServerDataHandler sdh) {
		this.sdh = sdh;
		retCards = new ArrayList<Card>();
		retComCards = new ArrayList<Card>();
		retUnCards = new ArrayList<Card>();
		retRareCards = new ArrayList<Card>();
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
		retCards.add(new Card(1, "Dual Strike", 1, new CardPlayCallBack(sdh) {
			public void play(Player play, int target) {
				sdh.enemies.get(target).takeAttackDamage(3 + play.getStrength());
				sdh.enemies.get(target).takeAttackDamage(3 + play.getStrength());
				play.drawCards(1);
			}

			public void playUpgraded(Player play, int target) {
				sdh.enemies.get(target).takeAttackDamage(4 + play.getStrength());
				sdh.enemies.get(target).takeAttackDamage(4 + play.getStrength());
				play.drawCards(1);
			}
		}));
		retCards.add(new Card(2, "Hatred's Bite", 2, new CardPlayCallBack(sdh) {
			public void play(Player play, int target) {
				play.gainBlock(15);
				play.addSE(new StatusEffect("Thorns", 5));
				play.addSE(new StatusEffect("Thorns Down", 5) {
					private static final long serialVersionUID = 1L;
					public void preTurn(Entity entity) {
						entity.reduceSE(new StatusEffect("Thorns", 5), 5);
					}
				});
			}

			public void playUpgraded(Player play, int target) {
				play.gainBlock(20);
				play.addSE(new StatusEffect("Thorns", 5));
				play.addSE(new StatusEffect("Thorns Down", 5) {
					private static final long serialVersionUID = 1L;
					public void preTurn(Entity entity) {
						entity.reduceSE(new StatusEffect("Thorns", 5), 5);
					}
				});
			}
		}));
		retCards.add(new Card(2, "Mounting Hate", 3, new CardPlayCallBack(sdh) {
			public void play(Player play, int target) {
				play.addSE(new StatusEffect("Thorns Gen", 2) {
					private static final long serialVersionUID = 1L;
					public void postTurn(Entity entity) {
						entity.addSE(new StatusEffect("Thorns", entity.getSE("Thorns Gen").value));
					}
				});
			}

			public void playUpgraded(Player play, int target) {
				play.addSE(new StatusEffect("Thorns Gen", 3) {
					private static final long serialVersionUID = 1L;
					public void postTurn(Entity entity) {
						entity.addSE(new StatusEffect("Thorns", 2));
					}
				});
			}
		}));
		for(Card c: retCards) {
			switch(c.rarity) {
			case 1: retComCards.add(c); break;
			case 2: retUnCards.add(c); break;
			case 3: retRareCards.add(c); break;
			}
		}
	}

	public Card getRandomCard(PlayerClass player) {
		if(player.equals(PlayerClass.RETRIBUTOR)) {
			double rand = Math.random() * 100;
			if(rand < 12.5) {
				Collections.shuffle(retRareCards);
				return retRareCards.get(0).copyCard();
			}else if(rand < 50) {
				Collections.shuffle(retUnCards);
				return retUnCards.get(0).copyCard();
			}else {
				Collections.shuffle(retComCards);
				return retComCards.get(0).copyCard();
			}
		}
		return null;
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
