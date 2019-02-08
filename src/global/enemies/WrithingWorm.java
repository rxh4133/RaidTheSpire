package global.enemies;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.Entity;
import global.enemyactions.Attack;
import global.enemyactions.Weaken;
import server.EntityListener;
import server.ServerDataHandler;

public class WrithingWorm extends Enemy {
	private static final long serialVersionUID = 1L;

	public WrithingWorm(ServerDataHandler sdh) {
		super(sdh, (int) ((Math.random() * 5) + 12));
		this.addAttDamSub(new CUEL());
	}
	
	public ArrayList<EnemyAction> decideAction() {
		nextTurnActions = new ArrayList<EnemyAction>();
		int rand = (int) (Math.random() * 2);
		if(rand == 0) {
			nextTurnActions.add(new Attack(this, dataHandler, 12, (int) (Math.random() * dataHandler.players.size())));
		}else {
			nextTurnActions.add(new Weaken(this, 2, (int) (Math.random() * dataHandler.players.size()), dataHandler));
		}
		return nextTurnActions;
	}

	private class CUEL implements EntityListener{
		
		private boolean alreadyCurledUp = false;

		@Override
		public void notify(Entity entity, String message, Object data) {
			if(!alreadyCurledUp) {
				entity.gainBlock((int) (3 + Math.random()*5));
				alreadyCurledUp = true;
			}
		}
		
	}
}
