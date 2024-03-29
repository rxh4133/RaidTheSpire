package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Thorns(int v) {
		super("Thorns", v, false, true);
	}

	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
		if(message.is(EntityListenerMessage.ATTACKED)) {
			Entity reflect = data.e;
			reflect.takeTrueDamage(this.value);
			entity.damageDealtOut(this.value, name);
		}
	}
}
