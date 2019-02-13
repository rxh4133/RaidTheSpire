package global.enemyactions;

import global.Enemy;
import global.EnemyAction;
import global.Player;
import server.ServerDataHandler;

public class AOEAttack extends EnemyAction{
	private static final long serialVersionUID = 1L;
	private int damage;

	public AOEAttack(Enemy e, ServerDataHandler sdh, int damage) {
		super(e, "AOEAttack", sdh);
		this.damage = damage;
	}
	
	public void doAction() {
		for(Player p: dataHandler.players) {
			int taken = p.takeAttackDamage(damage, enemy);
			enemy.damageDealtOut(taken, name);
		}
	}

}
