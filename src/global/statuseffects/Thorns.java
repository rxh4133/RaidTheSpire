package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Thorns(int v, Entity appliedTo) {
		super("Thorns", v);
		if(appliedTo.getSE("Thorns") == null) {
			appliedTo.addAttackedSub(this);
		}
	}

	public void onRemove(Entity e) {
		e.removeAttackedSub(this);
	}

	public void notify(Entity entity, String message, Object data) {
		Entity reflect = (Entity) ((Object[]) data)[1];
		reflect.takeTrueDamage(this.value);
		entity.damageDealtOut(this.value, name);

	}
}
