package global.cards.retributor;

import java.io.Serializable;

import global.Card;
import global.CardType;
import global.Entity;
import global.Player;
import global.Rarity;
import global.statuseffects.StartDraw;
import server.EntityListener;
import server.ServerDataHandler;

public class Brace extends Card {
	private static final long serialVersionUID = 1L;

	public Brace(ServerDataHandler sdh) {
		super(1, "Brace", Rarity.COMMON, CardType.SKILL, sdh);
	}
	
	public void play(Player play, int target) {
		tinp();
		play.gainBlock(7);
		BEL bel = new BEL(play, 2);
		play.addAttDamSub(bel);
	}
	
	public void playUpgraded(Player play, int target) {
		tinp();
		play.gainBlock(7);
		BEL bel = new BEL(play, 3);
		play.addAttDamSub(bel);
	}
	
	public Card copyCard() {
		return new Brace(dataHandler);
	}
	
	private class BEL implements Serializable, EntityListener{
		private static final long serialVersionUID = 1L;
		private Player owner;
		private int toDraw;
		
		public BEL(Player play, int td) {
			owner = play;
			toDraw = td;
		}

		@Override
		public void notify(Entity entity, String message, Object data) {
			System.out.println("notified " + owner.getBlock());
			if(owner.getBlock() == 0) {
				owner.addSE(new StartDraw(toDraw));
			}
		}
		
		
	}

}
