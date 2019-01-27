package global;

import java.util.ArrayList;

import server.ClientHandler;
import server.ServerDataHandler;

public class Player extends Entity{
	
	private ServerDataHandler dataHandler;
	
	private ArrayList<Card> cards;
	
	private ClientHandler client;
	private boolean readyToEndTurn;
	private boolean readyToStartGame;
	private boolean readyToStartFight;
	
	private int maxEnergy;
	private int curEnergy;
	
	
	public Player(ServerDataHandler sdh) {
		dataHandler = sdh;
		cards = new ArrayList<Card>();
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
	
	
}
