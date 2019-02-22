package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Weak extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Weak(int v) {
		super("Weak", v, true, false);
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.ATTACKING)) {
			o.n *= .75;
		}else if(m.is(ELM.TURN_START)) {
			e.reduceSE(this, 1);
		}
	}

}
