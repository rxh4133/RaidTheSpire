package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Regen extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Regen(int v) {
		super("Regen", v);
	}
	
	public void postTurn(Entity e) {
		e.heal(value);
		e.reduceSE(this, 1);
	}

}
