package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Evasion;
import server.ServerDataHandler;

public class Evade extends Card {
	private static final long serialVersionUID = 1L;

	public Evade(ServerDataHandler sdh) {
		super(2, "Evade", Rarity.UNCOMMON, CardType.SKILL, sdh);
	}
	
	public Evade(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Evade", Rarity.UNCOMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Gain 1 Evasion. (The next attack against you this turn will instead hit one of your allies)";
		flavor = "You don't have to outrun the bear, you just have to outrun your friend.";
	}
	
	public void play(Player p, int target) {
		tinp();
		p.addSE(new Evasion(1, dataHandler, p));
	}
	
	public void playUpgraded(Player p, int target) {
		tinp();
		p.addSE(new Evasion(2, dataHandler, p));
	}
	
	public Card copyCard() {
		return new Evade(dataHandler, playable, upgraded);
	}

}
