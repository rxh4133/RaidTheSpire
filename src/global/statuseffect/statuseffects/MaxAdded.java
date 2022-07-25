package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class MaxAdded extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public MaxAdded(int v, Entity appliedTo) {
		super("Max Added", v, false, true);
		appliedTo.addMaxHealth(v);
	}

	public void notify(Entity e, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.FIGHT_END)) {
			e.reduceMaxHealth(value);
			e.removeSE(this);
		}
	}
}
