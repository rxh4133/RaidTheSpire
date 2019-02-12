package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import global.statuseffect.statuseffects.Regen;
import server.EntityListener;
import server.ServerDataHandler;

public class MarkOfUndeath extends Relic {
	private static final long serialVersionUID = 1L;

	public MarkOfUndeath() {
		super("Mark of Undeath", Rarity.STARTER);
	}
	
	public Relic copyRelic() {
		return new MarkOfUndeath();
	}
	
	@Override
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		super.onAdd(owner, sdh);
		MUEL muel = new MUEL();
		owner.addListener(muel);
		return this;
	}
	
	private class MUEL implements EntityListener{

		@Override
		public void notify(Entity entity, ELM message, Object data) {
			if(message.is(ELM.DAMAGE_TAKEN) && (int) data > 0) {
				entity.addSE(new Regen(1));
			}
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN) && (int)((Object[]) data)[0] > 0) {
				entity.addSE(new Regen(1));
			}
		}
		
	}

}
