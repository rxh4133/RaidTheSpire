package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class MaxAdded extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxAdded(int v, Entity appliedTo) {
		super("Max Added", v);
		appliedTo.addMaxHealth(v);
		if(appliedTo.getSE(name) == null) {
			appliedTo.addFightEndSub(this);
		}
	}
	
	public void notify(Entity e, String message, Object data) {
		e.reduceMaxHealth(value);
		e.removeFightEndSub(this);
		e.removeSE(this);
	}
}
