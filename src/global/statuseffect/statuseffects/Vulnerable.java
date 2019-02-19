package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;
import server.ModifyValueException;

public class Vulnerable extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Vulnerable(int v, Entity appliedTo) {
		super("Vulnerable", v, true, false);
	}

	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

	public void notify(Entity entity, ELM message, NotifyPayload data) {
		if(message.is(ELM.ATTACKED)) {
			throw new ModifyValueException((int) (data.n * .5));
		}else if(message.is(ELM.TURN_START)) {
			entity.reduceSE(this, 1);
		}
	}
}
