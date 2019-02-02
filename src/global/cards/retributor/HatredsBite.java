package global.cards.retributor;

import global.Card;
import global.Player;
import global.statuseffects.Thorns;
import global.statuseffects.ThornsDown;
import server.ServerDataHandler;

public class HatredsBite extends Card {
	private static final long serialVersionUID = 1L;

	public HatredsBite(ServerDataHandler sdh) {
		super(2, "Hatred's Bite", 2, sdh);
	}
	
	public void play(Player play, int target) {
		play.gainBlock(15 + play.getDex());
		Thorns toApply = new Thorns(5);
		play.addSE(toApply);
		play.addAttDamSub(toApply);
		play.addSE(new ThornsDown(5));
	}

	public void playUpgraded(Player play, int target) {
		play.gainBlock(20 + play.getDex());
		Thorns toApply = new Thorns(5);
		play.addSE(toApply);
		play.addAttDamSub(toApply);
		play.addSE(new ThornsDown(5));
	}
	
	public Card copyCard() {
		return new HatredsBite(dataHandler);
	}
}
