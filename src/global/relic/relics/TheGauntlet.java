package global.relic.relics;

import java.util.ArrayList;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.relic.Relic;
import server.ServerDataHandler;

public class TheGauntlet extends Relic{
	private static final long serialVersionUID = 1L;

	public TheGauntlet() {
		super(TP.R_ALL_THEGAUNTLET_N, TP.R_ALL_THEGAUNTLET_D, TP.R_ALL_THEGAUNTLET_F, Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		ArrayList<Card> ownerCards = owner.getDeck();
		ArrayList<Card> chosenCards = new ArrayList<Card>();
		for(Card c: ownerCards) {
			if(Math.random() < .5) {
				Card chosen = c.clone();
				chosen.upgraded = true;
				chosen.onUpgrade();
				chosenCards.add(chosen);
			}
		}
		owner.setDeck(chosenCards);
		return this;
	}
}
