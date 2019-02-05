package global;

import java.io.Serializable;

import server.CardFailException;
import server.ServerDataHandler;

public class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	public int cost;
	public final int defaultCost;
	
	public String name;
	public Rarity rarity;
	private CardType type;
	
	protected transient ServerDataHandler dataHandler;

	public Card(int defCost, String name, Rarity rarity, CardType ct, ServerDataHandler sdh) {
		defaultCost = defCost;
		cost = defCost;
		this.rarity = rarity;
		this.name = name;
		type = ct;
		dataHandler = sdh;
	}
	
	public void onAddToDeck(Player p) {
		
	}
	
	public void play(Player player, int target) throws CardFailException{
		
	}
	
	public void playUpgraded(Player player, int target) throws CardFailException{
		
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Card && ((Card) obj).name.equals(name);
	}
	
	public Card copyCard() {
		return new Card(defaultCost, name, rarity, type, dataHandler);
	}
	
	public CardType getCardType() {
		return type;
	}
	
	public String toString() {
		return cost + " " + name;
	}
	
}
