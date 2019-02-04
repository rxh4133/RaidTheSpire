package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;
	
	private Entity appTo;

	public Thorns(int v, Entity appliedTo) {
		super("Thorns", v);
		if(appliedTo.getSE("Thorns") == null) {
			appliedTo.addAttDamSub(this);
		}
		appTo = appliedTo;
	}

	public void notify(Entity entity, String message, Object data) {
		if(message.equals("attdamagetaken")) {
			Entity reflect = (Entity) ((Object[]) data)[1];
			reflect.takeTrueDamage(this.value);
			appTo.damageDealtOut(this.value, name);
		}
	}
}
