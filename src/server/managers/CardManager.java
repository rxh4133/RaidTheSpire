package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.card.Card;
import global.PlayerClass;
import global.Rarity;
import global.card.*;
import global.card.cards.Defend;
import global.card.cards.Strike;
import global.card.cards.resipiscent.Hug;
import global.card.cards.resipiscent.Weave;
import global.card.cards.retributor.*;
import global.card.cards.revenant.*;
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
		cards.put(new HateSpike(sdh), PlayerClass.RETRIBUTOR);
		cards.put(new Probe(sdh), PlayerClass.RETRIBUTOR);

		cards.put(new FelFyre(sdh), PlayerClass.REVENANT);
		cards.put(new RawStrike(sdh), PlayerClass.REVENANT);
		cards.put(new PowerBlow(sdh), PlayerClass.REVENANT);
		cards.put(new Numb(sdh), PlayerClass.REVENANT);
		cards.put(new Sacrifice(sdh), PlayerClass.REVENANT);
		cards.put(new TakeAdvantage(sdh), PlayerClass.REVENANT);
		cards.put(new Gauge(sdh), PlayerClass.REVENANT);
		cards.put(new Salvage(sdh), PlayerClass.REVENANT);
		cards.put(new Remember(sdh), PlayerClass.REVENANT);
		cards.put(new Cannibalize(sdh), PlayerClass.REVENANT);
		cards.put(new NecroticTouch(sdh), PlayerClass.REVENANT);
		cards.put(new Recycle(sdh), PlayerClass.REVENANT);
		
		cards.put(new Hug(sdh), PlayerClass.RESIPISCENT);
		cards.put(new Weave(sdh), PlayerClass.RESIPISCENT);
	}

	public Card getRandomCard(PlayerClass player) {
		double rand = Math.random() * 100;
		if(rand < 12.5) {
			ArrayList<Card> rareCards = cards.getCards(player, Rarity.RARE);
			Collections.shuffle(rareCards);
			return rareCards.get(0).clone();
		}else if(rand < 50) {
			ArrayList<Card> unCards = cards.getCards(player, Rarity.UNCOMMON);
			Collections.shuffle(unCards);
			return unCards.get(0).clone();
		}else {
			ArrayList<Card> comCards = cards.getCards(player, Rarity.COMMON);
			Collections.shuffle(comCards);
			return comCards.get(0).clone();
		}
	}

	public Card getCard(PlayerClass pc, String name) {
		return cards.getCard(pc, name).clone();
	}

	public ArrayList<Card> getStartingDeck(PlayerClass pc){
		if(pc.equals(PlayerClass.RETRIBUTOR)) {
			return getRetStartingDeck();
		}else if(pc.equals(PlayerClass.REVENANT)) {
			return getRevStartingDeck();
		}else if(pc.equals(PlayerClass.RESIPISCENT)){
			return getResStartingDeck();
		}else if(pc.equals(PlayerClass.ALL)) {
			ArrayList<Card> fug = new ArrayList<Card>();
			fug.add(new Card(4, "Haha very funny, no you're not all the classes", "", "", Rarity.STARTER, CardType.CURSE, false, true, sdh));
			return fug;
		}
		return null;
	}

	public ArrayList<Card> getRevStartingDeck(){
		ArrayList<Card> starterDeck = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			starterDeck.add(getCard(PlayerClass.ALL, "Strike"));
			starterDeck.add(getCard(PlayerClass.ALL, "Defend"));
		}
		starterDeck.add(getCard(PlayerClass.REVENANT, "Fel Fyre"));
		return starterDeck;
	}

	public ArrayList<Card> getRetStartingDeck(){
		ArrayList<Card> starterDeck = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			starterDeck.add(getCard(PlayerClass.ALL, "Strike"));
			starterDeck.add(getCard(PlayerClass.ALL, "Defend"));
		}
		starterDeck.add(getCard(PlayerClass.RETRIBUTOR, "Enmity's Might"));
		starterDeck.add(getCard(PlayerClass.RETRIBUTOR, "Healthy Respect"));
		return starterDeck;
	}
	
	public ArrayList<Card> getResStartingDeck(){
		ArrayList<Card> starterDeck = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			starterDeck.add(getCard(PlayerClass.ALL, "Strike"));
			starterDeck.add(getCard(PlayerClass.ALL, "Defend"));
		}
		starterDeck.add(getCard(PlayerClass.RESIPISCENT, "Hug"));
		starterDeck.add(getCard(PlayerClass.RESIPISCENT, "Weave"));
		return starterDeck;
	}
}
