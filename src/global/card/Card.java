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

	public Card(int defCost, String name, String desc, String flav, Rarity rarity, CardType ct, boolean playable, boolean upgraded, ServerDataHandler sdh) {
		defaultCost = defCost;
		cost = defCost;
		this.rarity = rarity;
		this.name = name;
		type = ct;
		dataHandler = sdh;
		this.playable = playable;
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
		if(!playable) {
			throw new CardFailException("Tried to play unplayable card");
		}
		p.discardCard(index);
		return CardResult.DISCARD;
	}
	
	public CardResult onTurnEndInHand(Player p, int index) {
		return CardResult.DISCARD;
	}
	
	protected void playLogic(Player player, int entityTarget, int cardTarget) throws CardFailException{
		
	}
	
	protected void playUpgradedLogic(Player player, int entityTarget, int cardTarget) throws CardFailException{
		playLogic(player, entityTarget, cardTarget);
	}
	
	public void play(Player player, int entityTarget, int cardTarget) {
		if(!playable) {
			throw new CardFailException("Tried to play unplayable card");
		}
		if(upgraded) {
			playUpgradedLogic(player, entityTarget, cardTarget);
		}else {
			playLogic(player, entityTarget, cardTarget);
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
	
	public Card clone() {
		try {
			return (Card) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
		return new Card(-1, "Something fucked up and this didn't get cloned right", "","", Rarity.STATUS, CardType.STATUS, false,false,dataHandler);
	}
	
	public CardType getCardType() {
		return type;
	}
	
	public String toString() {
		return cost + " " + name;
	}
	
}
