package global.relic.relics;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import global.statuseffect.statuseffects.Dexterity;
import server.ServerDataHandler;

public class SmoothStone extends Relic{
	private static final long serialVersionUID = 1L;

	public SmoothStone() {
		super(TP.R_ALL_SMOOTHSTONE_N, TP.R_ALL_SMOOTHSTONE_D, TP.R_ALL_SMOOTHSTONE_F, Rarity.COMMON);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.FIGHT_START)) {
			e.addSE(new Dexterity(1));
		}
	}

}