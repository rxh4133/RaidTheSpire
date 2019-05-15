package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Evasion;
import server.ServerDataHandler;

public class Evade extends Card {
	private static final long serialVersionUID = 1L;

	public Evade(ServerDataHandler sdh) {
		super(2, TP.C_T_EVADE_N, TP.C_T_EVADE_N, TP.C_T_EVADE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player p, int entityTarget, int cardTarget) {
		tinp();
		p.addSE(new Evasion(1, dataHandler, p));
	}
	
	public void playUpgraded(Player p, int entityTarget, int cardTarget) {
		tinp();
		p.addSE(new Evasion(2, dataHandler, p));
	}
}
