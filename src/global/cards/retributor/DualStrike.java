package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.ServerDataHandler;

public class DualStrike extends Card{
	private static final long serialVersionUID = 1L;

	public DualStrike(ServerDataHandler sdh) {
		super(1, "Dual Strike", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public void play(Player play, int target) {
		int dealt = dataHandler.enemies.get(target).takeAttackDamage(3 + play.getStrength(), play);
		play.damageDealtOut(dealt, name);
		dealt = dataHandler.enemies.get(target).takeAttackDamage(3 + play.getStrength(), play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}

	public void playUpgraded(Player play, int target) {
		int dealt = dataHandler.enemies.get(target).takeAttackDamage(4 + play.getStrength(), play);
		play.damageDealtOut(dealt, name);
		dealt = dataHandler.enemies.get(target).takeAttackDamage(4 + play.getStrength(), play);
		play.damageDealtOut(dealt, name);
		play.drawCards(1);
	}
	
	public Card copyCard() {
		return new DualStrike(dataHandler);
	}
}
