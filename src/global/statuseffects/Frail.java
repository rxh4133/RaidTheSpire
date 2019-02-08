package global.statuseffects;

import global.Entity;
import global.StatusEffect;
import server.EntityListener;

public class Frail extends StatusEffect {
	private static final long serialVersionUID = 1L;
	
	private EntityListener el;

	public Frail(int v, Entity appliedTo) {
		super("Frail", v);
		el = new FEL();
		if(appliedTo.getSE("Frail") == null) {
			appliedTo.addBlockGainedSub(el);
		}
	}
	
	public void preTurn(Entity e) {
		e.reduceSE(this, 1);
	}
	
	public void onRemove(Entity p) {
		p.removeBlockGainedSub(el);
	}
	
	private class FEL implements EntityListener {

		@Override
		public void notify(Entity entity, String message, Object data) {
			int gained = (int) data;
			entity.loseBlock((int)(gained * .25));
		}
		
	}

}
