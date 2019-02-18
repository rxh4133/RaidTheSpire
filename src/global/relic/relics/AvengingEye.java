package global.relic.relics;

import java.io.Serializable;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import global.statuseffect.statuseffects.Strength;
import global.statuseffect.statuseffects.StrengthDown;
import server.EntityListener;
import server.ServerDataHandler;

public class AvengingEye extends Relic{
	private static final long serialVersionUID = 1L;

	public AvengingEye() {
		super(TP.R_T_AVENGINGEYE_N, TP.R_T_AVENGINGEYE_D, TP.R_T_AVENGINGEYE_F, Rarity.STARTER);
	}

	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		super.onAdd(owner, sdh);
		AEL ael = new AEL(owner);
		for(Player p: dataHandler.players) {
			p.addListener(ael);
		}
		return this;
	}

	private class AEL implements EntityListener, Serializable{
		private static final long serialVersionUID = 1L;
		public transient Player owner;

		public AEL(Player o) {
			owner = o;
		}

		@Override
		public void notify(Entity entity, ELM message, Object data) {
			if(message.is(ELM.ATTACK_DAMAGE_TAKEN)){
				if((int)(((Object[]) data)[0]) > 0) {
					owner.addSE(new Strength(1));
					owner.addSE(new StrengthDown(1));
				}
			}
			if(message.is(ELM.DAMAGE_TAKEN)) {
				if((int) data > 0) {
					owner.addSE(new Strength(1));
					owner.addSE(new StrengthDown(1));
				}
			}
		}

	}

}
