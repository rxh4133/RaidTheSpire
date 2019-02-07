package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.statuseffects.Thorns;
import global.statuseffects.ThornsDown;
import server.ServerDataHandler;

public class HatredsBite extends Card {
	private static final long serialVersionUID = 1L;

	public HatredsBite(ServerDataHandler sdh) {
		super(2, "Hatred's Bite", Rarity.UNCOMMON, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(15 + play.getDex());
		play.addSE(new Thorns(5, play));
		play.addSE(new ThornsDown(5));
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(20 + play.getDex());
		play.addSE(new Thorns(5, play));
		play.addSE(new ThornsDown(5));
	}
	
	public Card copyCard() {
		return new HatredsBite(dataHandler);
	}
}
