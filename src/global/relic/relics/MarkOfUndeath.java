package global.relic.relics;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import global.statuseffect.statuseffects.Regen;
import server.EntityListener;
import server.ServerDataHandler;

public class MarkOfUndeath extends Relic {
	private static final long serialVersionUID = 1L;

	public MarkOfUndeath() {
		super(TP.R_V_MARKOFUNDEATH_N, TP.R_V_MARKOFUNDEATH_D, TP.R_V_MARKOFUNDEATH_F, Rarity.STARTER);
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
		public void notify(Entity entity, ELM message, NotifyPayload data) {
			if(message.is(ELM.DAMAGE_TAKEN) && data.n > 0) {
				entity.addSE(new Regen(1));
			}
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN) && data.n > 0) {
				entity.addSE(new Regen(1));
			}
		}

		@Override
		public int compareTo(EntityListener o) {
			return getPriority() - o.getPriority();
		}

		@Override
		public int getPriority() {
			return 20;
		}
		
	}

}
