package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Dexterity extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Dexterity(int v) {
		super("Dexterity", v);
	}
	
	public boolean isDebuff() {
		return value < 0;
	}
	
	public boolean isBuff() {
		return value > 0;
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.BLOCK_GAINED_CARD)) {
			o.n += value;
		}
	}

}
