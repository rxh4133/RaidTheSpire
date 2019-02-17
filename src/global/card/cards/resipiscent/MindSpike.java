package global.card.cards.resipiscent;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.card.Card;
import global.card.CardType;
import global.statuseffect.statuseffects.Frail;
import server.ServerDataHandler;

public class MindSpike extends Card {
	private static final long serialVersionUID = 1L;

	public MindSpike(ServerDataHandler sdh) {
		super(1, "Mind Spike", Rarity.COMMON, CardType.SKILL, true, false, sdh);		
	}
	
	public MindSpike(ServerDataHandler sdh, Boolean play, boolean upgr) {
		super(1, "Mind Spike", Rarity.COMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public Card CopyCard() {
		return new MindSpike(dataHandler, playable, upgraded);
	}
	
	public void play(Player play, int target) {
		tinp();
		Enemy targ = getETarget(target);
		targ.addSE(new Frail(2));
		targ.takeTrueDamage(7);
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		Enemy targ = getETarget(target);
		targ.addSE(new Frail(2));
		targ.takeTrueDamage(9);
	}

}
