package global.card.cards;

import global.Player;
import global.Rarity;
import global.card.Card;
import global.card.CardType;
import server.CardFailException;
import server.ServerDataHandler;

public class ShipLoadOfStrikes extends Card{
	private static final long serialVersionUID = 1L;
	
	public void setTextStuff() {
		description = "Deal the damage of 6 attacks at once. That's 36 (54).";
		flavor = "Excuse me, enemy, could you wait a second? I just have to pop all this bubble wrap!";
	}

	public ShipLoadOfStrikes(ServerDataHandler sdh) {
		super(1, "Ship Load of Strikes", Rarity.MYTHIC, CardType.ATTACK, sdh);
	}
	
	public ShipLoadOfStrikes(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Ship Load of Strikes", Rarity.MYTHIC, CardType.ATTACK, sdh);
	}
	
	public Card copyCard() {
		return new ShipLoadOfStrikes(dataHandler, playable, upgraded);
	}
	
	public void play(Player player, int target) throws CardFailException{
		tinp();
		int dealt = getETarget(target).takeAttackDamage(36, player);
		player.damageDealtOut(dealt, name);
	}
	
	public void playUpgraded(Player player, int target) throws CardFailException{
		tinp();
		int dealt = getETarget(target).takeAttackDamage(54, player);
		player.damageDealtOut(dealt, name);
	}

}
