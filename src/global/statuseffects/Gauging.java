package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Gauging extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Gauging(int v) {
		super("Gauging", v);
	}
	
	public void preTurn(Entity e) {
		if(e.getBlock() > 0) {
			e.addSE(new Dexterity(3 * value));
		}
		e.removeSE(this);
	}

}
