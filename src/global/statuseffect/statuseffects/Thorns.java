package global.statuseffect.statuseffects;

import global.ELM;
import global.Entity;
import global.statuseffect.StatusEffect;

public class Thorns extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Thorns(int v, Entity appliedTo) {
		super("Thorns", v);
	}

	public void notify(Entity entity, ELM message, Object data) {
		if(message.is(ELM.ATTACKED)) {
			Entity reflect = (Entity) ((Object[]) data)[1];
			reflect.takeTrueDamage(this.value);
			entity.damageDealtOut(this.value, name);
		}
	}
}
