package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class Cannibalize extends Card{
	private static final long serialVersionUID = 1L;

	public Cannibalize(ServerDataHandler sdh) {
		super(1, TP.C_V_CANNIBALIZE_N, TP.C_V_CANNIBALIZE_D, TP.C_V_CANNIBALIZE_F, Rarity.RARE, CardType.ATTACK, true, false, sdh);
	}

	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		play.addMaxHealth(5);
		int dealt = getETarget(entityTarget).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play.addMaxHealth(5);
		int dealt = getETarget(entityTarget).takeAttackDamage(8, play);
		play.damageDealtOut(dealt, name);
	}
}
