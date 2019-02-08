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

	protected transient ArrayList<EntityListener> damSubs;
	protected transient ArrayList<EntityListener> attDamSubs;
	protected transient ArrayList<EntityListener> deathSubs;
	protected transient ArrayList<EntityListener> blockGainSubs;
	protected transient ArrayList<EntityListener> preTurnSubs;
	protected transient ArrayList<EntityListener> postTurnSubs;
	protected transient ArrayList<EntityListener> fightStartSubs;
	protected transient ArrayList<EntityListener> fightEndSubs;
	protected transient ArrayList<EntityListener> attackedSubs;
	protected transient ArrayList<EntityListener> attackingSubs;
	protected transient ArrayList<EntityListener> damageDealtSubs;
	protected transient ArrayList<EntityListener> healSubs;

	public Entity() {
		effects = new ArrayList<StatusEffect>();
		damSubs = new ArrayList<EntityListener>();
		attDamSubs = new ArrayList<EntityListener>();
		damSubs = new ArrayList<EntityListener>();
		blockGainSubs = new ArrayList<EntityListener>();
		preTurnSubs = new ArrayList<EntityListener>();
		postTurnSubs = new ArrayList<EntityListener>();
		fightStartSubs = new ArrayList<EntityListener>();
		fightEndSubs = new ArrayList<EntityListener>();
		deathSubs = new ArrayList<EntityListener>();
		attackedSubs = new ArrayList<EntityListener>();
		attackingSubs = new ArrayList<EntityListener>();
		damageDealtSubs = new ArrayList<EntityListener>();
		healSubs = new ArrayList<EntityListener>();
	}

	public void healToFull() {
		curHealth = maxHealth;
	}

	public void heal(int health) {
		for(EntityListener el: healSubs) {
			el.notify(this, "healed", health);
		}
		curHealth += health;
		if(curHealth > maxHealth) {
			curHealth = maxHealth;
		}
	}

	public void preTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).preTurn(this);
		}
		
		for(int i = 0; i < preTurnSubs.size(); i++) {
			preTurnSubs.get(i).notify(this, "preturn", this);
		}
		removeAllBlock();
	}

	public void postTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).postTurn(this);
		}
		for(EntityListener el: postTurnSubs) {
			el.notify(this, "postturn", this);
		}
	}

	public void removeAllSE() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(0).onRemove(this);
			effects.remove(0);
			i--;
		}
	}

	public void removeSE(StatusEffect se) {
		if(se != null) {
			se.onRemove(this);
			effects.remove(se);
		}
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

	public int getDamage(int damage) {
		if(this.getSE("Weak") != null) {
			damage = (int) (damage * .75);
		}
		damage += getStrength();
		return damage;
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
			for(int i = 0; i < deathSubs.size(); i++) {
				deathSubs.get(0).notify(this, "diedtodamage", damage);
				deathSubs.remove(0);
			}
		}
	}

	public int takeAttackDamage(int damage, Entity attacker) {
		damage = attacker.getDamage(damage);
		try {
			for(int i = 0; i < attackedSubs.size(); i++) {
				attackedSubs.get(i).notify(this, "attacked", new Object[] {damage, attacker});
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
			el.notify(this, "attdamagetaken", new Object[] {damage, attacker});
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(int i = 0; i < deathSubs.size(); i++) {
				deathSubs.get(0).notify(this, "diedtoattdamage", damage);
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

	public void reduceMaxHealth(int health) {
		maxHealth -= health;
		if(curHealth > maxHealth) {
			curHealth = maxHealth;
		}
		if(maxHealth <= 0) {
			takeTrueDamage(curHealth);
		}
	}

	public int getStrength() {
		for(StatusEffect se: effects) {
			if(se.name.equals("Strength")) {
				return se.value;
			}
		}
		return 0;
	}

	private int getDex() {
		for(StatusEffect se: effects) {
			if(se.name.equals("Dexterity")) {
				return se.value;
			}
		}
		return 0;
	}

	public void gainBlock(int block) {
		for(EntityListener el: blockGainSubs) {
			el.notify(this, "blockGained", block);
		}
		this.block += (block + getDex());
	}

	public void loseBlock(int block) {
		this.block -= block;
	}

	public int getBlock() {
		return block;
	}

	public void fightStartSubs() {
		for(EntityListener el: fightStartSubs) {
			el.notify(this, "fightstart", "fightstart");
		}
	}

	public void fightEndSubs() {
		for(EntityListener el: fightEndSubs) {
			el.notify(this, "fightend", "fightend");
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

	public void addTurnEndSub(EntityListener el) {
		postTurnSubs.add(el);
	}

	public void addBlockGainedSub(EntityListener el) {
		blockGainSubs.add(el);
	}

	public void removeBlockGainedSub(EntityListener el) {
		blockGainSubs.remove(el);
	}

	public void removeAttackedSub(EntityListener el) {
		attackedSubs.remove(el);
	}

	public void removeDamageDealtSub(EntityListener el) {
		damageDealtSubs.remove(el);
	}

	public void removeFightEndSub(EntityListener el) {
		fightEndSubs.remove(el);
	}

	public void addFightEndSub(EntityListener el) {
		fightEndSubs.add(el);
	}

	public void addHealSub(EntityListener el) {
		healSubs.add(el);
	}

	public void addDamSub(EntityListener el) {
		damSubs.add(el);
	}

	public void removeDamSub(EntityListener el) {
		damSubs.remove(el);
	}

	public void removeHealSub(EntityListener el) {
		healSubs.remove(el);
	}

	public void removeDeathSub(EntityListener el) {
		deathSubs.remove(el);
	}
	
	public void addTurnStartSub(EntityListener el) {
		preTurnSubs.add(el);
	}
	
	public void removeTurnStartSub(EntityListener el) {
		preTurnSubs.remove(el);
	}
	
	public void removeAttDamSub(EntityListener el) {
		attDamSubs.remove(el);
	}

	public boolean isDead() {
		return curHealth <= 0;
	}
}
