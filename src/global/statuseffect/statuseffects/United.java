package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.statuseffect.StatusEffect;

public class United extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public United(int v) {
		super("United", v);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload np) {
		if(m.is(EntityListenerMessage.TURN_START)) {
			if(e instanceof Player) {
				((Player) e).drawCards(value);
			}
		}
	}

}
