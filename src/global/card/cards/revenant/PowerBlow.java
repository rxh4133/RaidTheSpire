package global.card.cards.revenant;

import global.card.Card;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Vulnerable;
import server.ServerDataHandler;

public class PowerBlow extends Card {
	private static final long serialVersionUID = 1L;

	public PowerBlow(ServerDataHandler sdh) {
		super(1, TP.C_V_POWERBLOW_N, TP.C_V_POWERBLOW_D, TP.C_V_POWERBLOW_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		Enemy te = getETarget(entityTarget);
		int dealt = te.takeAttackDamage(7, play);
		play.damageDealtOut(dealt, name);
		getETarget(entityTarget).addSE(new Vulnerable(2));
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		Enemy te = getETarget(entityTarget);
		int dealt = te.takeAttackDamage(11, play);
		play.damageDealtOut(dealt, name);
		getETarget(entityTarget).addSE(new Vulnerable(2));
	}
}
