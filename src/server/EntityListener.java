package server;

import global.ELM;
import global.Entity;

public interface EntityListener {
	public void notify(Entity entity, ELM message, Object data);
}
