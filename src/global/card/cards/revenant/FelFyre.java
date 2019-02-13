package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.card.cards.FreshMinorWound;
import server.ServerDataHandler;

public class FelFyre extends Card {
	private static final long serialVersionUID = 1L;

	public FelFyre(ServerDataHandler sdh) {
		super(2, "Fel Fyre", Rarity.STARTER, CardType.ATTACK, sdh);
	}
	
	public FelFyre(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Fel Fyre", Rarity.STARTER, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Deal 16 (20) damage. Take 4 damage, and shuffle a Fresh Minor Wound into your draw pile.";
		flavor = "The only warmth you feel is burning.";
	}

	public void play(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(16, p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(4);
		p.addCardToDraw(new FreshMinorWound());

	}

	public void playUpgraded(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(20, p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(4);
		p.addCardToDraw(new FreshMinorWound());
	}

	public Card copyCard() {
		return new FelFyre(dataHandler, playable, upgraded);
	}

}
