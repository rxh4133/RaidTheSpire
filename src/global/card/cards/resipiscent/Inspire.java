package global.card.cards.resipiscent;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import server.ServerDataHandler;

public class Inspire extends Card {
	private static final long serialVersionUID = 1L;

	public Inspire(ServerDataHandler sdh) {
		super(-1, TP.C_S_INSPIRE_N, TP.C_S_INSPIRE_D, TP.C_S_INSPIRE_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		Player targ = getPTarget(entityTarget);
		targ.addEnergy(cost + 1);
	}

	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		Player targ = getPTarget(entityTarget);
		targ.addEnergy(cost + 2);
	}
}
