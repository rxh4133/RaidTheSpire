package global.cards;

import global.Card;
import global.CardType;
import global.Rarity;

public class MinorWound extends Card {
	private static final long serialVersionUID = 1L;

	public MinorWound() {
		super(0, "Minor Wound", Rarity.STATUS, CardType.STATUS, null);
		exhausts = true;
	}

}
