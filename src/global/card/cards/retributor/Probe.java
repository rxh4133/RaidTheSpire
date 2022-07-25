package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Strength;
import server.ServerDataHandler;

public class Probe extends Card {
	private static final long serialVersionUID = 1L;

	public Probe(ServerDataHandler sdh) {
		super(1, TP.C_T_PROBE_N, TP.C_T_PROBE_D, TP.C_T_PROBE_F, Rarity.RARE, CardType.ATTACK, true, false, sdh);
	}

	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(6, play);
		play.damageDealtOut(dealt, name);
		if(dealt == 0) {
			play.addSE(new Strength(3));
		}
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		if(dealt == 0) {
			play.addSE(new Strength(4));
		}
	}
}
