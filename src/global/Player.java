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

	public Reward lastReward;
	
	private ArrayList<Relic> relics;


	public Player() {
		hand = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		exhausted = new ArrayList<Card>();
		relics = new ArrayList<Relic>();
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
	
	public void addRelic(Relic relic) {
		relics.add(relic);
	}

	public void resetEnergy() {
		curEnergy = maxEnergy;
	}

	public void setMaxEnergy(int max) {
		maxEnergy = max;
	}

	public int getStrength() {
		for(StatusEffect se: effects) {
			if(se.name.equals("Strength")) {
				return se.value;
			}
		}
		return 0;
	}

	public int getDex() {
		for(StatusEffect se: effects) {
			if(se.name.equals("Dexterity")) {
				return se.value;
			}
		}
		return 0;
	}

	public void addCardToDeck(Card card) {
		deck.add(card);
	}

	public void setDeck(ArrayList<Card> cards) {
		deck = cards;
	}

	public void shuffleCardsFromDiscard() {
		int numToShuffle = discard.size();
		for(int i = 0; i < numToShuffle; i++) {
			draw.add(discard.get(0));
			discard.remove(0);
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
				discard.add(hand.get(handIndex));
				hand.remove(handIndex);
				numberToDiscard--;
			}
		}
	}

	public void endTurnDiscard() {
		discard.addAll(hand);
		hand.removeAll(hand);
	}

	public Message playCard(int index, int target) {
		System.out.println("playing");
		if(index < hand.size()) {
			Card card = hand.get(index);
			if(card != null && card.cost <= curEnergy) {
				System.out.println("doin it here");
				card.play(this, target);
				curEnergy -= card.cost;
				numberToDiscard++;
				discardCard(new int[] {index});
				return new Message("pcok", null);
			}
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
		return "Player: ("+ curEnergy + "/" + maxEnergy + " E) " + name + "\n\t"
				+ "Health: (" + block + " B) " + curHealth + "/" + maxHealth + "\n\t"
				+ "Cards: " + hand + "\n\t"
				+ "Status: " + effects + "\n\t"
				+ "Done: " + readyToEndTurn;

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Player && ((Player) obj).name.equals(name);
	}
}
