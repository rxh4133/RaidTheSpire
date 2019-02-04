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
		System.out.println("message thorns gets " + message);
		if(message.equals("attdamagetaken")) {
			Entity reflect = (Entity) ((Object[]) data)[1];
			System.out.println(this.value);
			reflect.takeTrueDamage(this.value);
			appTo.damageDealtOut(this.value, name);
		}
	}
}
