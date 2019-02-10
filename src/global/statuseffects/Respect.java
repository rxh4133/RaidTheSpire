package global.statuseffects;

import global.ELM;
import global.Entity;
import global.StatusEffect;

public class Respect extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Respect() {
		super("Respect", 0);
	}

	public void notify(Entity e, ELM message, Object data) {
		if(message.is(ELM.DAMAGE_DEALT)) {
			Object[] pay = (Object[]) data;
			if(pay[1].equals("Thorns")) {
				this.value += (int) pay[0];
			}
		}else if(message.is(ELM.TURN_START)) {
			e.gainBlock(value);
			value = 0;
		}
	}

}
