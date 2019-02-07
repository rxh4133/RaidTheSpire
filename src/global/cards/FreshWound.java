package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;

public class FreshWound extends Card {
	private static final long serialVersionUID = 1L;

	public FreshWound() {
		super(1, "Fresh Wound", Rarity.STATUS, CardType.STATUS, null);
		exhausts = true;
	}
	
	public boolean onTurnEndInHand(Player p) {
		p.addCardToDraw(new Wound());
		return true;
	}
	
	public void play(Player play, int target) {
		play.heal(4);
	}
	
	
	public Card copyCard() {
		return new FreshWound();
	}

}
