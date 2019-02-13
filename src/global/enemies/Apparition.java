package global.enemies;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.enemyactions.AOEAttack;
import server.ServerDataHandler;

public class Apparition extends Enemy{
	private static final long serialVersionUID = 1L;

	public Apparition(ServerDataHandler sdh, int health) {
		super(sdh, health, "Apparition");
	}
	
	public ArrayList<EnemyAction> decideAction(){
		nextTurnActions = new ArrayList<EnemyAction>();
		
		nextTurnActions.add(new AOEAttack(this, dataHandler, 10));
		
		return nextTurnActions;
	}

}
