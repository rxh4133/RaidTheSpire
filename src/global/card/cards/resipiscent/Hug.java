package global.card.cards.resipiscent;

import java.util.ArrayList;
import java.util.Collections;

import global.Player;
import global.Rarity;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.StatusEffect;
import server.CardFailException;
import server.ServerDataHandler;

public class Hug extends Card {
	private static final long serialVersionUID = 1L;

	public Hug(ServerDataHandler sdh) {
		super(2, "Hug", Rarity.STARTER, CardType.SKILL, true, false, sdh);
	}
	
	public Hug(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Hug", Rarity.STARTER, CardType.SKILL, play, upgr, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		int playerDex = dataHandler.players.indexOf(play);
		if(target == playerDex + 1 || target == playerDex - 1) {
			Player targ = getPTarget(target);
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
	
	public void playUpgraded(Player play, int target) {
		play(play, target);
	}
	
	public Card copyCard() {
		return new Hug(dataHandler, playable, upgraded);
	}

}
