package global;

import java.io.Serializable;

import server.EntityListener;
import server.ServerDataHandler;

public class Relic implements Serializable, EntityListener{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	protected transient ServerDataHandler dataHandler;
	
	public Relic(String name) {
		this.name = name;
	}

	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		dataHandler = sdh;
		return this;
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
