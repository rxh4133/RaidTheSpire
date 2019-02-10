package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class Remember extends Card {
	private static final long serialVersionUID = 1L;

	public Remember(ServerDataHandler sdh) {
		super(2, "Remember", Rarity.RARE, CardType.SKILL, sdh);
	}
	
	public void prePlay(Player play, int index) {
		play.exhaustFromHand(index);
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
	
	public Card copyCard() {
		return new Remember(dataHandler);
	}

}
