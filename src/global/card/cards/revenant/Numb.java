package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class Numb extends Card {
	private static final long serialVersionUID = 1L;

	public Numb(ServerDataHandler sdh) {
		super(1, "Numb", Rarity.COMMON, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlockFromCard(10);
		play.discardRandomCard();
		play.discardRandomCard();
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlockFromCard(10);
		play.discardRandomCard();
	}
	
	public Card copyCard() {
		return new Numb(dataHandler);
	}

}
