package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class Remember extends Card {
	private static final long serialVersionUID = 1L;

	public Remember(ServerDataHandler sdh) {
		super(2, TP.C_V_REMEMBER_N, TP.C_V_REMEMBER_D, TP.C_V_REMEMBER_F, Rarity.RARE, CardType.SKILL, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int target) {
		tinp();
		play.discardHand();
		play.drawCards(3);
		play.addEnergy(2);
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.discardHand();
		play.drawCards(4);
		play.addEnergy(3);
	}
}
