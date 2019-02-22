package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Strength extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Strength(int v) {
		super("Strength", v);
		priority = 5;
	}
	
	public boolean isDebuff() {
		return value < 0;
	}
	
	public boolean isBuff() {
		return value > 0;
	}

	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.ATTACKING)) {
			o.n += value;
		}
	}
}
