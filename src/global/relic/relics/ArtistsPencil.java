package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ServerDataHandler;

public class ArtistsPencil extends Relic{
	private static final long serialVersionUID = 1L;

	public ArtistsPencil() {
		super("Artist's Pencil", Rarity.RARE);
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.addListener(this);
		return this;
	}
	
	public Relic copyRelic() {
		return new ArtistsPencil();
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			((Player) e).drawCards(1);
		}
	}
}
