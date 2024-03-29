package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Vulnerable extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Vulnerable(int v) {
		super("Vulnerable", v, true, false);
	}

	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.ATTACKED)) {
			data.n *= 1.5;
		}else if(message.is(EntityListenerMessage.TURN_START)) {
			entity.reduceSE(this, 1);
		}
	}
}
