package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.cards.FreshMinorWound;
import server.ServerDataHandler;

public class FelFyre extends Card {
	private static final long serialVersionUID = 1L;

	public FelFyre(ServerDataHandler sdh) {
		super(2, "Fel Fyre", Rarity.STARTER, CardType.ATTACK, sdh);
	}

	public void play(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(16 + p.getStrength(), p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(3);
		p.addCardToDraw(new FreshMinorWound());

	}

	public void playUpgraded(Player p, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(20 + p.getStrength(), p);
		p.damageDealtOut(dealt, name);
		p.takeDamage(3);
		p.addCardToDraw(new FreshMinorWound());
	}

	public Card copyCard() {
		return new FelFyre(dataHandler);
	}

}
