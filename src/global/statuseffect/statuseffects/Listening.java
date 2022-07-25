package global.statuseffect.statuseffects;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Listening extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Listening() {
		super("Listening", 1, true, false);
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload np) {
		if(m.is(EntityListenerMessage.BLOCK_GAINED) || m.is(EntityListenerMessage.BLOCK_GAINED_CARD)) {
			np.n = 0;
		}
	}
	
	public int getPriority() {
		return 80;
	}

}
