package global.card.cards;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.CardFailException;

public class Wound extends Card {
	private static final long serialVersionUID = 1L;

	public Wound() {
		super(1, TP.C_ALL_WOUND_N, TP.C_ALL_WOUND_D, TP.C_ALL_WOUND_F, Rarity.STATUS, CardType.STATUS, false, false, null);
		playable = false;
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		throw new CardFailException("Wounds are unplayable");
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		throw new CardFailException("Wounds are unplayable, and how the hell did you manage to upgrade this?");
	}
}
