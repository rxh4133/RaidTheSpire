package global.statuseffects;

import global.ELM;
import global.Entity;
import global.StatusEffect;
import server.ModifyValueException;

public class Vulnerable extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Vulnerable(int v, Entity appliedTo) {
		super("Vulnerable", v);
	}

	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

	public void notify(Entity entity, ELM message, Object data) {
		if(message.is(ELM.ATTACKED)) {
			Object[] payload = (Object[]) data;
			throw new ModifyValueException((int) ((int) (payload[0]) * .5));
		}else if(message.is(ELM.TURN_START)) {
			entity.reduceSE(this, 1);
		}
	}
}
