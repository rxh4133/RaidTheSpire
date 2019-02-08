package global.cards.revenant;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import server.ServerDataHandler;

public class TakeAdvantage extends Card{
	private static final long serialVersionUID = 1L;

	public TakeAdvantage(ServerDataHandler sdh) {
		super(1, "Take Advantage", Rarity.COMMON, CardType.ATTACK, sdh);
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
		if(play.getSE("Frail") != null){
			play.addEnergy(1);	
			play.drawCards(1);
		}
	}
}
