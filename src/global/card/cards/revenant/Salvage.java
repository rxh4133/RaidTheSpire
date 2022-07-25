package global.card.cards.revenant;

import global.card.Card;
import global.card.CardResult;
import global.Player;
import global.Rarity;
import global.TP;
import global.card.CardType;
import global.statuseffect.statuseffects.MaxMissing;
import server.CardFailException;
import server.ServerDataHandler;

public class Salvage extends Card {
	private static final long serialVersionUID = 1L;

	public Salvage(ServerDataHandler sdh) {
		super(2, TP.C_V_SALVAGE_N, TP.C_V_SALVAGE_D, TP.C_V_SALVAGE_F, Rarity.UNCOMMON, CardType.SKILL, true, false, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		if(!playable) {
			throw new CardFailException("Tried to play card that's unplayable");
		}
		play.exhaustFromHand(index);
		return CardResult.EXHAUST;
	}
	
	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		play.heal(10);
		play.addSE(new MaxMissing(20, play));
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play.heal(10);
		play.addSE(new MaxMissing(15, play));
	}
}
