package global.card.cards.revenant;

import global.Enemy;
import global.Player;
import global.Rarity;
import global.card.Card;
import global.card.CardResult;
import global.card.CardType;
import global.statuseffect.statuseffects.Necrotic;
import global.statuseffect.statuseffects.Poison;
import server.ServerDataHandler;

public class NecroticTouch extends Card {
	private static final long serialVersionUID = 1L;

	public NecroticTouch(ServerDataHandler sdh) {
		super(2, "Necrotic Touch", Rarity.RARE, CardType.POWER, sdh);
	}
	
	public CardResult prePlay(Player play, int index) {
		play.removeCardFromHand(index);
		return CardResult.REMOVE;
	}
	
	public void play(Player play, int target) {
		tinp();
		Enemy e = getETarget(target);
		e.addSE(new Necrotic(1));
		e.addSE(new Poison(10));
	}
	
	public Card copyCard() {
		return new NecroticTouch(dataHandler);
	}
}
