package global.card.cards;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;

public class MinorWound extends Card {
	private static final long serialVersionUID = 1L;

	public MinorWound() {
		super(0, TP.C_ALL_MINORWOUND_N, TP.C_ALL_MINORWOUND_D, TP.C_ALL_MINORWOUND_F, Rarity.STATUS, CardType.STATUS, true, false, null);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
}
