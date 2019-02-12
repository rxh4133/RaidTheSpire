package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Rarity;
import global.relic.Relic;
import global.relic.relics.ArtistsPencil;
import global.relic.relics.GiantsCall;
import global.relic.relics.PenNibba;
import global.relic.relics.SmoothStone;
import global.relic.relics.SolarHat;
import global.relic.relics.Vajra;
import global.relic.relics.WildBloom;

public class RelicManager {

	private ArrayList<Relic> comRelics;
	private ArrayList<Relic> unRelics;
	private ArrayList<Relic> rareRelics;

	public RelicManager() {
		generateRelics();
	}

	public Relic getRandomRelic() {
		Collections.shuffle(comRelics);
		Collections.shuffle(unRelics);
		Collections.shuffle(rareRelics);
		int rand = (int) (Math.random() * 100);
		if(rand < 12.5) {
			if(rareRelics.size() > 0) {
				return rareRelics.remove(0);
			}else {
				return new Relic("PlaceholderRareRelic", Rarity.RARE);
			}
		}else if(rand < 50) {
			if(unRelics.size() > 0) {
				return unRelics.remove(0);
			}else {
				return new Relic("PlaceholderUncommonRelic", Rarity.UNCOMMON);
			}
		}else {
			if(comRelics.size() > 0) {
				return comRelics.remove(0);
			}else {
				return new Relic("PlaceholderCommonRelic", Rarity.COMMON);
			}
		}
	}

	public void generateRelics() {
		comRelics = new ArrayList<Relic>();
		unRelics = new ArrayList<Relic>();
		rareRelics = new ArrayList<Relic>();
		comRelics.add(new GiantsCall());
		comRelics.add(new Vajra());
		comRelics.add(new SmoothStone());
		unRelics.add(new WildBloom());
		unRelics.add(new PenNibba());
		rareRelics.add(new ArtistsPencil());
		rareRelics.add(new SolarHat());
	}
}
