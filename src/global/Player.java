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

	private int numberToDiscard;

	private int maxEnergy;
	private int curEnergy;

	private String name;

	public PlayerClass playerClass;


	public Player() {
		hand = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		exhausted = new ArrayList<Card>();
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
	
	public void setDeck(ArrayList<Card> cards) {
		deck = cards;
	}

	public void shuffleCardsFromDiscard() {
		for(int i = 0; i < discard.size(); i++) {
			draw.add(discard.get(i));
			discard.remove(i);
		}
		Collections.shuffle(draw);
	}
	
	public void removeHandAndDiscard() {
		hand.removeAll(hand);
		discard.removeAll(discard);
	}

	public void shuffleCardsFromDeck() {
		draw.removeAll(draw);
		for(int i = 0; i < deck.size(); i++) {
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

	public void prepDiscard(int numberToDiscard) {
		this.numberToDiscard += numberToDiscard;
	}
	
	public void exhaustFromHand(int index) {
		exhausted.add(hand.get(index));
		hand.remove(index);
	}

	public void discardCard(int[] handIndexes) {
		for(int handIndex: handIndexes) {
			if(hand.size() > handIndex && numberToDiscard > 0) {
				hand.remove(handIndex);
				numberToDiscard--;
			}
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
		return "Player: " + name + "\n\tHealth: (" + block + "B) " + curHealth + "/" + maxHealth + "\n\tCards:" + "\n\t\t" + hand;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Player && ((Player) obj).name.equals(name);
	}
}
