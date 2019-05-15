package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Gauging;
import server.ServerDataHandler;

public class Gauge extends Card {
	private static final long serialVersionUID = 1L;

	public Gauge(ServerDataHandler sdh) {
		super(1, TP.C_V_GAUGE_N, TP.C_V_GAUGE_D, TP.C_V_GAUGE_F, Rarity.UNCOMMON, CardType.ATTACK, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(2);
		play.addSE(new Gauging(1));
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(5);
		play.addSE(new Gauging(1));
	}
}
