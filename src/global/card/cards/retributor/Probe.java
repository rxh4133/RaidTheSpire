package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Strength;
import server.ServerDataHandler;

public class Probe extends Card {
	private static final long serialVersionUID = 1L;

	public Probe(ServerDataHandler sdh) {
		super(1, "Probe", Rarity.RARE, CardType.ATTACK, sdh);
	}
	
	public Probe(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Probe", Rarity.RARE, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Deal 6 (3) damage. If it deals none, gain 3 (4) Strength.";
		flavor = "Judge your opponent's defense, look for an opening.";
	}

	public void play(Player play, int target) {
		int dealt = getETarget(target).takeAttackDamage(6, play);
		play.damageDealtOut(dealt, name);
		if(dealt == 0) {
			play.addSE(new Strength(3));
		}
	}
	
	public void playUpgraded(Player play, int target) {
		int dealt = getETarget(target).takeAttackDamage(3, play);
		play.damageDealtOut(dealt, name);
		if(dealt == 0) {
			play.addSE(new Strength(4));
		}
	}
	
	public Card copyCard() {
		return new Probe(dataHandler, playable, upgraded);
	}
}
