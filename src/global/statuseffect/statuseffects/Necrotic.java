package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Necrotic extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Necrotic(int v) {
		super("Necrotic", v, true, false);
	}

	public void notify(Entity e, ELM message, NotifyPayload data) {
		if(message.is(ELM.HEALED)) {
			data.n -= (int) (data.n / 2);
		}
	}
}
