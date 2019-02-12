package global.relic;

import java.io.Serializable;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import server.EntityListener;
import server.ServerDataHandler;

public class Relic implements Serializable, EntityListener{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Rarity rarity;
	
	protected transient ServerDataHandler dataHandler;
	
	public Relic(String name, Rarity rarity) {
		this.name = name;
		this.rarity = rarity;
	}

	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		return this;
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	
	public String toString() {
		return name;
	}
	
	public Relic copyRelic() {
		throw new RuntimeException("You must override copyRelic");
	}

	@Override
	public void notify(Entity entity, ELM message, Object data) {
		
	}
}
