package global.relic.relics;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import global.TP;
import global.relic.Relic;
import server.ServerDataHandler;

public class ArtistsPencil extends Relic{
	private static final long serialVersionUID = 1L;

	public ArtistsPencil() {
		super(TP.R_ALL_ARTISTSPENCIL_N, TP.R_ALL_ARTISTSPENCIL_D, TP.R_ALL_ARTISTSPENCIL_F, Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, EntityListenerMessage m, NotifyPayload o) {
		if(m.is(EntityListenerMessage.TURN_START)) {
			((Player) e).drawCards(1);
		}
	}
}
