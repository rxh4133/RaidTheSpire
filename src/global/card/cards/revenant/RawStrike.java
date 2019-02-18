package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class RawStrike extends Card {
	private static final long serialVersionUID = 1L;

	public RawStrike(ServerDataHandler sdh) {
		super(1, TP.C_V_RAWSTRIKE_N, TP.C_V_RAWSTRIKE_D, TP.C_V_RAWSTRIKE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}

	public void play(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(15, play);
		play.damageDealtOut(dealt, name);
		play.takeDamage(7);
		play.addCardToHand(new FreshWound());
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(20, play);
		play.damageDealtOut(dealt, name);
		play.takeDamage(7);
		play.addCardToHand(new FreshWound());
	}
}
