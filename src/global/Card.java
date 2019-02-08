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
	public boolean exhausts;
	private boolean upgraded;
	public boolean playable;

	public Card(int defCost, String name, Rarity rarity, CardType ct, ServerDataHandler sdh) {
		defaultCost = defCost;
		cost = defCost;
		this.rarity = rarity;
		this.name = name;
		type = ct;
		dataHandler = sdh;
		playable = true;
	}
	
	public void onAddToDeck(Player p) {
		
	}
	
	/**
	 * Return true if this is ethereal
	 * @param p
	 * @return
	 */
	public boolean onTurnEndInHand(Player p) {
		return false;
	}
	
	public void play(Player player, int target) throws CardFailException{
		if(!playable) {
			throw new CardFailException("Tried to play unplayable card");
		}
	}
	
	public void playUpgraded(Player player, int target) throws CardFailException{
		if(!playable) {
			throw new CardFailException("Tried to play unplayable card");
		}
	}
	
	protected void tinp() {
		if(!playable) {
			throw new CardFailException("Tried to play unplayable card");
		}
	}
	
	protected Enemy getETarget(int target) {
		if(target < dataHandler.enemies.size() && target >= 0) {
			Enemy targetedEnemy = dataHandler.enemies.get(target);
			if(targetedEnemy != null) {
				return targetedEnemy;
			}
		}
		throw new CardFailException("Invalid target");
	}
	
	protected Player getPTarget(int target) {
		if(target < dataHandler.players.size() && target >= 0) {
			Player targetedPlayer = dataHandler.players.get(target);
			if(targetedPlayer != null) {
				return targetedPlayer;
			}
		}
		throw new CardFailException("Invalid target");
		
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Card && ((Card) obj).name.equals(name) && ((Card) obj).cost == cost && ((Card) obj).exhausts == exhausts && ((Card) obj).upgraded == upgraded;
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
