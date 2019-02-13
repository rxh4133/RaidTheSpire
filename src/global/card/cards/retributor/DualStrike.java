package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class DualStrike extends Card{
	private static final long serialVersionUID = 1L;

	public DualStrike(ServerDataHandler sdh) {
		super(1, "Dual Strike", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public DualStrike(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Dual Strike", Rarity.COMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Do 3 (4) damage twice, then draw a card.";
		flavor = "It's not stealing if it's rightfully yours.";
	}
	
	public void play(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		dealt = getETarget(target).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
		dealt = getETarget(target).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}
	
	public Card copyCard() {
		return new DualStrike(dataHandler, playable, upgraded);
	}
}
