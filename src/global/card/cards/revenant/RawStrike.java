package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class RawStrike extends Card {
	private static final long serialVersionUID = 1L;

	public RawStrike(ServerDataHandler sdh) {
		super(1, "Raw Strike", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public RawStrike(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Raw Strike", Rarity.COMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Deal 15 (20) damage. Take 7 damage. Add a Fresh Wound to your hand.";
		flavor = "Think of their brain as your baby, and their face as the car on top of them.";
	}

	public void play(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(15, play);
		play.damageDealtOut(dealt, name);
		play.takeDamage(7);
		play.addCardToHand(new FreshWound());
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(20, play);
		play.damageDealtOut(dealt, name);
		play.takeDamage(7);
		play.addCardToHand(new FreshWound());
	}

	public Card copyCard() {
		return new RawStrike(dataHandler, playable, upgraded);
	}

}
