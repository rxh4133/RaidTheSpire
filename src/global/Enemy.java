package global;

public class Enemy extends Entity{
	private static final long serialVersionUID = 1L;

	public Enemy() {
		super();
	}
	
	public EnemyAction takeAction() {
		return null;//bb
	}
	
	public EnemyAction decideAction() {
		return null;//bb
	}
	
	public String toString() {
		return "Enemy:\n\tHealth: (" + block + "B) " + curHealth + "/" + maxHealth;
	}
	
	public Enemy copyForDisplay() {
		Enemy e = new Enemy();
		e.maxHealth = maxHealth;
		e.curHealth = curHealth;
		e.effects = effects;
		e.block = block;
		return e;
	}

}
