package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Relic;

public class RelicManager {
	
	private ArrayList<Relic> relics;
	
	public RelicManager() {
		generateRelics();
	}
	
	public Relic getRandomRelic() {
		//do this better later
		Collections.shuffle(relics);
		return relics.get(0);
	}
	
	public void generateRelics() {
		relics = new ArrayList<Relic>();
		relics.add(new Relic(""));
	}
}
