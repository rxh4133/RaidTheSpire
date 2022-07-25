package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Regen extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Regen(int v) {
		super("Regen", v, false, true);
	}

	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_END)) {
			e.heal(value);
			e.reduceSE(this, 1);
		}
	}
}
