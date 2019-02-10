package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.card.cards.FreshMinorWound;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class Recycle extends Card {
	private static final long serialVersionUID = 1L;

	public Recycle(ServerDataHandler sdh) {
		super(0, "Recycle", Rarity.RARE, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.takeDamage(7);
			play.addCardToDraw(new FreshWound());
		}
	}
	
	public void playUpgraded(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.takeDamage(4);
			play.addCardToDraw(new FreshMinorWound());
		}
	}
	
	public Card copyCard() {
		return new Recycle(dataHandler);
	}

}
