package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.CardFailException;
import server.ServerDataHandler;

public class Strike extends Card{
	private static final long serialVersionUID = 1L;

	public void setTextStuff() {
		description = "Do 6 (9) damage.";
		flavor = "Standard stuff.";
	}
	
	public Strike(ServerDataHandler sdh) {
		super(1, "Strike", Rarity.STARTER, CardType.ATTACK, sdh);
	}
	
	public Strike(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Strike", Rarity.STARTER, CardType.ATTACK, play, upgr, sdh);
	}

	public void play(Player play, int target) throws CardFailException {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(6, play);
		play.damageDealtOut(dealt, name);
	}

	public void playUpgraded(Player play, int target) throws CardFailException {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(9, play);
		play.damageDealtOut(dealt, name);
	}

	public Card copyCard() {
		return new Strike(dataHandler, playable, upgraded);
	}
}
