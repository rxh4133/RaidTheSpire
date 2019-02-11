package global.enemies;

import java.util.ArrayList;

import global.ELM;
import global.Enemy;
import global.EnemyAction;
import global.Entity;
import global.statuseffect.StatusEffect;
import global.enemyactions.Attack;
import global.enemyactions.Weaken;
import server.ServerDataHandler;

public class WrithingWorm extends Enemy {
	private static final long serialVersionUID = 1L;

	public WrithingWorm(ServerDataHandler sdh) {
		super(sdh, (int) ((Math.random() * 5) + 12), "Writhing Worm");
		this.addSE(new CUEL((int) (3 + Math.random()*5)));
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

	private class CUEL extends StatusEffect {
		private static final long serialVersionUID = 1L;

		public CUEL(int v) {
			super("Curl Up", v);
		}

		private boolean alreadyCurledUp = false;

		@Override
		public void notify(Entity entity, ELM message, Object data) {
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN)) {
				if(!alreadyCurledUp) {
					entity.gainBlock(value);
					alreadyCurledUp = true;
				}
			}
		}

	}
}
