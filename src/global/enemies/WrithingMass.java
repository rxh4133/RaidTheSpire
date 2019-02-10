package global.enemies;

import java.util.ArrayList;

import global.ELM;
import global.Enemy;
import global.EnemyAction;
import global.Entity;
import global.Player;
import global.StatusEffect;
import global.statuseffects.Metallicize;
import global.statuseffects.Regen;
import server.ServerDataHandler;

public class WrithingMass extends Enemy {
	private static final long serialVersionUID = 1L;

	public WrithingMass(ServerDataHandler sdh) {
		super(sdh, 120);
		this.addSE(new MSEL(7));
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

	private class MSEL extends StatusEffect{
		private static final long serialVersionUID = 1L;

		public MSEL(int v) {
			super("Writhing", v);
		}

		@Override
		public void notify(Entity entity, ELM message, Object data) {
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN)){
				if(value > 0 && (int) (((Object[]) data)[0]) > 0) {
					WrithingWorm wm = new WrithingWorm(dataHandler);
					wm.decideAction();
					wm.takeDamage((int) (((Object[]) data)[0]));
					if(!wm.isDead()) {
						dataHandler.enemies.add(wm);
						wm.addListener(dataHandler);
					}
					value--;
				}
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
				int dealt = players.get(target1).takeAttackDamage((2*((WrithingMass) enemy).getSE("Writhing").value) + 10, enemy);
				enemy.damageDealtOut(dealt, name);
			}
			if(target2 < players.size() && target2 >= 0) {
				int dealt = players.get(target2).takeAttackDamage((2*((WrithingMass) enemy).getSE("Writhing").value) + 10, enemy);
				enemy.damageDealtOut(dealt, name);
			}
		}

		public String toString() {
			return "Bite: T1:" + target1 + " T2: " + target2 + " D:" +  (2*((WrithingMass) enemy).getSE("Writhing").value + 10);
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
			enemy.addSE(new MSEL(1));
			enemy.removeSE(enemy.getSE("Regen"));
			enemy.addSE(new Regen(5));
		}

		public String toString() {
			return "Swarm";
		}

	}
}
