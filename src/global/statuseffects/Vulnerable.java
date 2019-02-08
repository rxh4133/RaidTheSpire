package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Vulnerable extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Vulnerable(int v, Entity appliedTo) {
		super("Vulnerable", v);
		if(appliedTo.getSE("Vulnerable") == null) {
			appliedTo.addAttackedSub(this);
		}
	}

	public void onRemove(Entity p) {
		p.removeAttackedSub(this);
	}

	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

	public void notify(Entity entity, String message, Object data) {
		entity.takeDamage((int) ((int) ((Object[]) data)[0] * .5));
	}
}
