package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.ServerDataHandler;

public class Cannibalize extends Card{

	private static final long serialVersionUID = 1L;


	public Cannibalize(ServerDataHandler sdh) {
		super(1, "Cannibalize", Rarity.RARE, CardType.ATTACK, sdh);
	}

	public void play(Player play, int target) {
		play.addMaxHealth(5);
		int dealt = getETarget(target).takeAttackDamage(4, play);
		play.damageDealtOut(dealt, name);
	}
	
	public void playUpgraded(Player play, int target) {
		play.addMaxHealth(5);
		int dealt = getETarget(target).takeAttackDamage(8, play);
		play.damageDealtOut(dealt, name);
	}
	
	public Card copyCard() {
		return new Cannibalize(dataHandler);
	}
}
