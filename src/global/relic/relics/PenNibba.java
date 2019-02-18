package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ModifyValueException;
import server.ServerDataHandler;

public class PenNibba extends Relic{
	private static final long serialVersionUID = 1L;
	private int attacks;
	
	public PenNibba() {
		super(TP.R_ALL_PENNIBBA_N, TP.R_ALL_PENNIBBA_D, TP.R_ALL_PENNIBBA_F, Rarity.UNCOMMON);
	}
	
	public Relic onAdd(Player p, ServerDataHandler sdh) {
		p.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.ATTACKING)) {
			if(attacks == 9) {
				attacks = 0;
				throw new ModifyValueException((int) o * 2);
			}else {
				attacks++;
			}
		}
	}
}
