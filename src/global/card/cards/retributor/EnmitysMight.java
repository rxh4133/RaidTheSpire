package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Thorns;
import server.CardFailException;
import server.ServerDataHandler;

public class EnmitysMight extends Card {
	private static final long serialVersionUID = 1L;

	public EnmitysMight(ServerDataHandler sdh) {
		super(0, TP.C_T_ENMITYSMIGHT_N, TP.C_T_ENMITYSMIGHT_D, TP.C_T_ENMITYSMIGHT_F, Rarity.STARTER, CardType.SKILL, true, false, sdh);
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) throws CardFailException{
		getPTarget(entityTarget).addSE(new Thorns(2));
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) throws CardFailException{
		getPTarget(entityTarget).addSE(new Thorns(3));
	}
}
