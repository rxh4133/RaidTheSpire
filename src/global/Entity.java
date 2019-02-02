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
	protected ArrayList<EntityListener> attackedSubs;
	protected ArrayList<EntityListener> attackingSubs;
	
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
		attackedSubs = new ArrayList<EntityListener>();
		attackingSubs = new ArrayList<EntityListener>();
	}
	
	public void healToFull() {
		curHealth = maxHealth;
	}

	public void preTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).preTurn(this);
		}
		for(EntityListener el: preTurnSubs) {
			el.notify(this, "preturn", this);
		}
	}
	
	public void postTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).postTurn(this);
		}
		for(EntityListener el: postTurnSubs) {
			el.notify(this, "postturn", this);
		}
	}
	
	public void removeSE(StatusEffect se) {
		effects.remove(se);
	}
	
	public void reduceSE(StatusEffect se, int reduce) {
		StatusEffect al = effects.get(effects.indexOf(se));
		if(al != null) {
			al.value -= reduce;
			if(al.value <= 0) {
				removeSE(se);
			}
		}
	}
	
	public StatusEffect getSE(String seName) {
		return effects.get(effects.indexOf(new StatusEffect(seName, 0)));
	}
	
	public void addSE(StatusEffect se) {
		if(effects.contains(se)) {
			effects.get(effects.indexOf(se)).value += se.value;
		}else {
			effects.add(se);
		}
	}
	
	public void removeAllBlock() {
		block = 0;
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
	
	public void takeAttackDamage(int damage, Entity attacker) {
		if(block > 0) {
			damage = damage - block;
			if(damage < 0) {
				block = Math.abs(damage);
				damage = 0;
			}
		}
		if(damage > 0) {
			for(EntityListener el: attDamSubs) {
				System.out.println("notifying a sub");
				el.notify(this, "attdamagetaken", new Object[] {damage, attacker});
			}
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
	
	public EntityListener addAttDamSub(EntityListener el) {
		System.out.println("fuggin addin it");
		attDamSubs.add(el);
		return el;
	}
	
	public boolean isDead() {
		return curHealth <= 0;
	}
}
