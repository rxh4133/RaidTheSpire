package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;
import server.ModifyValueException;

public class Strength extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Strength(int v) {
		super("Strength", v);
	}

	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.ATTACKING)) {
			throw new ModifyValueException(value);
		}
	}
}
