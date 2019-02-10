package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;
import server.ModifyValueException;

public class Weak extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Weak(int v) {
		super("Weak", v);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.ATTACKING)) {
			throw new ModifyValueException(-(int) ((int) o * .25));
		}else if(m.is(ELM.TURN_START)) {
			e.reduceSE(this, 1);
		}
	}

}
