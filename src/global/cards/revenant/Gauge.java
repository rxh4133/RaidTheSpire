package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.statuseffects.Gauging;
import server.ServerDataHandler;

public class Gauge extends Card {
	private static final long serialVersionUID = 1L;

	public Gauge(ServerDataHandler sdh) {
		super(1, "Gauge", Rarity.UNCOMMON, CardType.ATTACK, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(2);
		play.addSE(new Gauging(1));
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(5);
		play.addSE(new Gauging(1));
	}
	
	public Card copyCard() {
		return new Gauge(dataHandler);
	}

}
