package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class MaxMissing extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxMissing(int v, Entity appliedTo) {
		super("Max Missing", v, true, false);
		appliedTo.reduceMaxHealth(v);
	}

	public void notify(Entity e, ELM message, NotifyPayload data) {
		if(message.is(ELM.FIGHT_END)) {
			e.addMaxHealth(value);
			e.removeSE(this);
		}
	}
}
