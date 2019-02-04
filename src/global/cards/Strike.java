package global.cards;

import global.Card;
import global.Enemy;
import global.Player;
import server.CardFailException;
import server.ServerDataHandler;

public class Strike extends Card{
	private static final long serialVersionUID = 1L;

	public Strike(ServerDataHandler sdh) {
		super(1, "Strike", 0, sdh);
	}

	public void play(Player play, int target) throws CardFailException {
		if(target < dataHandler.enemies.size()) {
			Enemy targetedEnemy = dataHandler.enemies.get(target);
			if(targetedEnemy != null) {
				int dealt = targetedEnemy.takeAttackDamage(6 + play.getStrength(), play);
				play.damageDealtOut(dealt, name);
			}
		}else {
			throw new CardFailException();
		}
	}

	public void playUpgraded(Player play, int target) throws CardFailException {
		if(target < dataHandler.enemies.size()) {
			Enemy targetedEnemy = dataHandler.enemies.get(target);
			if(targetedEnemy != null) {
				int dealt = targetedEnemy.takeAttackDamage(9 + play.getStrength(), play);
				play.damageDealtOut(dealt, name);
			}
		}else {
			throw new CardFailException();
		}
	}

	public Card copyCard() {
		return new Strike(dataHandler);
	}
}
