package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class Defend extends Card {
	private static final long serialVersionUID = 1L;

	public Defend(ServerDataHandler sdh) {
		super(1, "Defend", Rarity.STARTER, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlockFromCard(5);
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlockFromCard(8);
	}
	
	public Card copyCard() {
		return new Defend(dataHandler);
	}
}
