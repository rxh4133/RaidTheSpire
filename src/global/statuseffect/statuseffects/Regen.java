package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;

public class Regen extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Regen(int v) {
		super("Regen", v, false, true);
	}

	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_END)) {
			e.heal(value);
			e.reduceSE(this, 1);
		}
	}
}
