package global;

import java.util.ArrayList;
import java.util.Collections;

import server.S2CCommunicator;

public class Player extends Entity{
	private static final long serialVersionUID = 1L;

	private ArrayList<Card> hand;
	private ArrayList<Card> draw;
	private ArrayList<Card> exhausted;
	private ArrayList<Card> discard;
	private ArrayList<Card> deck;

	private transient S2CCommunicator client;
	private boolean readyToEndTurn;
	private boolean readyToStartGame;
	private boolean readyToStartFight;

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

	public void postTurn() {
		super.postTurn();
		int handSize = hand.size();
		for(int i = 0; i < handSize; i++) {
			hand.get(0).onTurnEndInHand(this, 0);
			discardCard(0);
		}
	}

	public void preTurn() {
		drawCards(5);
		super.preTurn();
		resetEnergy();
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

	public void heal(int heal) {
		curHealth += heal;
		if(curHealth > maxHealth) {
			curHealth = maxHealth;
		}
	}
	public void addEnergy(int e) {
		curEnergy += e;
	}
	public int getBlock() {
		return block;
	}

	public void setMaxEnergy(int max) {
		maxEnergy = max;
	}
	
	public void removeCardFromHand(int index) {
		if(index >= 0 && index < hand.size()) {
			hand.remove(index);
		}
	}

	public void addCardToDeck(Card card) {
		deck.add(card);
		card.onAddToDeck(this);
	}

	public void addCardToDraw(Card card) {
		draw.add(card);
		Collections.shuffle(draw);
	}

	public void addCardToHand(Card card) {
		hand.add(card);
	}

	public void setDeck(ArrayList<Card> cards) {
		deck = cards;
		for(Card c: deck) {
			c.onAddToDeck(this);
		}
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
			if(draw.size() != 0) {
				Card c = draw.get(0);
				draw.remove(0);
				hand.add(c);
			}
		}
	}

	public void exhaustFromHand(int index) {
		exhausted.add(hand.get(index));
		hand.remove(index);
	}

	public void discardCard(int[] handIndexes) {
		for(int handIndex: handIndexes) {
			if(hand.size() > handIndex) {
				discard.add(hand.get(handIndex));
				hand.remove(handIndex);
			}
		}
	}

	public void discardCard(int handIndex) {
		if(hand.size() > handIndex) {
			discard.add(hand.get(handIndex));
			hand.remove(handIndex);
		}
	}

	public void discardRandomCard() {
		if(hand.size() > 0) {
			int index = (int) (Math.random() * hand.size());
			discardCard(new int[] {index});
		}
	}

	public void discardHand() {
		discard.addAll(hand);
		hand.removeAll(hand);
	}

	public Message playCard(int index, int target) {
		try {
			if(index < hand.size()) {
				Card card = hand.get(index);
				if(card != null && card.cost <= curEnergy) {
					curEnergy -= card.cost;
					card.prePlay(this, index);
					card.play(this, target);
					return new Message("pcok", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message("pkfail", null);
	}

	public void setClientHandler(S2CCommunicator ch) {
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
				+ "Done: " + readyToEndTurn+ "\n\t"
				+ "Relics: " + relics;

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Player && ((Player) obj).name.equals(name);
	}
}
