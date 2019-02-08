package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.CardFailException;
import server.ServerDataHandler;

public class Strike extends Card{
	private static final long serialVersionUID = 1L;

	public Strike(ServerDataHandler sdh) {
		super(1, "Strike", Rarity.STARTER, CardType.ATTACK, sdh);
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
		return new Strike(dataHandler);
	}
}
