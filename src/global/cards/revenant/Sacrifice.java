package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.cards.FreshWound;
import server.ServerDataHandler;

public class Sacrifice extends Card {
	private static final long serialVersionUID = 1L;

	public Sacrifice(ServerDataHandler sdh) {
		super(1, "Sacrifice", Rarity.COMMON, CardType.SKILL, sdh);
	}

	public void play(Player play, int target) {
		tinp();
		play.gainBlock(14);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(20);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
	
	public Card copyCard() {
		return new Sacrifice(dataHandler);
	}
}
