package server.managers;

import java.util.ArrayList;
import java.util.Collections;

import global.Rarity;
import global.relic.Relic;
import global.relic.relics.ArtistsPencil;
import global.relic.relics.CorrugatedShipper;
import global.relic.relics.GiantsCall;
import global.relic.relics.OogitsYoYo;
import global.relic.relics.PenNibba;
import global.relic.relics.SmoothStone;
import global.relic.relics.SolarHat;
import global.relic.relics.TheGauntlet;
import global.relic.relics.Vajra;
import global.relic.relics.WildBloom;

public class RelicManager {

	private ArrayList<Relic> comRelics;
	private ArrayList<Relic> unRelics;
	private ArrayList<Relic> rareRelics;
	
	private ArrayList<Relic> workingComRelics;
	private ArrayList<Relic> workingUnRelics;
	private ArrayList<Relic> workingRareRelics;

	public RelicManager() {
		generateRelics();
	}

	public Relic getRandomRelic() {
		Collections.shuffle(workingComRelics);
		Collections.shuffle(workingUnRelics);
		Collections.shuffle(workingRareRelics);
		int rand = (int) (Math.random() * 100);
		if(rand < 12.5) {
			if(workingRareRelics.size() > 0) {
				return workingRareRelics.remove(0);
			}else {
				return new Relic("PlaceholderRareRelic", Rarity.RARE);
			}
		}else if(rand < 50) {
			if(workingUnRelics.size() > 0) {
				return workingUnRelics.remove(0);
			}else {
				return new Relic("PlaceholderUncommonRelic", Rarity.UNCOMMON);
			}
		}else {
			if(workingComRelics.size() > 0) {
				return workingComRelics.remove(0);
			}else {
				return new Relic("PlaceholderCommonRelic", Rarity.COMMON);
			}
		}
	}
	
	public void returnRelic(Relic relic) {
		if(relic.getRarity().equals(Rarity.RARE)) {
			workingRareRelics.add(relic);
		}else if(relic.getRarity().equals(Rarity.UNCOMMON)) {
			workingUnRelics.add(relic);
		}else if(relic.getRarity().equals(Rarity.COMMON)) {
			workingComRelics.add(relic);
		}
	}

	public void generateRelics() {
		comRelics = new ArrayList<Relic>();
		unRelics = new ArrayList<Relic>();
		rareRelics = new ArrayList<Relic>();
		workingComRelics = new ArrayList<Relic>();
		workingUnRelics = new ArrayList<Relic>();
		workingRareRelics = new ArrayList<Relic>();
		
		
		comRelics.add(new GiantsCall());
		comRelics.add(new Vajra());
		comRelics.add(new SmoothStone());
		comRelics.add(new CorrugatedShipper());
		
		comRelics.add(new GiantsCall());
		comRelics.add(new Vajra());
		comRelics.add(new SmoothStone());
		comRelics.add(new CorrugatedShipper());
		
		comRelics.add(new GiantsCall());
		comRelics.add(new Vajra());
		comRelics.add(new SmoothStone());
		comRelics.add(new CorrugatedShipper());
		
		unRelics.add(new WildBloom());
		unRelics.add(new PenNibba());
		unRelics.add(new OogitsYoYo());
		
		rareRelics.add(new ArtistsPencil());
		rareRelics.add(new SolarHat());
		rareRelics.add(new TheGauntlet());
		
		workingComRelics.addAll(comRelics);
		workingUnRelics.addAll(unRelics);
		workingRareRelics.addAll(rareRelics);
	}
}
