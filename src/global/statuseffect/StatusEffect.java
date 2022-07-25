package global.statuseffect;

import java.io.Serializable;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import server.ActionInteruptException;
import server.EntityListener;

public class StatusEffect implements Serializable, EntityListener, Cloneable{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int value;
	public boolean hidden;
	private boolean isDebuff;
	private boolean isBuff;
	protected int priority;
	
	public StatusEffect(String n, int v, boolean id, boolean ib) {
		name = n;
		value = v;
		isDebuff = id;
		isBuff = ib;
		priority = 25;
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
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) throws ActionInteruptException {
		
	}

	@Override
	public int compareTo(EntityListener o) {
		return getPriority() - o.getPriority();
	}

	@Override
	public int getPriority() {
		return priority;
	}
	
}
