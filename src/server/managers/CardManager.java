package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Card;
import global.PlayerClass;
import global.Rarity;
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
import server.CardStorage;
import server.ServerDataHandler;

public class CardManager{

	private transient ServerDataHandler sdh;
	private CardStorage cards;

	public CardManager(ServerDataHandler sdh) {
		this.sdh = sdh;
		cards = new CardStorage();
		generateCards();
	}

	public void generateCards() {
		cards.put(new Strike(sdh), PlayerClass.ALL);
		cards.put(new Defend(sdh), PlayerClass.ALL);
		cards.put(new MountingHate(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new HatredsBite(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new DualStrike(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new Brace(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new EnmitysMight(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new Evade(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new HealthyRespect(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new Revenge(sdh), PlayerClass.RETRIBUTOR);

	}

	public Card getRandomCard(PlayerClass player) {
		if(player.equals(PlayerClass.RETRIBUTOR)) {
			double rand = Math.random() * 100;
			if(rand < 12.5) {
				ArrayList<Card> retRareCards = cards.getCards(player, Rarity.RARE);
				Collections.shuffle(retRareCards);
				return retRareCards.get(0).copyCard();
			}else if(rand < 50) {
				ArrayList<Card> retUnCards = cards.getCards(player, Rarity.UNCOMMON);
				Collections.shuffle(retUnCards);
				return retUnCards.get(0).copyCard();
			}else {
				ArrayList<Card> retComCards = cards.getCards(player, Rarity.COMMON);
				Collections.shuffle(retComCards);
				return retComCards.get(0).copyCard();
			}
		}
		return null;
	}

	public Card getCard(PlayerClass pc, String name) {
		return cards.getCard(pc, name).copyCard();
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
			starterDeck.add(getCard(PlayerClass.ALL, "Strike"));
			starterDeck.add(getCard(PlayerClass.ALL, "Defend"));
		}
		starterDeck.add(getCard(PlayerClass.RETRIBUTOR, "Enmity's Might"));
		return starterDeck;
	}
}
