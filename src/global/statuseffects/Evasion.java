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

	private transient ServerDataHandler dataHandler;

	public Evasion(int v, ServerDataHandler sdh, Entity appliedTo) {
		super("Evasion", v);
		dataHandler = sdh;
		if(appliedTo.getSE("Evasion") == null) {
			appliedTo.addAttackedSub(this);
		}
	}

	public void preTurn(Entity e) {
		e.removeSE(this);
		e.removeAttackedSub(this);
	}
	
	public void onRemove(Entity e) {
		e.removeAttackedSub(this);
	}

	@Override
	public void notify(Entity entity, String message, Object data) throws AttackFailedException {
		if(value > 0) {
			if(entity instanceof Player) {
				ArrayList<Player> players = new ArrayList<Player>();
				players.addAll(dataHandler.players);
				if(players.size() > 1) {
					Object[] attackData = (Object[]) data;
					players.remove(entity);
					Collections.shuffle(players);
					boolean stillLeft = entity.reduceSE(this, 1);
					if(!stillLeft) {
						entity.removeAttackedSub(this);
					}
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
					boolean stillLeft = entity.reduceSE(this, 1);
					if(!stillLeft) {
						entity.removeAttackedSub(this);
					}
					enemies.get(0).takeAttackDamage((int) attackData[0], (Entity) attackData[1]);
					throw new AttackFailedException();
				}
			}
		}
	}

}
