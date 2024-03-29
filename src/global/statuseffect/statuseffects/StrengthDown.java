package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class StrengthDown extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public StrengthDown(int v) {
		super("Strength Down", v, true, false);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_END)) {
			e.reduceSE(e.getSE("Strength"), value);
			e.reduceSE(this, value);
		}
	}
}
