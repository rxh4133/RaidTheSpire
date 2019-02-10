package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.MaxMissing;
import server.ServerDataHandler;

public class Salvage extends Card {
	private static final long serialVersionUID = 1L;

	public Salvage(ServerDataHandler sdh) {
		super(2, "Salvage", Rarity.UNCOMMON, CardType.SKILL, sdh);
	}
	
	public void afterPlay(Player play, int index) {
		play.exhaustFromHand(index);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(20, play));
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(15, play));
	}
	
	public Card copyCard() {
		return new Salvage(dataHandler);
	}

}
