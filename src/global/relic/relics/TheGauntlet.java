package global.relic.relics;

import java.util.ArrayList;

import global.Player;
import global.Rarity;
import global.card.Card;
import global.relic.Relic;
import server.ServerDataHandler;

public class TheGauntlet extends Relic{
	private static final long serialVersionUID = 1L;

	public TheGauntlet() {
		super("The Gauntlet", Rarity.RARE);
		description = "On pickup, remove half of your deck at random. Upgrade the other half.";
		flavor = "With a snap of my fingers, I decide the fate of your deck.";
	}
	
	public Relic copyRelic() {
		return new TheGauntlet();
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		ArrayList<Card> ownerCards = owner.getDeck();
		ArrayList<Card> chosenCards = new ArrayList<Card>();
		for(Card c: ownerCards) {
			if(Math.random() < .5) {
				Card chosen = c.copyCard();
				chosen.upgraded = true;
				chosen.onUpgrade();
				chosenCards.add(chosen);
			}
		}
		owner.setDeck(chosenCards);
		return this;
	}
}
