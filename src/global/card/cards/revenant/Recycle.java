package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.card.cards.FreshMinorWound;
import global.card.cards.FreshWound;
import server.ServerDataHandler;

public class Recycle extends Card {
	private static final long serialVersionUID = 1L;

	public Recycle(ServerDataHandler sdh) {
		super(0, "Recycle", Rarity.RARE, CardType.SKILL, sdh);
	}
	
	public Recycle(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(0, "Recycle", Rarity.RARE, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Choose a number X. Then, do the following X times: Draw 1 card, gain 1 block, take 7 (4) damage, and add a Fresh (Minor) Wound to your draw pile.";
		flavor = "Sometimes, ingenuity requires respurposement.";
	}
	
	public void play(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.gainBlockFromCard(1);
			play.takeDamage(7);
			play.addCardToDraw(new FreshWound());
		}
	}
	
	public void playUpgraded(Player play, int target) {
		for(int i = 0; i < target; i++) {
			play.drawCards(1);
			play.gainBlockFromCard(1);
			play.takeDamage(4);
			play.addCardToDraw(new FreshMinorWound());
		}
	}
	
	public Card copyCard() {
		return new Recycle(dataHandler, playable, upgraded);
	}

}
