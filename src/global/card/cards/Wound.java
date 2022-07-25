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
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		throw new CardFailException("Wounds are unplayable");
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		throw new CardFailException("Wounds are unplayable, and how the hell did you manage to upgrade this?");
	}
}
