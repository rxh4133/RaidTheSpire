package global;

import java.util.ArrayList;

import server.ClientHandler;

public class Player extends Entity{
	private static final long serialVersionUID = 1L;

	private ArrayList<Card> cards;
	
	private transient ClientHandler client;
	private boolean readyToEndTurn;
	private boolean readyToStartGame;
	private boolean readyToStartFight;
	
	private int maxEnergy;
	private int curEnergy;
	
	private String name;
	
	
	public Player() {
		cards = new ArrayList<Card>();
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void resetEnergy() {
		curEnergy = maxEnergy;
	}
	
	public Message playCard(int index) {
		Card card = cards.get(index);
		if(card != null && card.cost <= curEnergy) {
			card.play();
			curEnergy -= card.cost;
			return new Message("pcok", null);
		}
		return new Message("pkfail", null);
	}
	
	public void setClientHandler(ClientHandler ch) {
		client = ch;
	}
	
	public void setReadyToEndTurn(boolean rdy) {
		readyToEndTurn = rdy;
	}
	
	public boolean getReadyToEndTurn() {
		return readyToEndTurn;
	}
	
	public void setReadyToStartGame(boolean rdy) {
		readyToStartGame = rdy;
	}
	
	public boolean getReadyToStartGame() {
		return readyToStartGame;
	}
	
	public void setReadyToStartFight(boolean rdy) {
		readyToStartFight = rdy;
	}
	
	public boolean getReadyToStartFight() {
		return readyToStartFight;
	}
	
	public void sendMessage(Message m) {
		client.send(m);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Player && ((Player) obj).name.equals(name);
	}
}
