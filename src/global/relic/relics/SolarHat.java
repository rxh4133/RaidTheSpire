package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ServerDataHandler;

public class SolarHat extends Relic{
	private static final long serialVersionUID = 1L;

	public SolarHat() {
		super("Solar Hat", Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.setMaxEnergy(owner.getMaxEnergy() + 1);
		owner.addListener(this);
		return this;
	}
	
	public Relic copyRelic() {
		return new SolarHat();
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			e.takeTrueDamage(1);
		}
	}
}
