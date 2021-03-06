package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class Numb extends Card {
	private static final long serialVersionUID = 1L;

	public Numb(ServerDataHandler sdh) {
		super(1, TP.C_V_NUMB_N, TP.C_V_NUMB_D, TP.C_V_NUMB_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(10);
		play.discardRandomCard();
		play.discardRandomCard();
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(10);
		play.discardRandomCard();
	}
}
