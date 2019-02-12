package global.card.cards;

import global.card.CardResult;
import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;

public class FreshWound extends Card {
	private static final long serialVersionUID = 1L;

	public FreshWound() {
		super(1, "Fresh Wound", Rarity.STATUS, CardType.STATUS, null);
	}
	
	public CardResult onTurnEndInHand(Player p, int index) {
		p.addCardToDraw(new Wound());
		return CardResult.EXHAUST;
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int target) {
		tinp();
		play.heal(4);
	}
	
	
	public Card copyCard() {
		return new FreshWound();
	}

}
