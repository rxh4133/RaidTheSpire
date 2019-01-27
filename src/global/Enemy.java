package global;

import java.util.ArrayList;

public class Enemy extends Entity{
	
	public Enemy() {
		effects = new ArrayList<StatusEffect>();
	}
	
	public EnemyAction takeAction() {
		return null;//bb
	}
	
	public EnemyAction decideAction() {
		return null;//bb
	}
	
	

}
