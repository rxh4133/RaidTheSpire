package server.managers;

import java.io.Serializable;
import java.util.ArrayList;

import global.Enemy;
import global.enemies.Apparitionist;
import global.enemies.BasicEnemy;
import global.enemies.WrithingMass;
import server.ServerDataHandler;

public class EnemyManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private transient ServerDataHandler dataHandler;
	
	public EnemyManager(ServerDataHandler sdh) {
		dataHandler = sdh;
		
	}
	
	public ArrayList<Enemy> getEnemiesForFight(int fightnum){
		ArrayList<Enemy> fight = new ArrayList<Enemy>();
		if(fightnum == 0) {
			fight.add(new BasicEnemy(dataHandler));
		}else if(fightnum == 1){
			fight.add(new WrithingMass(dataHandler));
		}else {
			fight.add(new Apparitionist(dataHandler));
		}
		return fight;
	}
}
