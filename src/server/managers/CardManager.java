package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Card;
import global.PlayerClass;
import global.cards.Defend;
import global.cards.Strike;
import global.cards.retributor.Brace;
import global.cards.retributor.DualStrike;
import global.cards.retributor.EnmitysMight;
import global.cards.retributor.Evade;
import global.cards.retributor.HatredsBite;
import global.cards.retributor.HealthyRespect;
import global.cards.retributor.MountingHate;
import global.cards.retributor.Revenge;
import server.ServerDataHandler;

public class CardManager{

	private transient ServerDataHandler sdh;
	private ArrayList<Card> retCards;
	private ArrayList<Card> retComCards;
	private ArrayList<Card> retUnCards;
	private ArrayList<Card> retRareCards;

	public CardManager(ServerDataHandler sdh) {
		this.sdh = sdh;
		retCards = new ArrayList<Card>();
		retComCards = new ArrayList<Card>();
		retUnCards = new ArrayList<Card>();
		retRareCards = new ArrayList<Card>();
		generateCards();
	}

	public void generateCards() {
		retCards.add(new Strike(sdh));
		retCards.add(new Defend(sdh));
		retCards.add(new MountingHate(sdh));
		retCards.add(new HatredsBite(sdh));
		retCards.add(new DualStrike(sdh));
		retCards.add(new Brace(sdh));
		retCards.add(new EnmitysMight(sdh));
		retCards.add(new Evade(sdh));
		retCards.add(new HealthyRespect(sdh));
		retCards.add(new Revenge(sdh));
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
		starterDeck.add(getRetCard("Enmity's Might"));
		return starterDeck;
	}
}
