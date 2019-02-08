package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.ServerDataHandler;

public class Defend extends Card {
	private static final long serialVersionUID = 1L;

	public Defend(ServerDataHandler sdh) {
		super(1, "Defend", Rarity.STARTER, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(5);
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(8);
	}
	
	public Card copyCard() {
		return new Defend(dataHandler);
	}
}
