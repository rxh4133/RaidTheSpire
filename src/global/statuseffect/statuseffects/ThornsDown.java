package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class ThornsDown extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public ThornsDown(int v) {
		super("Thorns Down", v, true, false);
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.TURN_START)) {
			e.reduceSE(new StatusEffect("Thorns", 5), value);
			e.reduceSE(new StatusEffect("Thorns Down", 5), value);
		}
	}

}
