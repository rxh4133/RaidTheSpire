package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Respect extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Respect() {
		super("Respect", 0, false, true);
	}

	public void notify(Entity e, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.DAMAGE_DEALT)) {
			if(data.t.equals("Thorns")) {
				this.value += data.n;
			}
		}else if(message.is(EntityListenerMessage.TURN_START)) {
			e.addProvBlock(value);
			value = 0;
		}
	}

}
