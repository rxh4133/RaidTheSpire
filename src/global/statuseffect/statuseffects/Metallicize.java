package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;

public class Metallicize extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Metallicize(int v) {
		super("Metallicize", v, false, true);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_END)) {
			e.gainBlock(value);
		}
	}
}
