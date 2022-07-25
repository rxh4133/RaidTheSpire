package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class StrengthUp extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public StrengthUp(int v) {
		super("Strength Up", v, false, true);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_END)) {
			e.addSE(new Strength(value));
			e.removeSE(this);
		}
	}
}
