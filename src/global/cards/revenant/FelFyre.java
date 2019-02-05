package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.cards.FreshMinorWound;
import server.CardFailException;
import server.ServerDataHandler;

public class FelFyre extends Card {
	private static final long serialVersionUID = 1L;

	public FelFyre(ServerDataHandler sdh) {
		super(2, "Fel Fyre", Rarity.STARTER, CardType.ATTACK, sdh);
	}
	
	public void play(Player p, int target) {
		if(target < dataHandler.enemies.size()) {
			Enemy targetedEnemy = dataHandler.enemies.get(target);
			if(targetedEnemy != null) {
				int dealt = targetedEnemy.takeAttackDamage(16 + p.getStrength(), p);
				p.damageDealtOut(dealt, name);
				p.takeDamage(3);
				p.addCardToDraw(new FreshMinorWound());
			}
		}else {
			throw new CardFailException();
		}
	}
	
	public void playUpgraded(Player p, int target) {
		if(target < dataHandler.enemies.size()) {
			Enemy targetedEnemy = dataHandler.enemies.get(target);
			if(targetedEnemy != null) {
				int dealt = targetedEnemy.takeAttackDamage(20 + p.getStrength(), p);
				p.damageDealtOut(dealt, name);
				p.takeDamage(3);
				p.addCardToDraw(new FreshMinorWound());
			}
		}else {
			throw new CardFailException();
		}
	}
	
	public Card copyCard() {
		return new FelFyre(dataHandler);
	}

}
