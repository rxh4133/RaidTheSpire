package global;

import java.io.Serializable;

import server.ServerDataHandler;

public abstract class CardPlayCallBack implements Serializable {
	protected transient ServerDataHandler sdh;
	
	public CardPlayCallBack(ServerDataHandler sdh) {
		this.sdh = sdh;
	}
	
	public void play(Player player, int target) {
		
	}
	
	public void playUpgraded(Player player, int target) {
		play(player, target);
	}
}
