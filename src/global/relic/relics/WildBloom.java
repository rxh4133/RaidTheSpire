package global.relic.relics;

import global.ELM;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ServerDataHandler;

public class WildBloom extends Relic {
	private static final long serialVersionUID = 1L;

	public WildBloom() {
		super(TP.R_ALL_WILDBLOOM_N, TP.R_ALL_WILDBLOOM_D, TP.R_ALL_WILDBLOOM_F, Rarity.UNCOMMON);
	}
	
	public Relic onAdd(Player p, ServerDataHandler sdh) {
		p.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, NotifyPayload o) {
		if(m.is(ELM.HEALED)) {
			o.n *= 2;
		}
	}
}
