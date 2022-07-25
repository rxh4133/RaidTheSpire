package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Gauging extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Gauging(int v) {
		super("Gauging", v, false, true);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_START)) {
			if(e.getBlock() > 0) {
				e.addSE(new Dexterity(3 * value));
			}
			e.removeSE(this);
		}
	}

}
