package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Gauging;
import server.ServerDataHandler;

public class Gauge extends Card {
	private static final long serialVersionUID = 1L;

	public Gauge(ServerDataHandler sdh) {
		super(1, "Gauge", Rarity.UNCOMMON, CardType.ATTACK, sdh);
	}
	
	public Gauge(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Gauge", Rarity.UNCOMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 2 (5) block. If you still have block remaining at the start of your next turn, gain 3 dexterity.";
		flavor = "Compliment your opponent on his technique. When he tries it again, bash his skull in.";
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlockFromCard(2);
		play.addSE(new Gauging(1));
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlockFromCard(5);
		play.addSE(new Gauging(1));
	}
	
	public Card copyCard() {
		return new Gauge(dataHandler, playable, upgraded);
	}

}
