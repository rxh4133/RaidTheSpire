package global.enemies;

import java.util.ArrayList;

import global.EntityListenerMessage;
import global.Enemy;
import global.EnemyAction;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.statuseffect.StatusEffect;
import global.statuseffect.statuseffects.Dexterity;
import global.statuseffect.statuseffects.Intangible;
import global.statuseffect.statuseffects.Metallicize;
import global.statuseffect.statuseffects.Strength;
import global.statuseffect.statuseffects.Weak;
import server.EntityListener;
import server.ServerDataHandler;

public class Apparitionist extends Enemy{
	private static final long serialVersionUID = 1L;

	public Entity lastAtt;
	public Entity secLastAtt;

	public Apparitionist(ServerDataHandler sdh) {
		super(sdh, 100, "Apparitionist");
		this.addSE(new Metallicize(20));
		this.addSE(new AEL(this));
	}

	public ArrayList<EnemyAction> decideAction() {
		nextTurnActions = new ArrayList<EnemyAction>();

		int rand = (int) (Math.random() * 4);
		if(rand == 0) {
			nextTurnActions.add(new Vanish(dataHandler));
		}else if(rand == 1) {
			nextTurnActions.add(new Surprise(dataHandler));
		}else if(rand == 2) {
			nextTurnActions.add(new Slit(this));
		}else if(rand == 3) {
			nextTurnActions.add(new Focus(this, dataHandler));
		}

		return nextTurnActions;
	}


	public void spawnApparition() {
		Apparition e = new Apparition(dataHandler, this.getSE("Metallicize").value + 1);
		e.decideAction();
		dataHandler.enemies.add(e);
		e.addListener(dataHandler);
		e.addListener(new AAEL(this));
	}
	
	private class Focus extends EnemyAction{
		private static final long serialVersionUID = 1L;

		public Focus(Enemy e, ServerDataHandler sdh) {
			super(e, "Focus", sdh);
		}
		
		public void doAction() {
			enemy.addSE(new Metallicize(1));
			enemy.gainBlock(5);
			for(Player p: dataHandler.players) {
				p.takeTrueDamage(5);
			}
		}
	}
	
	private class Slit extends EnemyAction{
		private static final long serialVersionUID = 1L;
		private Apparitionist a;
		public Slit(Apparitionist a) {
			super(a, "Slit", null);
			this.a = a;
		}
		
		public void doAction() {
			a.lastAtt.takeAttackDamage(15, a);
			a.secLastAtt.takeAttackDamage(15, a);
		}
		
	}
	
	private class Surprise extends EnemyAction{
		private static final long serialVersionUID = 1L;

		public Surprise(ServerDataHandler sdh) {
			super(null, "Surprise!", sdh);
		}
		
		public void doAction() {
			for(Player p: dataHandler.players) {
				p.addSE(new Weak(2));
				p.subtractSE(new Dexterity(-1));
				p.takeTrueDamage(5);
			}
		}
		
	}
	
	private class Vanish extends EnemyAction{
		private static final long serialVersionUID = 1L;

		public Vanish(ServerDataHandler sdh) {
			super(null, "Vanish", sdh);
		}
		
		public void doAction() {
			for(Player p: dataHandler.players) {
				if(p.getDrawSize() > 0) {
					p.exhaustRandomCardFromDraw();
				}else if(p.getDiscardSize() > 0) {
					p.exhaustRandomCardFromDiscard();
				}else if(p.getHandSize() > 0) {
					p.exhaustRandomCardFromHand();
				}
			}
		}
		
		
		public String toString() {
			return name + ": Skadiddle skadoodle your deck is now a noodle";
		}
	}

	private class AEL extends StatusEffect{
		private static final long serialVersionUID = 1L;
		private Apparitionist a;

		public AEL(Apparitionist a) {
			super("Illusory",1);
			this.a = a;
		}

		@Override
		public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
			if(message.is(EntityListenerMessage.ATTACK_DAMAGE_TAKEN)) {
				if(data.n > 0) {
					if(value == 1) {
						value = 0;
						a.spawnApparition();
						a.addSE(new Intangible(1));
					}
				}
			}else if(message.is(EntityListenerMessage.TURN_START)) {
				value = 1;
			}else if(message.is(EntityListenerMessage.ATTACKED)) {
				a.secLastAtt = a.lastAtt;
				a.lastAtt = data.e;
			}
		}
	}

	private class AAEL implements EntityListener{
		private Apparitionist a;

		public AAEL(Apparitionist a) {
			this.a = a;
		}

		@Override
		public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
			if(message.is(EntityListenerMessage.DIED_ATTACK_DAMAGE)|| message.is(EntityListenerMessage.DIED_TRUE_DAMAGE) || message.is(EntityListenerMessage.DIED_DAMAGE)) {
				a.addSE(new Strength(1));
				a.reduceSE(a.getSE("Metallicize"), 1);
				a.reduceSE(a.getSE("Intangible"), 1);
			}
		}

		@Override
		public int compareTo(EntityListener o) {
			return getPriority() - o.getPriority();
		}

		@Override
		public int getPriority() {
			return 0;
		}
	}

}
