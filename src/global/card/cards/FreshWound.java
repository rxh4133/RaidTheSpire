package global.card.cards;

import global.card.CardResult;
import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;

public class FreshWound extends Card {
	private static final long serialVersionUID = 1L;

	public FreshWound() {
		super(1, TP.C_ALL_FRESHWOUND_N, TP.C_ALL_FRESHWOUND_D, TP.C_ALL_FRESHWOUND_F, Rarity.STATUS, CardType.STATUS, true, false, null);
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
}
