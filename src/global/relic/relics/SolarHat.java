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
		description = "Gain 1 extra max energy. At the start of your turn, take 1 true damage.";
		flavor = "The creator's soldering skills weren't very good, you've got a short.";
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
