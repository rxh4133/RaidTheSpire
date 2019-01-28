package global;

import java.io.Serializable;

public class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	public int cost;
	public final int defaultCost;
	
	public String name;
	public int rarity;
	
	private CardPlayCallBack cpcb;

	public Card(int defCost, String name, int rarity, CardPlayCallBack cpcb) {
		this.cpcb = cpcb;
		defaultCost = defCost;
		cost = defCost;
	}
	
	public void play(Player player, int target) {
		cpcb.play(player, target);
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Card && ((Card) obj).name.equals(name);
	}
	
	public Card copyCard() {
		return new Card(defaultCost, name, rarity, cpcb);
	}
	
}
