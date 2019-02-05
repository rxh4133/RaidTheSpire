package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.statuseffects.ThornsGen;
import server.ServerDataHandler;

public class MountingHate extends Card {
	private static final long serialVersionUID = 1L;

	public MountingHate(ServerDataHandler sdh) {
		super(2, "Mounting Hate", Rarity.RARE, CardType.POWER, sdh);
	}

	public void play(Player play, int target) {
		play.addSE(new ThornsGen(2));
	}

	public void playUpgraded(Player play, int target) {
		play.addSE(new ThornsGen(3));
	}
	
	public Card copyCard() {
		return new MountingHate(dataHandler);
	}
}
