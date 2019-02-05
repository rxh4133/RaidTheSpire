package global;

import java.io.Serializable;

import global.relics.AvengingEye;

public enum PlayerClass implements Serializable{
	RETRIBUTOR(60, new AvengingEye()),
	REVENANT(80, null),
	RESIPISCENT(50, null),
	ALL(0,null);
	
	private int maxHealth;
	private Relic startingRelic;
	
	private PlayerClass(int maxHealth, Relic startingRelic) {
		this.maxHealth = maxHealth;
		this.startingRelic = startingRelic;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public Relic getStartingRelic() {
		if(startingRelic != null) {
			return startingRelic.copyRelic();
		}
		return null;
	}
}
