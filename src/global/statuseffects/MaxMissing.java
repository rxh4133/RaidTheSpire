package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class MaxMissing extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxMissing(int v, Entity appliedTo) {
		super("Max Missing", v);
		appliedTo.reduceMaxHealth(v);
		if(appliedTo.getSE(name) == null) {
			appliedTo.addFightEndSub(this);
		}
	}
	
	public void notify(Entity e, String message, Object data) {
		e.addMaxHealth(value);
		e.removeFightEndSub(this);
		e.removeSE(this);
	}
}
