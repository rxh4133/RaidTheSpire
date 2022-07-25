package global.relic;

import java.io.Serializable;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.Rarity;
import server.EntityListener;
import server.ServerDataHandler;

public class Relic implements Serializable, EntityListener, Cloneable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Rarity rarity;
	protected String description;
	protected String flavor;
	protected int priority;
	
	protected transient ServerDataHandler dataHandler;
	
	public Relic(String name, String desc, String flav, Rarity rarity) {
		this.name = name;
		this.rarity = rarity;
		description = desc;
		flavor = flav;
		priority = 25;
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
	
	public String getDesc() {
		return description;
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public Relic clone() {
		try {
			return (Relic) super.clone();
		}catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace();
		}catch(ClassCastException cce) {
			cce.printStackTrace();
		}
		return null;
	}

	@Override
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) {
		
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
