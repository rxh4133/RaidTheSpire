package global.enemyactions;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.Player;
import server.ServerDataHandler;

public class Attack extends EnemyAction{
	private static final long serialVersionUID = 1L;
	
	private int damage;
	private int target;

	public Attack(Enemy e, ServerDataHandler sdh, int damage, int target) {
		super(e, "Attack", sdh);
		this.damage = damage;
		this.target = target;
	}
	
	public Attack(Enemy e, String n, ServerDataHandler sdh, int damage, int target) {
		super(e, n, sdh);
		this.damage = damage;
		this.target = target;
	}
	
	public void doAction() {
		ArrayList<Player> players = dataHandler.players;
		if(target < players.size()) {
			int dealt = players.get(target).takeAttackDamage(damage, enemy);
			enemy.damageDealtOut(dealt, name);
		}
	}
	
	public String toString() {
		return name + ": T:" + target + " D:" + damage;
	}

}
