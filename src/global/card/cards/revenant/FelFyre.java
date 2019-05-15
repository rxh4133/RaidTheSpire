package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.card.cards.FreshMinorWound;
import server.ServerDataHandler;

public class FelFyre extends Card {
	private static final long serialVersionUID = 1L;

	public FelFyre(ServerDataHandler sdh) {
		super(2, TP.C_V_FELFYRE_N, TP.C_V_FELFYRE_D, TP.C_V_FELFYRE_F, Rarity.STARTER, CardType.ATTACK, true, false, sdh);
	}

	public void play(Player p, int entityTarget, int cardTarget) {
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(16, p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(4);
		p.addCardToDraw(new FreshMinorWound());

	}

	public void playUpgraded(Player p, int entityTarget, int cardTarget) {
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(20, p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(4);
		p.addCardToDraw(new FreshMinorWound());
	}
}
