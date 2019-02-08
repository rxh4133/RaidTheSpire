package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.statuseffects.Vulnerable;
import server.ServerDataHandler;

public class PowerBlow extends Card {
	private static final long serialVersionUID = 1L;

	public PowerBlow(ServerDataHandler sdh) {
		super(1, "Power Blow", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		Enemy te = getETarget(target);
		int dealt = te.takeAttackDamage(7, play);
		play.damageDealtOut(dealt, name);
		getETarget(target).addSE(new Vulnerable(2,te));
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		Enemy te = getETarget(target);
		int dealt = te.takeAttackDamage(11, play);
		play.damageDealtOut(dealt, name);
		getETarget(target).addSE(new Vulnerable(2,te));
	}
	
	public Card copyCard() {
		return new PowerBlow(dataHandler);
	}

}
