package global.statuseffects;

import global.Entity;

public class Metallicize extends global.StatusEffect{
	private static final long serialVersionUID = 1L;

	public Metallicize(int v) {
		super("Metallicize", v);
	}

	public void postTurn(Entity e) {
		e.gainBlock(value);
	}
}
