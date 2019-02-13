package global.relic.relics;

import java.util.ArrayList;

import global.Player;
import global.Rarity;
import global.card.Card;
import global.card.cards.ShipLoadOfStrikes;
import global.card.cards.Strike;
import global.relic.Relic;
import server.ServerDataHandler;

public class CorrugatedShipper extends Relic{
	private static final long serialVersionUID = 1L;

	public CorrugatedShipper() {
		super("Corrugated Shipper", Rarity.RARE);
		description = "All strikes are now unplayable. Add a Ship Load of Strikes to your deck.";
		flavor = "Wait, can you send that back? We accidentally shipped you one too many!";
	}
	
	public Relic copyRelic() {
		return new CorrugatedShipper();
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
