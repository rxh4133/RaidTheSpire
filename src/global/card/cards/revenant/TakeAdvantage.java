package global.card.cards.revenant;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import server.ServerDataHandler;

public class TakeAdvantage extends Card{
	private static final long serialVersionUID = 1L;

	public TakeAdvantage(ServerDataHandler sdh) {
		super(1, "Take Advantage", Rarity.COMMON, CardType.ATTACK, sdh);
	}
	
	public TakeAdvantage(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Take Advantage", Rarity.COMMON, CardType.ATTACK, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Deal 5 (7) damage. If the enemy is Frail (or Vulnerable), gain an energy and draw a card.";
		flavor = "Is your enemy in pain? Stop and help theGO FUCKING KILL THEM";
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
	
	public Card copyCard() {
		return new TakeAdvantage(dataHandler, playable, upgraded);
	}
}
