package global.card.cards.resipiscent;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.statuseffects.Strength;
import global.statuseffect.statuseffects.StrengthUp;
import global.statuseffect.statuseffects.Weak;
import server.ServerDataHandler;

public class Terrorize extends Card{
	private static final long serialVersionUID = 1L;

	public Terrorize(ServerDataHandler sdh) {
		super(2, TP.C_S_TERRORIZE_N, TP.C_S_TERRORIZE_D, TP.C_S_TERRORIZE_F, Rarity.UNCOMMON, CardType.ATTACK, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		Enemy targ = getETarget(entityTarget);
		targ.takeTrueDamage(10);
		play.damageDealtOut(10, name);
		targ.subtractSE(new Strength(3));
		targ.addSE(new StrengthUp(3));
		targ.addSE(new Weak(3));
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		Enemy targ = getETarget(entityTarget);
		targ.takeTrueDamage(15);
		play.damageDealtOut(15, name);
		targ.subtractSE(new Strength(3));
		targ.addSE(new StrengthUp(3));
		targ.addSE(new Weak(3));
	}
}
