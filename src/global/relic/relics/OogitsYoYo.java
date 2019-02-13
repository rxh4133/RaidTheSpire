package global.relic.relics;

import global.ELM;
import global.Entity;
import global.Player;
import global.Rarity;
import global.relic.Relic;
import server.ServerDataHandler;

public class OogitsYoYo extends Relic{
	
	private static final long serialVersionUID = 1L;
	private boolean justGained = false;
	
	public OogitsYoYo() {
		super("Oogit's YoYo", Rarity.UNCOMMON);
		description = "At the start of your turn, take 5 true damage or heal 7 health, alternating.";
		flavor = "Oogit only knew one trick, but he was very proud of it.";
	}
	
	public Relic copyRelic() {
		return new OogitsYoYo();
	}
	
	public Relic onAdd(Player owner, ServerDataHandler sdh) {
		owner.addListener(this);
		return this;
	}
	
	public void notify(Entity e, ELM m, Object o) {
		if(m.is(ELM.TURN_START)) {
			if(justGained) {
				e.takeTrueDamage(5);
			}else {
				e.heal(7);
			}
			justGained = !justGained;
		}
	}
}
