package global.relics;

import global.Entity;
import global.Player;
import global.Relic;
import server.EntityListener;
import server.ServerDataHandler;

public class AvengingEye extends Relic{

	public AvengingEye(ServerDataHandler sdh) {
		super("Avenging Eye", sdh);
	}
	
	public void onAdd(Player owner) {
		AEL ael = new AEL(owner);
		for(Player p: dataHandler.players) {
			p.addAttDamSub(ael);
		}
	}
	
	private class AEL implements EntityListener{
		
		public Player owner;
		
		public AEL(Player o) {
			owner = o;
		}

		@Override
		public void notify(Entity entity, String message, Object data) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
