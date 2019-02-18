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

public class Weave extends Card {
	private static final long serialVersionUID = 1L;

	public Weave(ServerDataHandler sdh) {
		super(2, TP.C_S_WEAVE_N, TP.C_S_WEAVE_D, TP.C_S_WEAVE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		Player targ = getPTarget(target);
		if(play.equals(targ)) {
			throw new CardFailException("Target cannot be yourself");
		}
		ArrayList<Player> recievers = new ArrayList<Player>();
		recievers.addAll(dataHandler.players);
		recievers.remove(play);
		recievers.remove(targ);
		if(recievers.size() == 0) {
			throw new CardFailException("No valid random reciever");
		}
		Collections.shuffle(recievers);
		for(StatusEffect se: targ.effects) {
			if(se.isBuff()) {
				try {
					recievers.get(0).addSE((StatusEffect) se.clone());
				} catch (CloneNotSupportedException e) {
					System.out.println("A buff was non-cloneable. ???");
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
