package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Thorns;
import global.statuseffect.statuseffects.ThornsDown;
import server.ServerDataHandler;

public class HatredsBite extends Card {
	private static final long serialVersionUID = 1L;

	public HatredsBite(ServerDataHandler sdh) {
		super(2, "Hatred's Bite", Rarity.UNCOMMON, CardType.SKILL, sdh);
	}
	
	public HatredsBite(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Hatred's Bite", Rarity.UNCOMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 15 (20) block, and for the rest of your turn 5 thorns.";
		flavor = "A wall of pure disdain is tough to overcome.";
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(15);
		play.addSE(new Thorns(5));
		play.addSE(new ThornsDown(5));
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(20);
		play.addSE(new Thorns(5));
		play.addSE(new ThornsDown(5));
	}
	
	public Card copyCard() {
		return new HatredsBite(dataHandler, playable, upgraded);
	}
}
