package global.statuseffects;

import global.ELM;
import global.Entity;
import global.StatusEffect;

public class MaxAdded extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxAdded(int v, Entity appliedTo) {
		super("Max Added", v);
		appliedTo.addMaxHealth(v);
	}

	public void notify(Entity e, ELM message, Object data) {
		if(message.is(ELM.FIGHT_END)) {
			e.reduceMaxHealth(value);
			e.removeSE(this);
		}
	}
}
