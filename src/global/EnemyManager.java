package global;

import java.io.Serializable;
import java.util.ArrayList;

import server.ServerDataHandler;

public class EnemyManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private transient ServerDataHandler dataHandler;
	private transient ArrayList<Enemy> myOnlyFight;
	
	public EnemyManager(ServerDataHandler sdh) {
		dataHandler = sdh;
		myOnlyFight = new ArrayList<Enemy>();
		Enemy e = new Enemy() {
			private static final long serialVersionUID = 1L;
			public EnemyAction takeAction() {
				return new EnemyAction() {
					private static final long serialVersionUID = 1L;

					public void doAction() {
						for(Player p: dataHandler.players) {
							p.takeAttackDamage(10);
						}
					}
				};
			}
		};
		e.addMaxHealth(30);
		e.addDeathSub(dataHandler);
		myOnlyFight.add(e);
	}
	
	public ArrayList<Enemy> getEnemiesForFight(int fightnum){
		return myOnlyFight;
	}
}
