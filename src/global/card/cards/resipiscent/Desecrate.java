package global.card.cards.resipiscent;

import global.Rarity;
import global.card.Card;
import global.card.CardType;
import server.ServerDataHandler;

public class Desecrate extends Card {
	private static final long serialVersionUID = 1L;

	public Desecrate(ServerDataHandler sdh) {
		super(2, "Desecrate", Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public Desecrate(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Desecrate", Rarity.UNCOMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public Card copyCard() {
		return new Desecrate(dataHandler, playable, upgraded);
	}

}
