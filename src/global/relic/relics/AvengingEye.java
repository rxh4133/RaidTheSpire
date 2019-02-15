package global.relic.relics;

import java.io.Serializable;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import global.statuseffect.statuseffects.Strength;
import global.statuseffect.statuseffects.StrengthDown;
import server.EntityListener;
import server.ServerDataHandler;

public class AvengingEye extends Relic{
	private static final long serialVersionUID = 1L;

	public AvengingEye() {
		super("Avenging Eye", Rarity.STARTER);
		description = "For the duration of your turn, gain strength equal to the number of instances of damage an ally took last turn.";
		flavor = "The eye you took in return for yours. (There's no player images right now, but the retributor is missing an eye)";
	}

	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		super.onAdd(owner, sdh);
		AEL ael = new AEL(owner);
		for(Player p: dataHandler.players) {
			p.addListener(ael);
		}
		return this;
	}

	public Relic copyRelic() {
		return new AvengingEye();
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