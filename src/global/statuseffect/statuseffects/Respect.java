package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.statuseffect.StatusEffect;

public class Respect extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Respect() {
		super("Respect", 0, false, true);
	}

	public void notify(Entity e, ELM message, NotifyPayload data) {
		if(message.is(ELM.DAMAGE_DEALT)) {
			if(data.t.equals("Thorns")) {
				this.value += data.n;
			}
		}else if(message.is(ELM.TURN_START)) {
			e.addProvBlock(value);
			value = 0;
		}
	}

}
