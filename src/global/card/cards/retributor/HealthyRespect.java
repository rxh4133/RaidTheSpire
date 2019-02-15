package global.card.cards.retributor;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Respect;
import server.ServerDataHandler;

public class HealthyRespect extends Card {
	private static final long serialVersionUID = 1L;

	public HealthyRespect(ServerDataHandler sdh) {
		super(1, "Healthy Respect", Rarity.UNCOMMON, CardType.POWER, sdh);
	}
	
	public HealthyRespect(ServerDataHandler sdh, boolean play, boolean upgr) {
		super(1, "Healthy Respect", Rarity.UNCOMMON, CardType.POWER, play, upgr, sdh);
	}
	
	public void setTextStuff() {
		description = "Whenever your thorns deal damage, gain that much block at the start of your next turn.";
		flavor = "With every attack, you know your enemy that much better.";
	}
	
	public CardResult prePlay(Player play, int index) {
		play.removeCardFromHand(index);
		return CardResult.REMOVE;
	}
	
	public void play(Player play, int target) {
		tinp();
		play.effects.add(new Respect());
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.addSE(new Respect());
	}
	
	public Card copyCard() {
		return new HealthyRespect(dataHandler, playable, upgraded);
	}

}
