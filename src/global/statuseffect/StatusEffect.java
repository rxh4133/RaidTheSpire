package global.statuseffect;

import java.io.Serializable;

import global.ELM;
import global.Entity;
import server.ActionInteruptException;
import server.EntityListener;

public class StatusEffect implements Serializable, EntityListener{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int value;
	public boolean hidden;
	
	public StatusEffect(String n, int v) {
		name = n;
		value = v;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof StatusEffect && ((StatusEffect) obj).name.equals(name);
	}
	
	public String toString() {
		return name + " " + value;
	}

	@Override
	public void notify(Entity entity, ELM message, Object data) throws ActionInteruptException {
		
	}
}
