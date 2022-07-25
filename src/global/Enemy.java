package global;

import java.util.ArrayList;

import server.ServerDataHandler;

public class Enemy extends Entity{
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<EnemyAction> nextTurnActions;
	protected transient ServerDataHandler dataHandler;
	protected String name;

	public Enemy(ServerDataHandler sdh, int health, String n) {
		super();
		dataHandler = sdh;
		addListener(dataHandler);
		this.maxHealth = health;
		this.curHealth = health;
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Execute the actions that this enemy is intending to take.
	 */
	public void takeAction() {
		for(EnemyAction ea: nextTurnActions) {
			ea.doAction();
		}
	}
	
	public ArrayList<EnemyAction> getActions(){
		return nextTurnActions;
	}
	
	/**
	 * Determine the next actions this enemy will take.
	 * @return
	 */
	public ArrayList<EnemyAction> decideAction() {
		return null;//bb
	}
	
	public String toString() {
		return "Enemy:\n\t"
				+ "Health: (" + block + "B) " + curHealth + "/" + maxHealth + "\n\t"
						+ "Actions: " + nextTurnActions + "\n\t"
						+ "Status: " + effects;
	}
}
