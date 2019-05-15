package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class StrengthUp extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public StrengthUp(int v) {
		super("Strength Up", v, false, true);
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.TURN_END)) {
			e.addSE(new Strength(value));
			e.removeSE(this);
		}
	}
}
