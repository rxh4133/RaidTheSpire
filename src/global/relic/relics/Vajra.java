package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import global.statuseffect.statuseffects.Strength;
import server.ServerDataHandler;

public class Vajra extends Relic{
	private static final long serialVersionUID = 1L;

	public Vajra() {
		super("Vajra", Rarity.COMMON);
		description = "Start each combat with an extra Strength.";
		flavor = "It's so heavy, you use it as a deadlift weight.";
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addListener(this);
		return this;
	}
	
	public Relic copyRelic() {
		return new Vajra();
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.FIGHT_START)) {
			e.addSE(new Strength(1));
		}
	}

}
