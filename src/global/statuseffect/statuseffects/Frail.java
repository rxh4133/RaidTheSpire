package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;
import server.ModifyValueException;

public class Frail extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Frail(int v, Entity appliedTo) {
		super("Frail", v);
	}

	@Override
	public void notify(Entity entity, ELM message, Object data) {
		if(message.is(ELM.TURN_START)) {
			entity.reduceSE(this, 1);
		}else if(message.is(ELM.BLOCK_GAINED_CARD)) {
			int gained = (int) data;
			throw new ModifyValueException(-(int)(gained * .25));
		}
	}


}
