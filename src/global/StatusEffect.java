package global;

import java.io.Serializable;

import server.EntityListener;

public class StatusEffect implements Serializable, EntityListener{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int value;
	
	public StatusEffect(String n, int v) {
		name = n;
		value = v;
	}

	public void preTurn(Entity e) {
		
	}
	
	public void postTurn(Entity e) {
		
	}
	
	public boolean equals(Object obj) {
		return obj instanceof StatusEffect && ((StatusEffect) obj).name.equals(name);
	}
	
	public String toString() {
		return name + " " + value;
	}

	@Override
	public void notify(Entity entity, String message, Object data) {
		
	}
}
