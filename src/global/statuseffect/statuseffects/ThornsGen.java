package global.statuseffect.statuseffects;

import global.Entity;
import global.statuseffect.StatusEffect;

public class ThornsGen extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public ThornsGen(int v) {
		super("Thorns Gen", v);
	}

	public void postTurn(Entity entity) {
		entity.addSE(new Thorns(value, entity));
	}
}
