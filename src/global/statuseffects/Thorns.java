package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Thorns(int v, Entity appliedTo) {
		super("Thorns", v);
		if(appliedTo.getSE("Thorns") == null) {
			appliedTo.addAttDamSub(this);
		}
	}

	public void notify(Entity entity, String message, Object data) {
		System.out.println("message thorns gets " + message);
		if(message.equals("attdamagetaken")) {
			Entity reflect = (Entity) ((Object[]) data)[1];
			System.out.println(this.value);
			reflect.takeTrueDamage(this.value);
		}
	}
}
