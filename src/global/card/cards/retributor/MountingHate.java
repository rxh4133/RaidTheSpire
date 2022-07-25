package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.ThornsGen;
import server.ServerDataHandler;

public class MountingHate extends Card {
	private static final long serialVersionUID = 1L;

	public MountingHate(ServerDataHandler sdh) {
		super(2, TP.C_T_MOUNTINGHATE_N, TP.C_T_MOUNTINGHATE_D, TP.C_T_MOUNTINGHATE_F, Rarity.RARE, CardType.POWER, true, false, sdh);
	}

	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		play.addSE(new ThornsGen(2));
	}

	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play.addSE(new ThornsGen(3));
	}
}
