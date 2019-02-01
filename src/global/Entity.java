package global;

import java.io.Serializable;
import java.util.ArrayList;

import server.EntityListener;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int block;
	protected int maxHealth;
	protected int curHealth;
	
	protected ArrayList<StatusEffect> effects;
	protected ArrayList<EntityListener> damSubs;
	protected ArrayList<EntityListener> attDamSubs;
	protected ArrayList<EntityListener> deathSubs;
	protected ArrayList<EntityListener> blockGainSubs;
	protected ArrayList<EntityListener> preTurnSubs;
	protected ArrayList<EntityListener> postTurnSubs;
	protected ArrayList<EntityListener> fightStartSubs;
	
	public Entity() {
		effects = new ArrayList<StatusEffect>();
		damSubs = new ArrayList<EntityListener>();
		attDamSubs = new ArrayList<EntityListener>();
		damSubs = new ArrayList<EntityListener>();
		blockGainSubs = new ArrayList<EntityListener>();
		preTurnSubs = new ArrayList<EntityListener>();
		postTurnSubs = new ArrayList<EntityListener>();
		fightStartSubs = new ArrayList<EntityListener>();
		deathSubs = new ArrayList<EntityListener>();
	}

	public void preTurn() {
		for(StatusEffect se: effects) {
			se.preTurn(this);
		}
		for(EntityListener el: preTurnSubs) {
			el.notify(this, "preturn", this);
		}
	}
	
	public void postTurn() {
		for(StatusEffect se: effects) {
			se.postTurn(this);
		}
		for(EntityListener el: postTurnSubs) {
			el.notify(this, "postturn", this);
		}
	}
	
	public void removeSE(StatusEffect se) {
		effects.remove(se);
	}
	
	public void takeTrueDamage(int damage) {
		curHealth -= damage;
		if(curHealth <= 0) {
			for(EntityListener el: deathSubs) {
				el.notify(this, "diedtotruedamage", damage);
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
				el.notify(this, "diedtodamage", damage);
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
				el.notify(this, "diedtoattdamage", damage);
			}
		}
	}
	
	public void addMaxHealth(int health) {
		maxHealth += health;
		curHealth += health;
		if(maxHealth <= 0) {
			takeTrueDamage(curHealth);
		}
	}
	
	public void gainBlock(int block) {
		for(EntityListener el: blockGainSubs) {
			el.notify(this, "blockGained", block);
		}
		this.block += block;
	}
	
	public void fightStartSubs() {
		for(EntityListener el: fightStartSubs) {
			el.notify(this, "fightstart", "fightstart");
		}
	}
	
	public void addDeathSub(EntityListener el) {
		deathSubs.add(el);
	}
	
	public boolean isDead() {
		return curHealth <= 0;
	}
}
