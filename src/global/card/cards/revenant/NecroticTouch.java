package global.card.cards.revenant;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardResult;
import global.card.CardType;
import global.statuseffect.statuseffects.Necrotic;
import global.statuseffect.statuseffects.Poison;
import server.ServerDataHandler;

public class NecroticTouch extends Card {
	private static final long serialVersionUID = 1L;

	public NecroticTouch(ServerDataHandler sdh) {
		super(2, TP.C_V_NECROTICTOUCH_N, TP.C_V_NECROTICTOUCH_D, TP.C_V_NECROTICTOUCH_F, Rarity.RARE, CardType.POWER, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.removeCardFromHand(index);
		return CardResult.REMOVE;
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		Enemy e = getETarget(entityTarget);
		e.addSE(new Necrotic(1));
		e.addSE(new Poison(10));
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		Enemy e = getETarget(entityTarget);
		e.addSE(new Necrotic(1));
		e.addSE(new Poison(14));
	}
}
