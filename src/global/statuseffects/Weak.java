package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Weak extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Weak(int v) {
		super("Weak", v);
	}
	
	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

}
