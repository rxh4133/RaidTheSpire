package global.statuseffects;

import global.Entity;
import global.StatusEffect;

public class Respect extends StatusEffect{
	private static final long serialVersionUID = 1L;

	public Respect(Entity appliedTo) {
		super("Respect", 0);
		appliedTo.addDamageDealtSub(this);
	}
	
	public void preTurn(Entity e) {
		e.gainBlock(value);
		value = 0;
	}
	
	public void notify(Entity e, String message, Object data) {
		System.out.println("respect otified");
		Object[] pay = (Object[]) data;
		if(pay[1].equals("Thorns")) {
			this.value += (int) pay[0];
		}
	}

}
