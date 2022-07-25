package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class ThornsGen extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public ThornsGen(int v) {
		super("Thorns Gen", v, false, true);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_END)) {
			e.addSE(new Thorns(value));
		}
	}
}
