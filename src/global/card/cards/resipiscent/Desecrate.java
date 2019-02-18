package global.card.cards.resipiscent;

import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import server.ServerDataHandler;

public class Desecrate extends Card {
	private static final long serialVersionUID = 1L;
	
	public Desecrate(ServerDataHandler sdh) {
		super(2, TP.C_S_DESECRATE_N, TP.C_S_DESECRATE_D, TP.C_S_DESECRATE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}

}
