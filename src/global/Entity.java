package global;

import java.util.ArrayList;

public class Entity {
	
	protected int block;
	protected int maxHealth;
	protected int curHealth;
	
	protected ArrayList<StatusEffect> effects;
	protected ArrayList<EntityListener> damSubs;
	protected ArrayList<EntityListener> attDamSubs;
	
	public Entity() {
		
	}

	public void preTurnSE() {
		for(StatusEffect se: effects) {
			se.preTurn(this);
		}
	}
	
	public void postTurnSE() {
		for(StatusEffect se: effects) {
			se.postTurn(this);
		}
	}
	
	public void removeSE(StatusEffect se) {
		effects.remove(se);
	}
	
	public void takeDamage(int damage) {
		for(EntityListener el: damSubs) {
			el.tookDamage(this, damage);
		}
	}
	
	public void takeAttackDamage(int damage) {
		
	}
	
	public class EntityListener{
		public void tookDamage(Entity entity, Object data) {
			
		}
	}
}
