package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.MaxMissing;
import server.ServerDataHandler;

public class Salvage extends Card {
	private static final long serialVersionUID = 1L;

	public Salvage(ServerDataHandler sdh) {
		super(2, "Salvage", Rarity.UNCOMMON, CardType.SKILL, sdh);
	}
	
	public Salvage(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(2, "Salvage", Rarity.UNCOMMON, CardType.SKILL, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Heal 10. Lose 20 (15) max hp for the rest of the fight. Exhaust.";
		flavor = "Make the most of what's left of you.";
	}
	
	public CardResult prePlay(Player play, int index) {
		tinp();
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int target) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(20, play));
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(15, play));
	}
	
	public Card copyCard() {
		return new Salvage(dataHandler, playable, upgraded);
	}

}
