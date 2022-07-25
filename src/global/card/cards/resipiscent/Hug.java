package global.card.cards.resipiscent;

import java.util.ArrayList;
import java.util.Collections;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.StatusEffect;
import server.CardFailException;
import server.ServerDataHandler;

public class Hug extends Card {
	private static final long serialVersionUID = 1L;
	
	public Hug(ServerDataHandler sdh) {
		super(2, TP.C_S_HUG_N, TP.C_S_HUG_D, TP.C_S_HUG_F, Rarity.STARTER, CardType.SKILL, true, false, sdh);
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		int playerDex = dataHandler.players.indexOf(play);
		if(entityTarget == playerDex + 1 || entityTarget == playerDex - 1) {
			Player targ = getPTarget(entityTarget);
			ArrayList<StatusEffect> ses = new ArrayList<StatusEffect>();
			ses.addAll(targ.effects);
			Collections.shuffle(ses);
			for(StatusEffect se: ses) {
				if(se.isDebuff()) {
					targ.removeSE(se);
					break;
				}
			}
		}else {
			throw new CardFailException("Target player not adjacent");
		}
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play(play, entityTarget, -1);
	}
}
