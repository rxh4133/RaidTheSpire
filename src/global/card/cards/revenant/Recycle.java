package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.card.cards.FreshMinorWound;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class Recycle extends Card {
	private static final long serialVersionUID = 1L;

	public Recycle(ServerDataHandler sdh) {
		super(0, TP.C_V_RECYCLE_N, TP.C_V_RECYCLE_D, TP.C_V_RECYCLE_F, Rarity.RARE, CardType.SKILL, true, false, sdh);
	}
	
	public void play(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.gainBlockFromCard(1);
			play.takeDamage(7);
			play.addCardToDraw(new FreshWound());
		}
	}
	
	public void playUpgraded(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.gainBlockFromCard(1);
			play.takeDamage(4);
			play.addCardToDraw(new FreshMinorWound());
		}
	}
}
