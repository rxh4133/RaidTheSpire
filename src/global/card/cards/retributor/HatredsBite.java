package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Thorns;
import global.statuseffect.statuseffects.ThornsDown;
import server.ServerDataHandler;

public class HatredsBite extends Card {
	private static final long serialVersionUID = 1L;

	public HatredsBite(ServerDataHandler sdh) {
		super(2, TP.C_T_HATREDSBITE_N, TP.C_T_HATREDSBITE_D, TP.C_T_HATREDSBITE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}

	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlock(15);
		play.addSE(new Thorns(5));
		play.addSE(new ThornsDown(5));
	}

	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlock(20);
		play.addSE(new Thorns(5));
		play.addSE(new ThornsDown(5));
	}
}
