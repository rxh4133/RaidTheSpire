package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Frail extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Frail(int v) {
		super("Frail", v, true, false);
	}

	@Override
	public void notify(Entity entity, ELM message, NotifyPayload data) {
		if(message.is(ELM.TURN_START)) {
			entity.reduceSE(this, 1);
		}else if(message.is(ELM.BLOCK_GAINED_CARD)) {
			data.n -= (int)(data.n/4);
		}
	}


}
