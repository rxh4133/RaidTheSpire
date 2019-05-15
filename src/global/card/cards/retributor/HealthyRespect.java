package global.card.cards.retributor;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.Respect;
import server.ServerDataHandler;

public class HealthyRespect extends Card {
	private static final long serialVersionUID = 1L;

	public HealthyRespect(ServerDataHandler sdh) {
		super(1, TP.C_T_HEALTHYRESPECT_N, TP.C_T_HEALTHYRESPECT_D, TP.C_T_HEALTHYRESPECT_F, Rarity.UNCOMMON, CardType.POWER, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.removeCardFromHand(index);
		return CardResult.REMOVE;
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.effects.add(new Respect());
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.addSE(new Respect());
	}
}
