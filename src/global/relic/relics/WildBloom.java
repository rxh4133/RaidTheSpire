package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ModifyValueException;
import server.ServerDataHandler;

public class WildBloom extends Relic {
	private static final long serialVersionUID = 1L;

	public WildBloom() {
		super("Wild Bloom", Rarity.UNCOMMON);
	}
	
	public Relic onAdd(Player p, ServerDataHandler sdh) {
		p.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.HEALED)) {
			throw new ModifyValueException((int) ((int) o * .5));
		}
	}
	
	public Relic copyRelic() {
		return new WildBloom();
	}
}
