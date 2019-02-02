package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class StrengthDown extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public StrengthDown(int v) {
		super("Strength Down", v);
	}
	
	public void postTurn(Entity e) {
		e.reduceSE(e.getSE("Strength"), value);
	}

}
