package server;

import global.ELM;
import global.Entity;
import global.NotifyPayload;

public interface EntityListener extends Comparable<EntityListener>{
	public void notify(Entity entity, ELM message, NotifyPayload data);
	public int getPriority();
}
