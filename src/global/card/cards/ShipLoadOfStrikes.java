package global.card.cards;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import server.CardFailException;
import server.ServerDataHandler;

public class ShipLoadOfStrikes extends Card{
	private static final long serialVersionUID = 1L;

	public ShipLoadOfStrikes(ServerDataHandler sdh) {
		super(1, TP.C_ALL_SHIPLOADOFSTRIKES_N, TP.C_ALL_SHIPLOADOFSTRIKES_D, TP.C_ALL_SHIPLOADOFSTRIKES_F, Rarity.MYTHIC, CardType.ATTACK, true, false, sdh);
	}
	
	public void play(Player player, int entityTarget, int cardTarget) throws CardFailException{
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(36, player);
		player.damageDealtOut(dealt, name);
	}
	
	public void playUpgraded(Player player, int entityTarget, int cardTarget) throws CardFailException{
		tinp();
		int dealt = getETarget(entityTarget).takeAttackDamage(54, player);
		player.damageDealtOut(dealt, name);
	}

}
