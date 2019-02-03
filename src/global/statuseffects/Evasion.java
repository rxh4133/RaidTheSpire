package global.statuseffects;

import java.util.ArrayList;
import java.util.Collections;

import global.Enemy;
import global.Entity;
import global.Player;
import global.StatusEffect;
import server.AttackFailedException;
import server.ServerDataHandler;

public class Evasion extends StatusEffect {
	private static final long serialVersionUID = 1L;
	
	private ServerDataHandler dataHandler;

	public Evasion(int v, ServerDataHandler sdh) {
		super("Evasion", v);
		dataHandler = sdh;
	}
	
	public void preTurn(Entity e) {
		e.removeSE(this);
	}
	
	@Override
	public void notify(Entity entity, String message, Object data) throws AttackFailedException {
		if(entity instanceof Player) {
			ArrayList<Player> players = dataHandler.players;
			if(players.size() > 1) {
				Object[] attackData = (Object[]) data;
				players.remove(entity);
				Collections.shuffle(players);
				entity.reduceSE(this, 1);
				players.get(0).takeAttackDamage((int) attackData[0], (Entity) attackData[1]);
				throw new AttackFailedException();
			}
		}
		if(entity instanceof Enemy) {
			ArrayList<Enemy> enemies = dataHandler.enemies;
			if(enemies.size() > 1) {
				Object[] attackData = (Object[]) data;
				enemies.remove(entity);
				Collections.shuffle(enemies);
				entity.reduceSE(this, 1);
				enemies.get(0).takeAttackDamage((int) attackData[0], (Entity) attackData[1]);
				throw new AttackFailedException();
			}
		}

	}

}
