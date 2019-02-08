package global.statuseffects;

import global.Entity;
import global.Player;
import global.StatusEffect;
import server.EntityListener;

public class Vulnerable extends StatusEffect{

	private static final long serialVersionUID = 1L;
	
	private EntityListener el;

	public Vulnerable(int v, Entity appliedTo) {
		super("Vulnerable", v);
		if(appliedTo.getSE("Vulnerable") == null) {
			appliedTo.addAttackedSub(new VEL());
		}
	}
	
	public void onRemove(Player p) {
		p.removeAttackedSub(el);
	}

	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}

	private class VEL implements EntityListener{

		@Override
		public void notify(Entity entity, String message, Object data) {
			entity.takeDamage((int) ((Object[]) data)[0]);
		}

	}
}
