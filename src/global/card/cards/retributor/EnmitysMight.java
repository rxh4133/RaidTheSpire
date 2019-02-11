package global.card.cards.retributor;

import global.card.Card;
import global.Player;
import global.Rarity;
import global.card.CardType;
import global.statuseffect.statuseffects.Thorns;
import server.CardFailException;
import server.ServerDataHandler;

public class EnmitysMight extends Card {
	private static final long serialVersionUID = 1L;

	public EnmitysMight(ServerDataHandler sdh) {
		super(0, "Enmity's Might", Rarity.STARTER, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(2));
	}
	
	public void playUpgraded(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(3));
	}
	
	public Card copyCard() {
		return new EnmitysMight(dataHandler);
	}

}
