package global.statuseffects;

import java.util.ArrayList;
import java.util.Collections;

import global.ELM;
import global.Enemy;
import global.Entity;
import global.Player;
import global.StatusEffect;
import server.ActionInteruptException;
import server.ServerDataHandler;

public class Evasion extends StatusEffect {
	private static final long serialVersionUID = 1L;

	private transient ServerDataHandler dataHandler;

	public Evasion(int v, ServerDataHandler sdh, Entity appliedTo) {
		super("Evasion", v);
		dataHandler = sdh;
	}

	@Override
	public void notify(Entity entity, ELM message, Object data) throws ActionInteruptException {
		if(message.is(ELM.TURN_START)) {
			entity.removeSE(this);
		}else if(message.is(ELM.ATTACKED)) {
			if(value > 0) {
				if(entity instanceof Player) {
					ArrayList<Player> players = new ArrayList<Player>();
					players.addAll(dataHandler.players);
					if(players.size() > 1) {
						Object[] attackData = (Object[]) data;
						players.remove(entity);
						Collections.shuffle(players);
						entity.reduceSE(this, 1);
						players.get(0).takeAttackDamage((int) attackData[0], (Entity) attackData[1]);
						throw new ActionInteruptException();
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
						throw new ActionInteruptException();
					}
				}
			}
		}
	}

}
