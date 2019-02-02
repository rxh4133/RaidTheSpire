package global.statuseffects;

import global.Entity;
import global.Player;
import global.StatusEffect;

public class StartDraw extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public StartDraw(int v) {
		super("Start Draw", v);
	}
	
	public void preTurn(Entity e) {
		((Player) e).drawCards(value);
		((Player) e).reduceSE(this, value);
	}

}
