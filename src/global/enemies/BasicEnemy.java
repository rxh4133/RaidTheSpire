package global.enemies;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.enemyactions.Attack;
import server.ServerDataHandler;

public class BasicEnemy extends Enemy{
	private static final long serialVersionUID = 1L;
	public BasicEnemy(ServerDataHandler sdh) {
		super(sdh, 30, "Basic Enemy");
	}
	
	public ArrayList<EnemyAction> decideAction() {
		nextTurnActions = new ArrayList<EnemyAction>();
		for(int i = 0; i < dataHandler.players.size(); i++) {
			nextTurnActions.add(new Attack(this, dataHandler, 10, i));
		}
		return nextTurnActions;
	}


}
