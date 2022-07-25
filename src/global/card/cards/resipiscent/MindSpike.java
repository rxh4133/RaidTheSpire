package global.card.cards.resipiscent;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.statuseffects.Frail;
import server.ServerDataHandler;

public class MindSpike extends Card {
	private static final long serialVersionUID = 1L;

	public MindSpike(ServerDataHandler sdh) {
		super(1, TP.C_S_MINDSPIKE_N, TP.C_S_MINDSPIKE_D, TP.C_S_MINDSPIKE_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);		
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		Enemy targ = getETarget(entityTarget);
		targ.addSE(new Frail(2));
		targ.takeTrueDamage(7);
		play.damageDealtOut(7, TP.C_S_MINDSPIKE_N);
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		Enemy targ = getETarget(entityTarget);
		targ.addSE(new Frail(2));
		targ.takeTrueDamage(9);
		play.damageDealtOut(9, TP.C_S_MINDSPIKE_N);
	}
}
