package global.relic.relics;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import global.statuseffect.statuseffects.Strength;
import server.ServerDataHandler;

public class Vajra extends Relic{
	private static final long serialVersionUID = 1L;

	public Vajra() {
		super(TP.R_ALL_VAJRA_N, TP.R_ALL_VAJRA_D, TP.R_ALL_VAJRA_F, Rarity.COMMON);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.FIGHT_START)) {
			e.addSE(new Strength(1));
		}
	}

}
