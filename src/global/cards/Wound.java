package global.cards;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.CardFailException;

public class Wound extends Card {
	private static final long serialVersionUID = 1L;

	public Wound() {
		super(1, "Wound", Rarity.STATUS, CardType.STATUS, null);
		playable = false;
	}
	
	public void play(Player play, int target) {
		throw new CardFailException("Wounds are unplayable");
	}
	
	public void playUpgraded(Player play, int target) {
		throw new CardFailException("Wounds are unplayable, and how the hell did you manage to upgrade this?");
	}
	
	public Card copyCard() {
		return new Wound();
	}

}
