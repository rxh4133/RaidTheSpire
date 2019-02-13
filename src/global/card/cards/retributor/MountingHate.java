package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.ThornsGen;
import server.ServerDataHandler;

public class MountingHate extends Card {
	private static final long serialVersionUID = 1L;

	public MountingHate(ServerDataHandler sdh) {
		super(2, "Mounting Hate", Rarity.RARE, CardType.POWER, sdh);
	}
	
	public MountingHate(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Mounting Hate", Rarity.RARE, CardType.POWER, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "At the end of your turn, gain 2 (3) thorns.";
		flavor = "It writhes inside you...";
	}

	public void play(Player play, int target) {
		tinp();
		play.addSE(new ThornsGen(2));
	}

	public void playUpgraded(Player play, int target) {
		tinp();
		play.addSE(new ThornsGen(3));
	}
	
	public Card copyCard() {
		return new MountingHate(dataHandler, playable, upgraded);
	}
}
