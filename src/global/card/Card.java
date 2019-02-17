package global.card;

import java.io.Serializable;

import global.Enemy;
import global.Player;
import global.Rarity;
import server.CardFailException;
import server.ServerDataHandler;

public class Card implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	public int cost;
	public final int defaultCost;
	
	public String name;
	public Rarity rarity;
	private CardType type;
	
	protected String description;
	protected String flavor;
	
	protected transient ServerDataHandler dataHandler;
	public boolean upgraded;
	public boolean playable;
	public boolean retain;

	public Card(int defCost, String name, String desc, String flav, Rarity rarity, CardType ct, boolean playable, boolean upgraded, boolean retains, ServerDataHandler sdh) {
		defaultCost = defCost;
		cost = defCost;
		this.rarity = rarity;
		this.name = name;
		type = ct;
		dataHandler = sdh;
		this.playable = playable;
		retain = retains;
		this.upgraded = upgraded;
		description = desc;
		flavor = flav;
	}
	
	public String getDesc() {
		return description;
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public void setTextStuff() {
		
	}
	
	public void onAddToDeck(Player p) {
		
	}
	
	public void onUpgrade() {
		
	}
	
	public CardResult prePlay(Player p, int index) {
		tinp();
		p.discardCard(index);
		return CardResult.DISCARD;
	}
	
	public CardResult onTurnEndInHand(Player p, int index) {
		if(retain) {
			return CardResult.RETAIN;
		}
		return CardResult.DISCARD;
	}
	
	public void play(Player player, int target) throws CardFailException{
		tinp();
	}
	
	public void playUpgraded(Player player, int target) throws CardFailException{
		tinp();
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
		return obj instanceof Card && ((Card) obj).name.equals(name) && ((Card) obj).cost == cost && ((Card) obj).upgraded == upgraded;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return new Card(-1, "Something fucked up and this didn't get cloned right", "","", Rarity.STATUS, CardType.STATUS, false,false,false,dataHandler);
	}
	
	public CardType getCardType() {
		return type;
	}
	
	public String toString() {
		return cost + " " + name;
	}
	
}
