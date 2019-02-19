package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;
import server.ModifyValueException;

public class Necrotic extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Necrotic(int v) {
		super("Necrotic", v, true, false);
	}

	public void notify(Entity e, ELM message, NotifyPayload data) {
		if(message.is(ELM.HEALED)) {
			throw new ModifyValueException(-(int) (data.n * .5));
		}
	}
}
