package global;

import java.util.ArrayList;

import server.ServerDataHandler;

public class EnemyManager {
	
	private ServerDataHandler dataHandler;
	private ArrayList<Enemy> myOnlyFight;
	
	public EnemyManager(ServerDataHandler sdh) {
		dataHandler = sdh;
	}
	
	public ArrayList<Enemy> getEnemiesForFight(int fightnum){
		myOnlyFight = new ArrayList<Enemy>();
		return myOnlyFight;
	}
}
