package global;

import java.io.Serializable;

import server.ServerDataHandler;

public class EnemyAction implements Serializable{
	private static final long serialVersionUID = 1L;
	protected Enemy enemy;
	protected String name;
	protected transient ServerDataHandler dataHandler;
	
	public EnemyAction(Enemy e, String n, ServerDataHandler sdh) {
		enemy = e;
		name = n;
		dataHandler = sdh;
	}
	
	public void showAction() {
		//blackbox
	}
	
	public void doAction() {
		//blackbox
	}
}
