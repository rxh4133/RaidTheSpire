package global.relics;

import global.Relic;

public class MarkOfUndeath extends Relic {
	private static final long serialVersionUID = 1L;

	public MarkOfUndeath() {
		super("Mark of Undeath");
	}
	
	public Relic copyRelic() {
		return new MarkOfUndeath();
	}

}
