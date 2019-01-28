package global;

import java.util.ArrayList;
import java.util.Collections;

import server.ClientHandler;

public class Player extends Entity{
	private static final long serialVersionUID = 1L;

	private ArrayList<Card> hand;
	private ArrayList<Card> draw;
	private ArrayList<Card> exhausted;
	private ArrayList<Card> discard;
	private ArrayList<Card> deck;
	
	private transient ClientHandler client;
	private boolean readyToEndTurn;
	private boolean readyToStartGame;
	private boolean readyToStartFight;
	
	private int maxEnergy;
	private int curEnergy;
	
	private String name;
	
	public PlayerClass playerClass;
	
	
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
	
	public int getStrength() {
		for(StatusEffect se: effects) {
			if(se.name.equals("STRENGTH")) {
				return se.value;
			}
		}
		return 0;
	}
	
	public int getDex() {
		for(StatusEffect se: effects) {
			if(se.name.equals("DEXTERITY")) {
				return se.value;
			}
		}
		return 0;
	}
	
	public void shuffleCardsFromDiscard() {
		for(int i = 0; i < discard.size(); i++) {
			draw.add(discard.get(i));
			discard.remove(i);
		}
		Collections.shuffle(draw);
	}
	
	public void shuffleCardsFromDeck() {
		for(int i = 0; i < discard.size(); i++) {
			draw.add(deck.get(i).copyCard());
		}
		Collections.shuffle(draw);
	}
	
	public void drawCards(int toDraw) {
		 for(int i = 0; i < toDraw; i++) {
			 if(draw.size() == 0) {
				 shuffleCardsFromDiscard();
			 }
			 Card c = draw.get(0);
			 draw.remove(0);
			 hand.add(c);
		 }
	}
	
	public Message playCard(int index, int target) {
		Card card = hand.get(index);
		if(card != null && card.cost <= curEnergy) {
			card.play(this, target);
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
