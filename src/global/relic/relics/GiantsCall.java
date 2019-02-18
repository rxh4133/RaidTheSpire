package global.relic.relics;

import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ServerDataHandler;

public class GiantsCall extends Relic {
	private static final long serialVersionUID = 1L;

	public GiantsCall() {
		super(TP.R_ALL_GIANTSCALL_N, TP.R_ALL_GIANTSCALL_D, TP.R_ALL_GIANTSCALL_F, Rarity.COMMON);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		owner.addMaxHealth((int) (owner.getMaxHealth() * .2));
		return this;
	}
}
