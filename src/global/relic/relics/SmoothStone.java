package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import global.statuseffect.statuseffects.Dexterity;
import server.ServerDataHandler;

public class SmoothStone extends Relic{
	private static final long serialVersionUID = 1L;

	public SmoothStone() {
		super("Smooth Stone", Rarity.COMMON);
		description = "Start every fight with an extra dexterity.";
		flavor = "If you can dodge a stone, you can dodge a strike.";
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addListener(this);
		return this;
	}
	
	public Relic copyRelic() {
		return new SmoothStone();
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.FIGHT_START)) {
			e.addSE(new Dexterity(1));
		}
	}

}