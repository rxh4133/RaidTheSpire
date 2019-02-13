package global.card.cards;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.card.CardType;

public class MinorWound extends Card {
	private static final long serialVersionUID = 1L;

	public MinorWound() {
		super(0, "Minor Wound", Rarity.STATUS, CardType.STATUS, null);
	}
	
	public void setTextStuff() {
		description = "Do nothing. Exhaust.";
		flavor = "Anyone got a band-aid?";
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}

	public Card copyCard() {
		return new MinorWound();
	}
}
