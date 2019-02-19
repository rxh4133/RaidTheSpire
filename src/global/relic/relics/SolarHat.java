package global.relic.relics;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ServerDataHandler;

public class SolarHat extends Relic{
	private static final long serialVersionUID = 1L;

	public SolarHat() {
		super(TP.R_ALL_SOLARHAT_N, TP.R_ALL_SOLARHAT_D, TP.R_ALL_SOLARHAT_F, Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.setMaxEnergy(owner.getMaxEnergy() + 1);
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.TURN_START)) {
			e.takeTrueDamage(1);
		}
	}
}
