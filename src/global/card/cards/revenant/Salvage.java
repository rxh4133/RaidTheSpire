package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.MaxMissing;
import server.ServerDataHandler;

public class Salvage extends Card {
	private static final long serialVersionUID = 1L;

	public Salvage(ServerDataHandler sdh) {
		super(2, TP.C_V_SALVAGE_N, TP.C_V_SALVAGE_D, TP.C_V_SALVAGE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		tinp();
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	public void play(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(20, play));
	}
	
	public void playUpgraded(Player play, int entityTarget, int cardTarget) {
		tinp();
		play.heal(10);
		play.addSE(new MaxMissing(15, play));
	}
}
