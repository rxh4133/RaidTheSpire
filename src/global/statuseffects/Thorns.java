package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Thorns(int v) {
		super("Thorns", v);
	}

	public void notify(Entity entity, String message, Object data) {
		if(message.equals("attdamagetaken")) {
			Entity reflect = (Entity) ((Object[]) data)[1];
			reflect.takeTrueDamage(this.value);
		}
	}
}