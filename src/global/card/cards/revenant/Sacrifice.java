package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class Sacrifice extends Card {
	private static final long serialVersionUID = 1L;

	public Sacrifice(ServerDataHandler sdh) {
		super(1, "Sacrifice", Rarity.COMMON, CardType.SKILL, sdh);
	}
	
	public Sacrifice(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Sacrifice", Rarity.COMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 14 (20) block. Take 5 damage. Add a Fresh Wound to your draw pile. Exhaust.";
		flavor = "Is your finger bleeding too much? Just cut it off!";
	}
	
	public CardResult prePlay(Player play, int index) {
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}

	public void play(Player play, int target) {
		tinp();
		play.gainBlockFromCard(14);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlockFromCard(20);
		play.takeDamage(5);
		play.addCardToDraw(new FreshWound());
	}
	
	public Card copyCard() {
		return new Sacrifice(dataHandler, playable, upgraded);
	}
}
