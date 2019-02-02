package global;

import java.io.Serializable;

import server.ServerDataHandler;

public class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	public int cost;
	public final int defaultCost;
	
	public String name;
	public int rarity;
	
	protected transient ServerDataHandler dataHandler;

	public Card(int defCost, String name, int rarity, ServerDataHandler sdh) {
		defaultCost = defCost;
		cost = defCost;
		this.rarity = rarity;
		this.name = name;
		dataHandler = sdh;
	}
	
	public void play(Player player, int target) {
		
	}
	
	public void playUpgraded(Player player, int target) {
		
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Card && ((Card) obj).name.equals(name);
	}
	
	public Card copyCard() {
		return new Card(defaultCost, name, rarity, dataHandler);
	}
	
	public String toString() {
		return cost + " " + name;
	}
	
}
