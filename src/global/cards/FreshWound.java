package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;

public class FreshWound extends Card {
	private static final long serialVersionUID = 1L;

	public FreshWound() {
		super(1, "Fresh Wound", Rarity.STATUS, CardType.STATUS, null);
	}
	
	public void onTurnEndInHand(Player p, int index) {
		p.addCardToDraw(new Wound());
		p.exhaustFromHand(index);
	}
	
	public void prePlay(Player play, int index) {
		play.exhaustFromHand(index);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.heal(4);
	}
	
	
	public Card copyCard() {
		return new FreshWound();
	}

}
