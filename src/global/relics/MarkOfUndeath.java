package global.relics;

import global.Entity;
import global.Player;
import global.Relic;
import global.statuseffects.Regen;
import server.EntityListener;
import server.ServerDataHandler;

public class MarkOfUndeath extends Relic {
	private static final long serialVersionUID = 1L;

	public MarkOfUndeath() {
		super("Mark of Undeath");
	}
	
	public Relic copyRelic() {
		return new MarkOfUndeath();
	}
	
	@Override
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		super.onAdd(owner, sdh);
		MUEL muel = new MUEL();
		owner.addAttDamSub(muel);
		owner.addDamSub(muel);
		return this;
	}
	
	private class MUEL implements EntityListener{

		@Override
		public void notify(Entity entity, String message, Object data) {
			if(message.equals("damagetaken") && (int) data > 0) {
				entity.addSE(new Regen(1));
			}
			if(message.equals("attdamagetaken") && (int)((Object[]) data)[0] > 0) {
				entity.addSE(new Regen(1));
			}
		}
		
	}

}
