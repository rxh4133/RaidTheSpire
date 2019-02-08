package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.ServerDataHandler;

public class Numb extends Card {
	private static final long serialVersionUID = 1L;

	public Numb(ServerDataHandler sdh) {
		super(1, "Numb", Rarity.COMMON, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(10);
		play.discardRandomCard();
		play.discardRandomCard();
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(10);
		play.discardRandomCard();
	}
	
	public Card copyCard() {
		return new Numb(dataHandler);
	}

}
