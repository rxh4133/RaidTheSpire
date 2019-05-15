package global.card.cards.resipiscent;

import java.util.ArrayList;
import java.util.Collections;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import server.CardFailException;
import server.ServerDataHandler;

public class Shift extends Card {
	private static final long serialVersionUID = 1L;

	public Shift(ServerDataHandler sdh) {
		super(1, TP.C_S_SHIFT_N, TP.C_S_SHIFT_D, TP.C_S_SHIFT_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player player, int entityTarget, int cardTarget) {
		tinp();
		Player targ = getPTarget(entityTarget);
		ArrayList<Player> recievers = new ArrayList<Player>();
		recievers.addAll(dataHandler.players);
		recievers.remove(targ);
		if(recievers.size() == 0) {
			throw new CardFailException("Must have a player to move debuffs to.");
		}
		Collections.shuffle(recievers);
		for(int i = 0; i < targ.getEffects().size(); i++) {
			if(targ.getEffects().get(i).isDebuff()) {
				recievers.get(0).getEffects().add(targ.getEffects().get(i));
				targ.getEffects().remove(i);
				i--;
			}
		}
	}
	
	public void playUpgraded(Player player, int entityTarget, int cardTarget) {
		play(player, entityTarget, -1);
	}

}
