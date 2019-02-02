package global.cards.retributor;

import global.Card;
import global.Player;
import server.ServerDataHandler;

public class DualStrike extends Card{
	private static final long serialVersionUID = 1L;

	public DualStrike(ServerDataHandler sdh) {
		super(1, "Dual Strike", 1, sdh);
	}
	
	public void play(Player play, int target) {
		dataHandler.enemies.get(target).takeAttackDamage(3 + play.getStrength(), play);
		dataHandler.enemies.get(target).takeAttackDamage(3 + play.getStrength(), play);
		play.drawCards(1);
	}

	public void playUpgraded(Player play, int target) {
		dataHandler.enemies.get(target).takeAttackDamage(4 + play.getStrength(), play);
		dataHandler.enemies.get(target).takeAttackDamage(4 + play.getStrength(), play);
		play.drawCards(1);
	}
	
	public Card copyCard() {
		return new DualStrike(dataHandler);
	}
}
