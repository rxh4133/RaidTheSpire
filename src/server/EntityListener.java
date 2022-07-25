package server;

import global.EntityListenerMessage;
import global.Entity;
import global.NotifyPayload;

public interface EntityListener extends Comparable<EntityListener>{
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data);
	public int getPriority();
}
