package server;

import java.util.ArrayList;
import java.util.HashMap;

import global.card.Card;
import global.PlayerClass;
import global.Rarity;
import global.card.CardType;

public class CardStorage {
	
	private HashMap<PlayerClass, HashMap<Rarity, ArrayList<Card>>> cardsByClassThenRarity;
	private HashMap<PlayerClass, HashMap<String, Card>> cardsByClassThenName;
	private HashMap<PlayerClass, HashMap<CardType, ArrayList<Card>>> cardsByClassThenType;

	public CardStorage() {
		cardsByClassThenRarity = new HashMap<PlayerClass, HashMap<Rarity, ArrayList<Card>>>();
		cardsByClassThenName = new HashMap<PlayerClass, HashMap<String, Card>>();
		cardsByClassThenType = new HashMap<PlayerClass, HashMap<CardType, ArrayList<Card>>>();
		for(PlayerClass pc: PlayerClass.values()) {
			cardsByClassThenRarity.put(pc, new HashMap<Rarity, ArrayList<Card>>());
			for(Rarity r: Rarity.values()) {
				cardsByClassThenRarity.get(pc).put(r, new ArrayList<Card>());
			}
			cardsByClassThenName.put(pc, new HashMap<String, Card>());
			cardsByClassThenType.put(pc, new HashMap<CardType, ArrayList<Card>>());
			for(CardType ct: CardType.values()) {
				cardsByClassThenType.get(pc).put(ct, new ArrayList<Card>());
			}
		}
		
	}
	
	public void put(Card c, PlayerClass pc) {
		cardsByClassThenRarity.get(pc).get(c.rarity).add(c);
		cardsByClassThenName.get(pc).put(c.name, c);
		cardsByClassThenType.get(pc).get(c.getCardType()).add(c);
	}
	
	public Card getCard(PlayerClass pc, String name) {
		Card card = cardsByClassThenName.get(pc).get(name);
		if(card == null) {
			card = cardsByClassThenName.get(PlayerClass.ALL).get(name);
		}
		return card;
	}
	
	public ArrayList<Card> getCards(PlayerClass pc, Rarity r) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.addAll(cardsByClassThenRarity.get(pc).get(r));
		cards.addAll(cardsByClassThenRarity.get(PlayerClass.ALL).get(r));
		return cards;
	}
	
	public ArrayList<Card> getCards(PlayerClass pc, CardType ct, boolean includeStarters) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.addAll(cardsByClassThenType.get(pc).get(ct));
		cards.addAll(cardsByClassThenType.get(PlayerClass.ALL).get(ct));
		if(!includeStarters) {
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).rarity.equals(Rarity.STARTER)) {
					cards.remove(i);
					i--;
				}
			}
		}
		return cards;
	}

}
