package global.card.cards;

import global.card.CardResult;
import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;

public class FreshMinorWound extends Card{
	private static final long serialVersionUID = 1L;

	public FreshMinorWound() {
		super(0, TP.C_ALL_FRESHMINORWOUND_N, TP.C_ALL_FRESHMINORWOUND_D, TP.C_ALL_FRESHMINORWOUND_F, Rarity.STATUS, CardType.STATUS, true, false, null);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int target) {
		play.heal(2);
	}
	
	public void playUpgraded(Player play, int target) {
		play(play, target);
	}
	
	public CardResult onTurnEndInHand(Player p, int index) {
		p.addCardToDraw(new MinorWound());
		return CardResult.EXHAUST;
	}
}
