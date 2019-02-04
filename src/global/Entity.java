package global;

import java.io.Serializable;
import java.util.ArrayList;
import server.AttackFailedException;
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
	protected ArrayList<EntityListener> damageDealtSubs;

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
		damageDealtSubs = new ArrayList<EntityListener>();
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

	public boolean reduceSE(StatusEffect se, int reduce) {
		int index = effects.indexOf(se);
		if (index >= 0) {
			StatusEffect al = effects.get(index);
			if(al != null) {
				al.value -= reduce;
				if(al.value <= 0) {
					removeSE(se);
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public StatusEffect getSE(String seName) {
		int index = effects.indexOf(new StatusEffect(seName, 0));
		if(index == -1) {
			return null;
		}else {
			return effects.get(index);
		}
	}

	public void addSE(StatusEffect se) {
		if(effects.contains(se)) {
			effects.get(effects.indexOf(se)).value += se.value;
		}else {
			effects.add(se);
		}
	}
	
	public void damageDealtOut(int damage, String source) {
		for(EntityListener el: damageDealtSubs) {
			el.notify(this, "damagedealt", new Object[] {damage, source});
		}
	}

	public void removeAllBlock() {
		block = 0;
	}

	public void takeTrueDamage(int damage) {
		curHealth -= damage;
		System.out.println("True damage taken: " + damage);
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

	public int takeAttackDamage(int damage, Entity attacker) {
		try {
			for(EntityListener el: attackedSubs) {
				el.notify(this, "attacked", new Object[] {damage, attacker});
			}
		} catch(AttackFailedException afe) {
			return 0;
		}
		if(block > 0) {
			damage = damage - block;
			if(damage < 0) {
				block = Math.abs(damage);
				damage = 0;
			}else {
				block = 0;
			}
		}
		for(EntityListener el: attDamSubs) {
			System.out.println("notifying a sub");
			el.notify(this, "attdamagetaken", new Object[] {damage, attacker});
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(EntityListener el: deathSubs) {
				el.notify(this, "diedtoattdamage", damage);
			}
		}
		return damage;
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
		attDamSubs.add(el);
		return el;
	}

	public void addAttackedSub(EntityListener el) {
		attackedSubs.add(el);
	}
	
	public void addDamageDealtSub(EntityListener el) {
		damageDealtSubs.add(el);
	}
	
	public void removeAttackedSub(EntityListener el) {
		attackedSubs.remove(el);
	}

	public boolean isDead() {
		return curHealth <= 0;
	}
}
