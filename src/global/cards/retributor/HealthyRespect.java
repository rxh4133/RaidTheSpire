package global.cards.retributor;

import global.Card;
import global.Player;
import global.statuseffects.Respect;
import server.ServerDataHandler;

public class HealthyRespect extends Card {
	private static final long serialVersionUID = 1L;

	public HealthyRespect(ServerDataHandler sdh) {
		super(2, "Health Respect", 2, sdh);
	}
	
	public void play(Player play, int target) {
		play.addSE(new Respect(play));
	}
	
	public void playUpgraded(Player play, int target) {
		play.addSE(new Respect(play));
	}
	
	public Card copyCard() {
		return new HealthyRespect(dataHandler);
	}

}
