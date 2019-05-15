package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.statuseffect.StatusEffect;

public class United extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public United(int v) {
		super("United", v);
	}
	
	public void notify(Entity e, ELM m, NotifyPayload np) {
		if(m.is(ELM.TURN_START)) {
			if(e instanceof Player) {
				((Player) e).drawCards(value);
			}
		}
	}

}
