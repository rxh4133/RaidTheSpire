package global;

import java.io.Serializable;

public class StatusEffect implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	public String name;
	public int value;

	public void preTurn(Entity e) {
		
	}
	
	public void postTurn(Entity e) {
		
	}
	
	public String toString() {
		return name + " " + value;
	}
}
