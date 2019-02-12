package global.card.cards;

import global.card.CardResult;
import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;

public class FreshMinorWound extends Card{
	private static final long serialVersionUID = 1L;

	public FreshMinorWound() {
		super(0, "Fresh Minor Wound", Rarity.STATUS, CardType.STATUS, null);
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

	public Card copyCard() {
		return new FreshMinorWound();
	}
}
