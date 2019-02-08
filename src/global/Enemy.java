package global;

import java.util.ArrayList;

import server.ServerDataHandler;

public class Enemy extends Entity{
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<EnemyAction> nextTurnActions;
	protected transient ServerDataHandler dataHandler;

	public Enemy(ServerDataHandler sdh, int health) {
		super();
		dataHandler = sdh;
		addDeathSub(dataHandler);
		this.maxHealth = health;
		this.curHealth = health;
	}
	
	public void takeAction() {
		for(EnemyAction ea: nextTurnActions) {
			ea.doAction();
		}
	}
	
	public ArrayList<EnemyAction> decideAction() {
		return null;//bb
	}
	
	public String toString() {
		return "Enemy:\n\t"
				+ "Health: (" + block + "B) " + curHealth + "/" + maxHealth + "\n\t"
						+ "Actions: " + nextTurnActions + "\n\t"
						+ "Status: " + effects;
	}
	
	public Enemy copyForDisplay() {
		Enemy e = new Enemy(dataHandler, maxHealth);
		e.curHealth = curHealth;
		e.effects = effects;
		e.block = block;
		return e;
	}

}
