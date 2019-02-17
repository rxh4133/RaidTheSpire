package global.statuseffect;

import java.io.Serializable;

import global.ELM;
import global.Entity;
import server.ActionInteruptException;
import server.EntityListener;

public class StatusEffect implements Serializable, EntityListener, Cloneable{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int value;
	public boolean hidden;
	private boolean isDebuff;
	private boolean isBuff;
	
	public StatusEffect(String n, int v, boolean id, boolean ib) {
		name = n;
		value = v;
		isDebuff = id;
		isBuff = ib;
	}
	
	public StatusEffect(String n, int v) {
		name = n;
		value = v;
	}
	
	public boolean isDebuff() {
		return isDebuff;
	}
	
	public boolean isBuff() {
		return isBuff;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof StatusEffect && ((StatusEffect) obj).name.equals(name);
	}
	
	public String toString() {
		return name + " " + value;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public void notify(Entity entity, ELM message, Object data) throws ActionInteruptException {
		
	}
}
