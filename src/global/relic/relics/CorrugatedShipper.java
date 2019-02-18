package global.relic.relics;

import java.util.ArrayList;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.cards.ShipLoadOfStrikes;
import global.card.cards.Strike;
import global.relic.Relic;
import server.ServerDataHandler;

public class CorrugatedShipper extends Relic{
	private static final long serialVersionUID = 1L;

	public CorrugatedShipper() {
		super(TP.R_ALL_CORRUGATEDSHIPPER_N, TP.R_ALL_CORRUGATEDSHIPPER_D, TP.R_ALL_CORRUGATEDSHIPPER_F, Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		ArrayList<Card> ownerCards = owner.getDeck();
		for(Card c: ownerCards) {
			if(c instanceof Strike) {
				c.playable = false;
			}
		}
		ownerCards.add(new ShipLoadOfStrikes(sdh));
		owner.setDeck(ownerCards);
		return this;
	}

}
