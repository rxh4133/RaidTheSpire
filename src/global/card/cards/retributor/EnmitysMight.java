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
	
	public void play(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(2));
	}
	
	public void playUpgraded(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(3));
	}
}
