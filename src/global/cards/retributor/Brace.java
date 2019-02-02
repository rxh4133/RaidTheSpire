package global.cards.retributor;

import java.io.Serializable;

import global.Card;
import global.Entity;
import global.Player;
import global.statuseffects.StartDraw;
import server.EntityListener;
import server.ServerDataHandler;

public class Brace extends Card {
	private static final long serialVersionUID = 1L;

	public Brace(ServerDataHandler sdh) {
		super(1, "Brace", 1, sdh);
	}
	
	public void play(Player play, int target) {
		play.gainBlock(7);
		BEL bel = new BEL(play);
		play.addAttDamSub(bel);
	}
	
	private class BEL implements Serializable, EntityListener{
		private static final long serialVersionUID = 1L;
		private Player owner;
		
		public BEL(Player play) {
			owner = play;
		}

		@Override
		public void notify(Entity entity, String message, Object data) {
			if(owner.getBlock() == 0) {
				owner.addSE(new StartDraw(2));
			}
		}
		
		
	}

}
