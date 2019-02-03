package global.cards;

import global.Card;
import global.Player;
import global.statuseffects.Thorns;
import server.CardFailException;
import server.ServerDataHandler;

public class EnmitysMight extends Card {
	private static final long serialVersionUID = 1L;

	public EnmitysMight(ServerDataHandler sdh) {
		super(0, "Enmity's Might", 0, sdh);
	}
	
	public void play(Player play, int target) throws CardFailException{
		if(target < dataHandler.players.size()) {
			dataHandler.players.get(target).addSE(new Thorns(2, play));
		}else {
			throw new CardFailException();
		}
	}
	
	public void playUpgraded(Player play, int target) throws CardFailException{
		if(target < dataHandler.players.size()) {
			dataHandler.players.get(target).addSE(new Thorns(3, play));
		}else {
			throw new CardFailException();
		}
	}
	
	public Card copyCard() {
		return new EnmitysMight(dataHandler);
	}

}
