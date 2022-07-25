package global.statuseffect.statuseffects;

import java.util.ArrayList;
import java.util.Collections;

import global.EntityListenerMessage;
import global.Enemy;
import global.Entity;
import global.NotifyPayload;
import global.Player;
import global.statuseffect.StatusEffect;
import server.ActionInteruptException;
import server.ServerDataHandler;

public class Evasion extends StatusEffect {
	private static final long serialVersionUID = 1L;

	private transient ServerDataHandler dataHandler;

	public Evasion(int v, ServerDataHandler sdh, Entity appliedTo) {
		super("Evasion", v, false, true);
		dataHandler = sdh;
	}

	@Override
	public void notify(Entity entity, EntityListenerMessage message, NotifyPayload data) throws ActionInteruptException {
		if(message.is(EntityListenerMessage.TURN_START)) {
			entity.removeSE(this);
		}else if(message.is(EntityListenerMessage.ATTACKED)) {
			if(value > 0) {
				if(entity instanceof Player) {
					ArrayList<Player> players = new ArrayList<Player>();
					players.addAll(dataHandler.players);
					if(players.size() > 1) {
						players.remove(entity);
						Collections.shuffle(players);
						entity.reduceSE(this, 1);
						players.get(0).takeAttackDamage(data.n, data.e);
						throw new ActionInteruptException();
					}
				}
				if(entity instanceof Enemy) {
					ArrayList<Enemy> enemies = dataHandler.enemies;
					if(enemies.size() > 1) {
						enemies.remove(entity);
						Collections.shuffle(enemies);
						entity.reduceSE(this, 1);
						enemies.get(0).takeAttackDamage(data.n, data.e);
						throw new ActionInteruptException();
					}
				}
			}
		}
	}

}
