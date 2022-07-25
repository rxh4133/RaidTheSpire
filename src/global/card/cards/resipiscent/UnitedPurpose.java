package global.card.cards.resipiscent;

import global.Player;
import global.Rarity;
import global.TP;
import global.card.Card;
import global.card.CardResult;
import global.card.CardType;
import global.statuseffect.statuseffects.United;
import server.CardFailException;
import server.ServerDataHandler;

public class UnitedPurpose extends Card {
	private static final long serialVersionUID = 1L;

	public UnitedPurpose(ServerDataHandler sdh) {
		super(3, TP.C_S_UNITEDPURPOSE_N, TP.C_S_UNITEDPURPOSE_D, TP.C_S_UNITEDPURPOSE_F, Rarity.RARE, CardType.POWER, true, false, sdh);
	}
	
	public CardResult prePlay(Player player, int index) {
		if(!playable) {
			throw new CardFailException("Tried to play an unplayable card");
		}
		player.removeCardFromHand(index);
		return CardResult.REMOVE;
	}

	@Override
	protected void playLogic(Player play, int entityTarget, int cardTarget) {
		for(Player p: dataHandler.players) {
			p.addSE(new United(dataHandler.players.size()));
		}
	}
	
	@Override
	protected void playUpgradedLogic(Player play, int entityTarget, int cardTarget) {
		play(play, entityTarget, -1);
	}
}
