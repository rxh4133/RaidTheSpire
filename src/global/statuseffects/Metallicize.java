package global.statuseffects;

import global.ELM;
import global.Entity;

public class Metallicize extends global.StatusEffect{
	private static final long serialVersionUID = 1L;

	public Metallicize(int v) {
		super("Metallicize", v);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_END)) {
			e.gainBlock(value);
		}
	}
}
