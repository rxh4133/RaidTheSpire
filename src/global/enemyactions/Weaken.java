package global.enemyactions;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.Player;
import global.statuseffect.statuseffects.Weak;
import server.ServerDataHandler;

public class Weaken extends EnemyAction{
	private static final long serialVersionUID = 1L;
	private int toWeaken;
	private int target;
	
	public Weaken(Enemy e, int toWeaken, int target, ServerDataHandler sdh) {
		super(e, "Weaken", sdh);
		this.toWeaken = toWeaken;
		this.target = target;
	}
	
	public void doAction() {
		ArrayList<Player> players = dataHandler.players;
		if(target < players.size()) {
			players.get(target).addSE(new Weak(toWeaken));
		}
	}
	
	public String toString() {
		return "Weaken: T:" + target;
	}

}
