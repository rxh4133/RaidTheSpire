package global;

import java.io.Serializable;

public class Card implements Serializable{
	public int cost;
	private final int ID;
	private Card(int id) {
		ID = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Card && ((Card) obj).ID == ID;
	}

	public int getID() {
		return ID;
	}
	
	public void play() {
		
	}
	
}
