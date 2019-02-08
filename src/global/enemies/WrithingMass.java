package global.enemies;

import java.util.ArrayList;

import global.Enemy;
import global.EnemyAction;
import global.Entity;
import global.Player;
import global.statuseffects.Metallicize;
import global.statuseffects.Regen;
import server.EntityListener;
import server.ServerDataHandler;

public class WrithingMass extends Enemy {
	private static final long serialVersionUID = 1L;

	private int numCanSpawn = 7;
	private int numSpawned;

	public WrithingMass(ServerDataHandler sdh) {
		super(sdh, 120);
		this.addAttDamSub(new MSEL(this));
	}

	public ArrayList<EnemyAction> decideAction() {
		nextTurnActions = new ArrayList<EnemyAction>();
		int whatToDo = (int) (Math.random() * 3);
		System.out.println(whatToDo + " fuck man");
		if(whatToDo == 0) {
			nextTurnActions.add(new Bite(this, dataHandler));
		}else if(whatToDo == 1) {
			nextTurnActions.add(new Writhe(this, dataHandler));
		}else {
			nextTurnActions.add(new Swarm(this, dataHandler));
		}

		return nextTurnActions;
	}

	private class MSEL implements EntityListener{
		
		private WrithingMass wm;
		
		public MSEL(WrithingMass wm) {
			this.wm = wm;
		}

		@Override
		public void notify(Entity entity, String message, Object data) {
			if(wm.numSpawned < wm.numCanSpawn && (int) (((Object[]) data)[0]) > 0) {
				WrithingWorm wm = new WrithingWorm(dataHandler);
				wm.decideAction();
				wm.takeDamage((int) (((Object[]) data)[0]));
				if(!wm.isDead()) {
					dataHandler.enemies.add(wm);
					wm.addDeathSub(dataHandler);
				}
				numSpawned++;
			}
		}

	}

	private class Bite extends EnemyAction {
		private static final long serialVersionUID = 1L;
		int target1;
		int target2;

		public Bite(WrithingMass wm, ServerDataHandler sdh) {
			super(wm, "Bite", sdh);
			ArrayList<Player> players = sdh.players;
			if(players.size() > 1) {
				int offset = (int) (Math.random() * (players.size() - 1));
				target1 = offset;
				target2 = offset + 1;
			}else if(players.size() == 1){
				target1 = 0;
				target2 = -1;
			}
		}

		public void doAction() {
			ArrayList<Player> players = dataHandler.players;
			if(target1 < players.size() && target1 >= 0) {
				int dealt = players.get(target1).takeAttackDamage((2*((WrithingMass) enemy).numCanSpawn) + 10, enemy);
				enemy.damageDealtOut(dealt, name);
			}
			if(target2 < players.size() && target2 >= 0) {
				int dealt = players.get(target2).takeAttackDamage((2*((WrithingMass) enemy).numCanSpawn) + 10, enemy);
				enemy.damageDealtOut(dealt, name);
			}
		}

		public String toString() {
			return "Bite: T1:" + target1 + " T2: " + target2 + " D:" +  2*((WrithingMass) enemy).numCanSpawn + 10;
		}

	}

	private class Writhe extends EnemyAction{
		private static final long serialVersionUID = 1L;

		public Writhe(Enemy e, ServerDataHandler sdh) {
			super(e, "Writhe", sdh);		
		}
		
		public void doAction() {
			enemy.gainBlock(20);
			enemy.addSE(new Metallicize(1));
		}
		
		public String toString() {
			return "Writhe";
		}

	}

	private class Swarm extends EnemyAction{
		private static final long serialVersionUID = 1L;

		public Swarm(WrithingMass wm, ServerDataHandler sdh) {
			super(wm, "Swarm", sdh);
		}
		
		public void doAction() {
			((WrithingMass) enemy).numCanSpawn++;
			enemy.removeSE(enemy.getSE("Regen"));
			enemy.addSE(new Regen(5));
		}
		
		public String toString() {
			return "Swarm";
		}
		
	}
}
