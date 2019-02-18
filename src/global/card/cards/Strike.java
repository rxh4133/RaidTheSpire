package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.CardFailException;
import server.ServerDataHandler;

public class Strike extends Card{
	private static final long serialVersionUID = 1L;
	
	public Strike(ServerDataHandler sdh) {
		super(1, TP.C_ALL_STRIKE_N, TP.C_ALL_STRIKE_D, TP.C_ALL_STRIKE_F, Rarity.STARTER, CardType.ATTACK, true, false, sdh);
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
}
