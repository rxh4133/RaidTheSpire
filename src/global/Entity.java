package global;

import java.io.Serializable;
import java.util.ArrayList;

import global.statuseffect.StatusEffect;
import server.ActionInteruptException;
import server.ELList;
import server.EntityListener;

public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int block;
	protected int provisionalBlock;
	protected int maxHealth;
	protected int curHealth;

	public ELList<StatusEffect> effects;

	protected transient ELList<EntityListener> listeners;

	public Entity() {
		effects = new ELList<StatusEffect>();
		listeners = new ELList<EntityListener>();
	}

	public void healToFull() {
		curHealth = maxHealth;
	}

	public void heal(int health) {
		NotifyPayload pay = new NotifyPayload(this, health, "healed");
		notify(this, ELM.HEALED, pay);
		curHealth += pay.n;
		if(curHealth > maxHealth) {
			curHealth = maxHealth;
		}
	}
	
	public void addProvBlock(int block) {
		provisionalBlock += block;
	}
	
	public int getCurHealth() {
		return curHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public ArrayList<StatusEffect> getEffects(){
		return effects;
	}

	public void preTurn() {
		notify(this, ELM.TURN_START, new NotifyPayload(this, 0, "turnstart"));
		removeAllBlock();
		block += provisionalBlock;
		provisionalBlock = 0;
	}

	public void postTurn() {
		notify(this, ELM.TURN_END, new NotifyPayload(this, 0, "turnend"));
	}

	public void removeAllSE() {
		effects.removeAll(effects);
	}

	public void removeSE(StatusEffect se) {
		if(se != null) {
			effects.remove(se);
		}
	}
	

	public void subtractSE(StatusEffect se) {
		int index = effects.indexOf(se);
		if(index >= 0) {
			StatusEffect have = effects.get(index);
			have.value += se.value;
		}else {
			effects.add(se);
		}
	}

	public void reduceSE(StatusEffect se, int reduce) {
		int index = effects.indexOf(se);
		if (index >= 0) {
			StatusEffect al = effects.get(index);
			if(al != null) {
				al.value -= reduce;
				if(al.value <= 0) {
					removeSE(se);
				}
			}
		}
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
		notify(this, ELM.DAMAGE_DEALT, new NotifyPayload(this, damage, source));
	}

	public void removeAllBlock() {
		block = 0;
	}

	public void takeTrueDamage(int damage) {
		curHealth -= damage;
		if(curHealth <= 0) {
			notify(this, ELM.DIED_TRUE_DAMAGE, new NotifyPayload(this, damage, "diedtruedamage"));
		}
	}

	public void takeDamage(int damage) {
		notify(this, ELM.DAMAGE_TAKEN, new NotifyPayload(this, damage, "damtaken"));
		curHealth -= damage;
		if(curHealth <= 0) {
			notify(this, ELM.DIED_DAMAGE, new NotifyPayload(this, damage, "dieddamage"));
		}
	}

	public int takeAttackDamage(int damage, Entity attacker) {
		NotifyPayload pay = new NotifyPayload(this, damage, "attacking");
		attacker.notify(attacker, ELM.ATTACKING, pay);
		damage = pay.n;
		
		pay = new NotifyPayload(attacker, damage, "attacked");
		try {
			notify(this, ELM.ATTACKED, pay);
			damage = pay.n;
		} catch(ActionInteruptException afe) {
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

		pay = new NotifyPayload(attacker, damage, "attdamtaken");
		notify(this, ELM.ATTACK_DAMAGE_TAKEN, pay);
		damage = pay.n;

		curHealth -= damage;
		if(curHealth <= 0) {
			notify(this, ELM.DIED_ATTACK_DAMAGE, new NotifyPayload(attacker, damage, "diedattdam"));
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

	public void gainBlockFromCard(int block) {
		NotifyPayload pay = new NotifyPayload(null, block, "blockcard");
		notify(this, ELM.BLOCK_GAINED_CARD, pay);
		this.block += (pay.n);
	}

	public void gainBlock(int block) {
		NotifyPayload pay = new NotifyPayload(null, block, "block");
		notify(this, ELM.BLOCK_GAINED, new NotifyPayload(null, block, "block"));
		this.block += (pay.n);
	}

	public void loseBlock(int block) {
		this.block -= block;
	}

	public int getBlock() {
		return block;
	}

	public void fightStartSubs() {
		notify(this, ELM.FIGHT_START, new NotifyPayload(null, 0, "fightstart"));
	}

	public void fightEndSubs() {
		notify(this, ELM.FIGHT_END, new NotifyPayload(null, 0, "fightend"));
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
	
	private void notify(Entity el, ELM message, NotifyPayload data) {
		listeners.notifyAll(el, message, data);
		effects.notifyAll(el, message, data);
	}
}
