package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class DualStrike extends Card{
	private static final long serialVersionUID = 1L;

	public DualStrike(ServerDataHandler sdh) {
		super(1, TP.C_T_DUALSTRIKE_N, TP.C_T_DUALSTRIKE_D, TP.C_T_DUALSTRIKE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		dealt = getETarget(entityTarget).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}

	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		int dealt = getETarget(entityTarget).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
		dealt = getETarget(entityTarget).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}
}
