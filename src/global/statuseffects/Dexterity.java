package global.statuseffects;

import global.ELM;
import global.Entity;
import global.StatusEffect;
import server.ModifyValueException;

public class Dexterity extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Dexterity(int v) {
		super("Dexterity", v);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.BLOCK_GAINED_CARD)) {
			throw new ModifyValueException(value);
		}
	}

}
