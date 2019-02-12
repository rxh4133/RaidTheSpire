package global.relic.relics;

import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ServerDataHandler;

public class GiantsCall extends Relic {
	private static final long serialVersionUID = 1L;

	public GiantsCall() {
		super("Giant's Call", Rarity.COMMON);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addMaxHealth((int) (owner.getMaxHealth() * .2));
		return this;
	}
	
	public Relic copyRelic() {
		return new GiantsCall();
	}

}
