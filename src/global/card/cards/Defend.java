package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class Defend extends Card {
	private static final long serialVersionUID = 1L;
	
	public Defend(ServerDataHandler sdh) {
		super(1, TP.C_ALL_DEFEND_N, TP.C_ALL_DEFEND_D, TP.C_ALL_DEFEND_F, Rarity.STARTER, CardType.SKILL, true, false, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 5 block.";
		flavor = "Standard stuff.";
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		play.gainBlockFromCard(5);
	}

	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play.gainBlockFromCard(8);
	}
}
