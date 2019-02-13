package global.card.cards.revenant;

import global.card.Card;
import global.Enemy;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Vulnerable;
import server.ServerDataHandler;

public class PowerBlow extends Card {
	private static final long serialVersionUID = 1L;

	public PowerBlow(ServerDataHandler sdh) {
		super(1, "Power Blow", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public PowerBlow(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Power Blow", Rarity.COMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Deal 7 (11) damage. Apply 2 Vulnerable.";
		flavor = "Knock 'em off balance, then laugh as your friends beat 'em up!";
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
		return new PowerBlow(dataHandler, playable, upgraded);
	}

}
