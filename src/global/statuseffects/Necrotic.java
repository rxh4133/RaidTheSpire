package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Necrotic extends StatusEffect {
	private static final long serialVersionUID = 1L;

	public Necrotic(int v, Entity appliedTo) {
		super("Necrotic", v);
		if(appliedTo.getSE(name) == null) {
			appliedTo.addHealSub(this);
		}
	}
	
	public void onRemove(Entity e) {
		e.removeHealSub(this);
	}

	public void notify(Entity e, String message, Object data) {
		e.takeTrueDamage((int) ((int) data * .5));
	}
}
