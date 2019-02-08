package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;

public class MinorWound extends Card {
	private static final long serialVersionUID = 1L;

	public MinorWound() {
		super(0, "Minor Wound", Rarity.STATUS, CardType.STATUS, null);
	}
	
	public void prePlay(Player play, int index) {
		play.exhaustFromHand(index);
	}

	public Card copyCard() {
		return new MinorWound();
	}
}
