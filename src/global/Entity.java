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

	protected transient ArrayList<EntityListener> listeners;

	public Entity() {
		effects = new ArrayList<StatusEffect>();
		listeners = new ArrayList<EntityListener>();
	}

	public void healToFull() {
		curHealth = maxHealth;
	}

	public void heal(int health) {
		for(EntityListener el: listeners) {
			el.notify(this, ELM.HEALED, health);
		}
		curHealth += health;
		if(curHealth > maxHealth) {
			curHealth = maxHealth;
		}
	}

	public void preTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).notify(this, ELM.TURN_START, this);
		}
		
		for(int i = 0; i < listeners.size(); i++) {
			listeners.get(i).notify(this, ELM.TURN_START, this);
		}
		removeAllBlock();
	}

	public void postTurn() {
		for(int i = 0; i < effects.size(); i++) {
			effects.get(i).notify(this, ELM.TURN_END, this);
		}
		for(EntityListener el: listeners) {
			el.notify(this, ELM.TURN_END, this);
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
		for(EntityListener el: listeners) {
			el.notify(this, ELM.DAMAGE_DEALT, new Object[] {damage, source});
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
			for(EntityListener el: listeners) {
				el.notify(this, ELM.DIED_TRUE_DAMAGE, damage);
			}
		}
	}

	public void takeDamage(int damage) {
		for(EntityListener el: listeners) {
			el.notify(this, "damagetaken", damage);
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(int i = 0; i < listeners.size(); i++) {
				listeners.get(0).notify(this, "diedtodamage", damage);
				listeners.remove(0);
			}
		}
	}

	public int takeAttackDamage(int damage, Entity attacker) {
		damage = attacker.getDamage(damage);
		try {
			for(int i = 0; i < listeners.size(); i++) {
				listeners.get(i).notify(this, "attacked", new Object[] {damage, attacker});
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
		for(EntityListener el: listeners) {
			el.notify(this, "attdamagetaken", new Object[] {damage, attacker});
		}
		curHealth -= damage;
		if(curHealth <= 0) {
			for(int i = 0; i < listeners.size(); i++) {
				listeners.get(0).notify(this, "diedtoattdamage", damage);
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
		for(EntityListener el: listeners) {
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
		for(EntityListener el: listeners) {
			el.notify(this, "fightstart", "fightstart");
		}
	}

	public void fightEndSubs() {
		for(EntityListener el: listeners) {
			el.notify(this, "fightend", "fightend");
		}
	}

	public void addListener(EntityListener el) {
		listeners.add(el);
	}
	
	public void removeListener(EntityListener el) {
		listeners.remove(el);
	}

	public boolean isDead() {
		return curHealth <= 0;
	}
}
