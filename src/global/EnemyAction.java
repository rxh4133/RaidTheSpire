package global;

import java.io.Serializable;

public class EnemyAction implements Serializable{
	
	protected Enemy enemy;
	
	public EnemyAction() {
		
	}
	
	public EnemyAction(Enemy e) {
		enemy = e;
	}
	
	public void showAction() {
		//blackbox
	}
	
	public void doAction() {
		//blackbox
	}
}
