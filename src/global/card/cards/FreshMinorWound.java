package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;

public class FreshMinorWound extends Card{
	private static final long serialVersionUID = 1L;

	public FreshMinorWound() {
		super(0, "Fresh Minor Wound", Rarity.STATUS, CardType.STATUS, null);
	}
	
	public void prePlay(Player play, int index) {
		play.exhaustFromHand(index);
	}
	
	public void play(Player play, int target) {
		play.heal(2);
	}
	
	public void playUpgraded(Player play, int target) {
		play(play, target);
	}
	
	public void onTurnEndInHand(Player p, int index) {
		p.addCardToDraw(new MinorWound());
		p.exhaustFromHand(index);
	}

	public Card copyCard() {
		return new FreshMinorWound();
	}
}
