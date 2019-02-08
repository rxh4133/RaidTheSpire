package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;

public class FreshMinorWound extends Card{
	private static final long serialVersionUID = 1L;

	public FreshMinorWound() {
		super(0, "Fresh Minor Wound", Rarity.STATUS, CardType.STATUS, null);
		exhausts = true;
	}
	
	public void play(Player play, int target) {
		play.heal(2);
	}
	
	public void playUpgraded(Player play, int target) {
		play(play, target);
	}
	
	public boolean onTurnEndInHand(Player p) {
		p.addCardToDraw(new MinorWound());
		p.removeCardFromHand(this);
		return true;
	}

	public Card copyCard() {
		return new FreshMinorWound();
	}
}
