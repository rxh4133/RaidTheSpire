package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;

public class Poison extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Poison(int v) {
		super("Poison", v, true, false);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			e.takeTrueDamage(value);
			if(e.getSE("Necrotic") == null) {
				e.reduceSE(this, 1);
			}
		}
	}

}
