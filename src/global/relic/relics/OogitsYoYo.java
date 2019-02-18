package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ServerDataHandler;

public class OogitsYoYo extends Relic{
	
	private static final long serialVersionUID = 1L;
	private boolean justGained = false;
	
	public OogitsYoYo() {
		super(TP.R_ALL_OOGITSYOYO_N, TP.R_ALL_OOGITSYOYO_D, TP.R_ALL_OOGITSYOYO_F, Rarity.UNCOMMON);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			if(justGained) {
				e.takeTrueDamage(5);
			}else {
				e.heal(7);
			}
			justGained = !justGained;
		}
	}
}
