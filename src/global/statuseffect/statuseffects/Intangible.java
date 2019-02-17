package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;
import server.ActionInteruptException;

public class Intangible extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Intangible(int v) {
		super("Intangible", v, false, true);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			e.reduceSE(this, 1);
		}else if(m.is(ELM.ATTACK_DAMAGE_TAKEN)) {
			e.takeTrueDamage(1);
			throw new ActionInteruptException();
		}
	}

}
