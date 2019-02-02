package global.cards;

import global.Card;
import global.Player;
import server.ServerDataHandler;

public class Defend extends Card {
	private static final long serialVersionUID = 1L;

	public Defend(ServerDataHandler sdh) {
		super(1, "Defend", 0, sdh);
	}
	
	public void play(Player play, int target) {
		play.gainBlock(5 + play.getDex());
	}

	public void playUpgraded(Player play, int target) {
		play.gainBlock(8 + play.getDex());
	}
	
	public Card copyCard() {
		return new Defend(dataHandler);
	}
}
