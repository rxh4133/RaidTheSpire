package server.managers;

import java.io.Serializable;
import java.util.ArrayList;

import global.Enemy;
import global.enemies.BasicEnemy;
import server.ServerDataHandler;

public class EnemyManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private transient ServerDataHandler dataHandler;
	private transient ArrayList<Enemy> myOnlyFight;
	
	public EnemyManager(ServerDataHandler sdh) {
		dataHandler = sdh;
		
	}
	
	public ArrayList<Enemy> getEnemiesForFight(int fightnum){
		myOnlyFight = new ArrayList<Enemy>();
		myOnlyFight.add(new BasicEnemy(dataHandler));
		return myOnlyFight;
	}
}
