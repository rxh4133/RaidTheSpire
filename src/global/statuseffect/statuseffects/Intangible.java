package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;
import server.ActionInteruptException;

public class Intangible extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Intangible(int v) {
		super("Intangible", v, false, true);
		priority = 60;
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_START)) {
			e.reduceSE(this, 1);
		}else if(m.is(EntityListenerMessage.ATTACK_DAMAGE_TAKEN)) {
			e.takeTrueDamage(1);
			throw new ActionInteruptException();
		}
	}

}
