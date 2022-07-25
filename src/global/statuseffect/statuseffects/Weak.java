package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Weak extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Weak(int v) {
		super("Weak", v, true, false);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.ATTACKING)) {
			o.n *= .75;
		}else if(m.is(EntityListenerMessage.TURN_START)) {
			e.reduceSE(this, 1);
		}
	}
}
