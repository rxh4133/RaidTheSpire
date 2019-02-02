package global.cards;

import global.Card;
import global.Enemy;
import global.Player;
import server.ServerDataHandler;

public class Strike extends Card{
	private static final long serialVersionUID = 1L;

	public Strike(ServerDataHandler sdh) {
		super(1, "Strike", 0, sdh);
	}
	
	public void play(Player play, int target) {
		System.out.println("striking");
		Enemy targetedEnemy = dataHandler.enemies.get(target);
		if(targetedEnemy != null) {
			targetedEnemy.takeAttackDamage(6 + play.getStrength(), play);
		}
	}
	
	public void playUpgraded(Player play, int target) {
		Enemy targetedEnemy = dataHandler.enemies.get(target);
		if(targetedEnemy != null) {
			targetedEnemy.takeAttackDamage(9 + play.getStrength(), play);
		}
	}
	
	public Card copyCard() {
		return new Strike(dataHandler);
	}
}
