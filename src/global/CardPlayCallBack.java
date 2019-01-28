package global;

import server.ServerDataHandler;

public abstract class CardPlayCallBack {
	protected ServerDataHandler sdh;
	
	public CardPlayCallBack(ServerDataHandler sdh) {
		this.sdh = sdh;
	}
	
	public void play(Player player, int target) {
		
	}
	
	public void playUpgraded(Player player, int target) {
		play(player, target);
	}
}
