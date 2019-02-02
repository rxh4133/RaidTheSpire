package global;

import java.io.Serializable;

import server.EntityListener;
import server.ServerDataHandler;

public class Relic implements Serializable, EntityListener{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	protected ServerDataHandler dataHandler;
	
	public Relic(String name, ServerDataHandler sdh) {
		this.name = name;
		dataHandler = sdh;
	}

	public Relic onAdd() {
		return null;
	}
	
	public String toString() {
		return name;
	}

	@Override
	public void notify(Entity entity, String message, Object data) {
		
	}
}
