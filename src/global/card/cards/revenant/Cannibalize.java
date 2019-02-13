package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class Cannibalize extends Card{

	private static final long serialVersionUID = 1L;

	public Cannibalize(ServerDataHandler sdh) {
		super(1, "Cannibalize", Rarity.RARE, CardType.ATTACK, sdh);
	}
	
	public Cannibalize(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Cannibalize", Rarity.RARE, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 5 max hp. Deal 4 (8) damage.";
		flavor = "HUNGRY.";
	}

	public void play(Player play, int target) {
		play.addMaxHealth(5);
		int dealt = getETarget(target).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
	}
	
	public void playUpgraded(Player play, int target) {
		play.addMaxHealth(5);
		int dealt = getETarget(target).takeAttackDamage(8, play);
		play.damageDealtOut(dealt, name);
	}
	
	public Card copyCard() {
		return new Cannibalize(dataHandler, playable, upgraded);
	}
}
