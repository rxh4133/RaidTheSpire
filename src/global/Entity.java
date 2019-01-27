package global;

import java.util.ArrayList;

import server.EntityListener;

public class Entity {
	
	protected int block;
	protected int maxHealth;
	protected int curHealth;
	
	protected ArrayList<StatusEffect> effects;
	protected ArrayList<EntityListener> damSubs;
	protected ArrayList<EntityListener> attDamSubs;
	protected ArrayList<EntityListener> deathSubs;
	
	public Entity() {
		effects = new ArrayList<StatusEffect>();
		damSubs = new ArrayList<EntityListener>();
		attDamSubs = new ArrayList<EntityListener>();
		damSubs = new ArrayList<EntityListener>();
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
	
	public void takeTrueDamage(int damage) {
		curHealth -= damage;
		if(curHealth <= 0) {
			for(EntityListener el: deathSubs) {
				el.notify(this, "diedtotruedamage", "diedtotruedamage");
			}
		}
	}
	
	public void takeDamage(int damage) {
		for(EntityListener el: damSubs) {
			el.notify(this, "damagetaken", damage);
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(EntityListener el: deathSubs) {
				el.notify(this, "diedtodamage", "diedtodamage");
			}
		}
	}
	
	public void takeAttackDamage(int damage) {
		for(EntityListener el: attDamSubs) {
			el.notify(this, "attdamagetaken", damage);
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(EntityListener el: deathSubs) {
				el.notify(this, "diedtoattdamage", "diedtoattdamage");
			}
		}
	}
}
