package global;

public enum EntityListenerMessage {
	ATTACKED,
	ATTACKING,
	DAMAGE_TAKEN,
	ATTACK_DAMAGE_TAKEN,
	BLOCK_GAINED,
	BLOCK_GAINED_CARD,
	HEALED,
	FIGHT_START,
	FIGHT_END,
	TURN_START,
	TURN_END,
	DAMAGE_DEALT,
	DIED_ATTACK_DAMAGE,
	DIED_TRUE_DAMAGE,
	DIED_DAMAGE;
	
	
	
	public boolean is(EntityListenerMessage elm) {
		return this.equals(elm);
	}
}
