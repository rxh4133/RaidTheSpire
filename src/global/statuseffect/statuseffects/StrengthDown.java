package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;

public class StrengthDown extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public StrengthDown(int v) {
		super("Strength Down", v);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_END)) {
			e.reduceSE(e.getSE("Strength"), value);
			e.reduceSE(this, value);
		}
	}

}
