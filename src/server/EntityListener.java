package server;

import global.Entity;

public interface EntityListener {
	public void notify(Entity entity, String message, Object data) throws AttackFailedException;
}
