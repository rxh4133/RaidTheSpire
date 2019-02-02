package global.relics;

import global.Entity;
import global.Player;
import global.Relic;
import global.statuseffects.Strength;
import global.statuseffects.StrengthDown;
import server.EntityListener;
import server.ServerDataHandler;

public class AvengingEye extends Relic{
	private static final long serialVersionUID = 1L;

	public AvengingEye(ServerDataHandler sdh) {
		super("Avenging Eye", sdh);
	}
	
	public Relic onAdd(Player owner) {
		AEL ael = new AEL(owner);
		for(Player p: dataHandler.players) {
			p.addAttDamSub(ael);
		}
		return this;
	}
	
	private class AEL implements EntityListener{
		
		public Player owner;
				
		public AEL(Player o) {
			owner = o;
		}

		@Override
		public void notify(Entity entity, String message, Object data) {
			owner.addSE(new Strength(1));
			owner.addSE(new StrengthDown(1));
		}
		
	}

}
