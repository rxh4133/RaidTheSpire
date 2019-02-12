package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ModifyValueException;
import server.ServerDataHandler;

public class PenNibba extends Relic{
	private static final long serialVersionUID = 1L;
	private int attacks;
	
	public PenNibba() {
		super("Pen Nibba", Rarity.UNCOMMON);
	}
	
	public Relic onAdd(Player p, ServerDataHandler sdh) {
		p.addListener(this);
		return this;
	}
	
	public Relic copyRelic() {
		return new PenNibba();
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
