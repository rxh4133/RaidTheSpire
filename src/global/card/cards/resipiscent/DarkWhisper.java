package global.card.cards.resipiscent;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardType;
import server.ServerDataHandler;

public class DarkWhisper extends Card {
	private static final long serialVersionUID = 1L;

	public DarkWhisper(ServerDataHandler sdh) {
		super(0, TP.C_S_DARKWHISPER_N, TP.C_S_DARKWHISPER_D, TP.C_S_DARKWHISPER_F, Rarity.RARE, CardType.SKILL, true, false, sdh);
	}

	public void play(Player player, int entityTarget, int cardTarget) {
		tinp();
		player.takeTrueDamage(1);
		player.addEnergy(2);
		player.addCardToDraw(this.clone());
		player.addCardToDraw(this.clone());
	}
	
	public void playUpgraded(Player player, int entityTarget, int cardTarget) {
		tinp();
		player.takeTrueDamage(1);
		player.addEnergy(3);
		player.addCardToDraw(this.clone());	
		player.addCardToDraw(this.clone());
	}
}
