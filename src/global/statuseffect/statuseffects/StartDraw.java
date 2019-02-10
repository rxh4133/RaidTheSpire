package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.Player;
import global.statuseffect.StatusEffect;

public class StartDraw extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public StartDraw(int v) {
		super("Start Draw", v);
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			((Player) e).drawCards(value);
			((Player) e).reduceSE(this, value);
		}
	}

}
