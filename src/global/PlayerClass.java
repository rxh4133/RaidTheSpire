package global;

import java.io.Serializable;

import global.relic.Relic;
import global.relic.relics.AvengingEye;
import global.relic.relics.MarkOfUndeath;

public enum PlayerClass implements Serializable{
	RETRIBUTOR(60, new AvengingEye()),
	REVENANT(80, new MarkOfUndeath()),
	RESIPISCENT(50, new MarkOfUndeath()),
	ALL(0,new Relic("Ah fuck you", "","", Rarity.MYTHIC));
	
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
			return startingRelic.clone();
		}
		return null;
	}
}
