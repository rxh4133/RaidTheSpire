package global.statuseffects;

import global.Entity;
import global.StatusEffect;
import server.EntityListener;

public class Vulnerable extends StatusEffect{

	private static final long serialVersionUID = 1L;

	public Vulnerable(int v, Entity appliedTo) {
		super("Vulnerable", v);
		appliedTo.addAttackedSub(new VEL());
	}

	
	private class VEL implements EntityListener{

		@Override
		public void notify(Entity entity, String message, Object data) {
			entity.takeDamage((int) ((Object[]) data)[0]);
		}
		
	}
}
