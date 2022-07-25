package global.card.cards.resipiscent;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardResult;
import global.card.CardType;
import global.statuseffect.statuseffects.Frail;
import global.statuseffect.statuseffects.Vulnerable;
import server.ServerDataHandler;

public class Desecrate extends Card {
	private static final long serialVersionUID = 1L;
	
	public Desecrate(ServerDataHandler sdh) {
		super(2, TP.C_S_DESECRATE_N, TP.C_S_DESECRATE_D, TP.C_S_DESECRATE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}

	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		for(Enemy e: dataHandler.enemies) {
			e.addSE(new Vulnerable(3));
			e.addSE(new Frail(3));
		}
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		for(Enemy e: dataHandler.enemies) {
			e.addSE(new Vulnerable(5));
			e.addSE(new Frail(5));
		}
	}
}
