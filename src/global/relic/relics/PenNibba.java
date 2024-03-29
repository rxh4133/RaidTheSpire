package global.relic.relics;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
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
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.ATTACKING)) {
			if(attacks == 9) {
				attacks = 0;
				o.n *= 2;
			}else {
				attacks++;
			}
		}
	}
}
