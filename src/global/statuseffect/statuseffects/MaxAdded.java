package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class MaxAdded extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxAdded(int v, Entity appliedTo) {
		super("Max Added", v, false, true);
		appliedTo.addMaxHealth(v);
	}

	public void notify(Entity e, ELM message, NotifyPayload data) {
		if(message.is(ELM.FIGHT_END)) {
			e.reduceMaxHealth(value);
			e.removeSE(this);
		}
	}
}
