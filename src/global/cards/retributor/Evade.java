package global.cards.retributor;

import global.Card;
import global.Player;
import global.statuseffects.Evasion;
import server.ServerDataHandler;

public class Evade extends Card {
	private static final long serialVersionUID = 1L;

	public Evade(ServerDataHandler sdh) {
		super(2, "Evade", 2, sdh);
	}
	
	public void play(Player p, int target) {
		p.addSE(new Evasion(1, dataHandler));
	}
	
	public void playUpgraded(Player p, int target) {
		p.addSE(new Evasion(2, dataHandler));
	}
	
	public Card copyCard() {
		return new Evade(dataHandler);
	}

}
