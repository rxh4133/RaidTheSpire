package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class Remember extends Card {
	private static final long serialVersionUID = 1L;

	public Remember(ServerDataHandler sdh) {
		super(2, "Remember", Rarity.RARE, CardType.SKILL, sdh);
	}
	
	public Remember(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Remember", Rarity.RARE, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Discard your hand. Draw 3 (4) cards, and gain 2 (3) energy.";
		flavor = "And then clarity, cutting through the horriying fog like a beacon...";
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int target) {
		tinp();
		play.discardHand();
		play.drawCards(3);
		play.addEnergy(2);
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.discardHand();
		play.drawCards(4);
		play.addEnergy(3);
	}
	
	public Card copyCard() {
		return new Remember(dataHandler, playable, upgraded);
	}

}
