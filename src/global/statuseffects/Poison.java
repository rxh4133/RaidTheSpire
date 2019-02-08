package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Poison extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Poison(int v) {
		super("Poison", v);
	}
	
	public void preTurn(Entity e) {
		e.takeDamage(value);
		if(e.getSE("Necrotic") == null) {
			e.reduceSE(this, 1);
		}
	}

}
