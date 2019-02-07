package global.cards.retributor;

import global.Card;
import global.CardType;
import global.Player;
import global.Rarity;
import global.statuseffects.Thorns;
import server.CardFailException;
import server.ServerDataHandler;

public class EnmitysMight extends Card {
	private static final long serialVersionUID = 1L;

	public EnmitysMight(ServerDataHandler sdh) {
		super(0, "Enmity's Might", Rarity.STARTER, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(2, play));
	}
	
	public void playUpgraded(Player play, int target) throws CardFailException{
		tinp();
		getPTarget(target).addSE(new Thorns(3, play));
	}
	
	public Card copyCard() {
		return new EnmitysMight(dataHandler);
	}

}
