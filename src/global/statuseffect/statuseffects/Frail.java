package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Frail extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Frail(int v) {
		super("Frail", v, true, false);
	}

	@Override
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.TURN_START)) {
			entity.reduceSE(this, 1);
		}else if(message.is(EntityListenerMessage.BLOCK_GAINED_CARD)) {
			data.n -= (int)(data.n/4);
		}
	}


}
