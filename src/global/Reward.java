package global;

import java.io.Serializable;
import java.util.ArrayList;

public class Reward implements Serializable{
	public ArrayList<Card> cardReward1;
	public ArrayList<Card> cardReward2;
	public ArrayList<Card> cardReward3;
	public ArrayList<Card> cardReward4;
	public ArrayList<Card> cardReward5;
	
	public ArrayList<Relic> relicReward1;
	public ArrayList<Relic> relicReward2;
	
	public int numCampfires;
}
