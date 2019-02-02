package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Relic;
import server.ServerDataHandler;

public class RelicManager {
	
	private ArrayList<Relic> relics;
	private ServerDataHandler dataHandler;
	
	public RelicManager(ServerDataHandler sdh) {
		dataHandler = sdh;
		generateRelics();
	}
	
	public Relic getRandomRelic() {
		//do this better later
		Collections.shuffle(relics);
		return relics.get(0);
	}
	
	public void generateRelics() {
		relics = new ArrayList<Relic>();
		relics.add(new Relic("",dataHandler));
	}
}
