package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Necrotic extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Necrotic(int v) {
		super("Necrotic", v, true, false);
	}

	public void notify(Entity e, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.HEALED)) {
			data.n -= (int) (data.n / 2);
		}
	}
}
