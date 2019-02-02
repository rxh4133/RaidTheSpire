package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class ThornsDown extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public ThornsDown(int v) {
		super("Thorns Down", v);
	}
	
	public void preTurn(Entity entity) {
		entity.reduceSE(new StatusEffect("Thorns", 5), value);
		entity.reduceSE(new StatusEffect("Thorns Down", 5), value);

	}

}
