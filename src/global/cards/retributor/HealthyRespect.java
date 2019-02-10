package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.statuseffects.Respect;
import server.ServerDataHandler;

public class HealthyRespect extends Card {
	private static final long serialVersionUID = 1L;

	public HealthyRespect(ServerDataHandler sdh) {
		super(2, "Healthy Respect", Rarity.UNCOMMON, CardType.POWER, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.addSE(new Respect());
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.addSE(new Respect());
	}
	
	public Card copyCard() {
		return new HealthyRespect(dataHandler);
	}

}
