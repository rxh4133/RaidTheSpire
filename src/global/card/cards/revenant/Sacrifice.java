package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class Sacrifice extends Card {
	private static final long serialVersionUID = 1L;

	public Sacrifice(ServerDataHandler sdh) {
		super(1, TP.C_V_SACRIFICE_N, TP.C_V_SACRIFICE_D, TP.C_V_SACRIFICE_F, Rarity.COMMON, CardType.SKILL, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}

	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(14);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.gainBlockFromCard(20);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
}
