package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import server.ServerDataHandler;

public class TakeAdvantage extends Card{
	private static final long serialVersionUID = 1L;

	public TakeAdvantage(ServerDataHandler sdh) {
		super(1, TP.C_V_TAKEADVANTAGE_N, TP.C_V_TAKEADVANTAGE_D, TP.C_V_TAKEADVANTAGE_F, Rarity.COMMON, CardType.ATTACK, true, false, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(5, play);
		play.damageDealtOut(dealt, name);
		if(play.getSE("Frail") != null){
			play.addEnergy(1);	
			play.drawCards(1);
		}

	}

	public void playUpgraded(Player play, int target) {
		tinp();
		int dealt = getETarget(target).takeAttackDamage(7, play);
		play.damageDealtOut(dealt, name);
		if(play.getSE("Frail") != null || play.getSE("Vulnerable") != null){
			play.addEnergy(1);	
			play.drawCards(1);
		}
	}
}
