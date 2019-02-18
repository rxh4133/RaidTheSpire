package global;

import java.util.ArrayList;
import java.util.Collections;

import global.card.CardResult;
import global.relic.Relic;
import global.card.Card;
import server.ActionInteruptException;
import server.CardFailException;
import server.S2CCommunicator;

public class Player extends Entity{
	private static final long serialVersionUID = 1L;

	private ArrayList<Card> hand;
	private ArrayList<Card> draw;
	private ArrayList<Card> exhausted;
	private ArrayList<Card> discard;
	private ArrayList<Card> removed;
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
		setHand(new ArrayList<Card>());
		discard = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		exhausted = new ArrayList<Card>();
		removed = new ArrayList<Card>();
		relics = new ArrayList<Relic>();
	}

	public Player(String name) {
		this.name = name;
	}

	public void postTurn() {//TODO fix this asap
		super.postTurn();
		for(int i = 0; i < getHand().size(); i++) {
			CardResult r = getHand().get(i).onTurnEndInHand(this, i);
			if(r.equals(CardResult.DISCARD)) {
				discardCard(i);
				i--;
			}else if(r.equals(CardResult.EXHAUST)) {
				exhaustFromHand(i);
				i--;
			}else if(r.equals(CardResult.REMOVE)) {
				getHand().remove(i);
				i--;
			}
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
		setCurEnergy(getMaxEnergy());
	}

	public void addEnergy(int e) {
		setCurEnergy(getCurEnergy() + e);
	}
	public int getBlock() {
		return block;
	}

	public void setMaxEnergy(int max) {
		maxEnergy = max;
	}

	public void removeCardFromHand(int index) {
		if(index >= 0 && index < getHand().size()) {
			removed.add(hand.remove(index));
			System.out.println("Card removed!");
		}else {
			System.out.println("Card was not removed of index: " + index);
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
		getHand().add(card);
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
		getHand().removeAll(getHand());
		discard.removeAll(discard);
	}

	public void shuffleCardsFromDeck() {
		draw.removeAll(draw);
		for(int i = 0; i < deck.size(); i++) {
			draw.add(deck.get(i).clone());
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
				getHand().add(c);
			}
		}
	}
	
	public int getDrawSize() {
		return draw.size();
	}

	public int getDiscardSize() {
		return discard.size();
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	public void exhaustRandomCardFromDraw() {
		exhausted.add(draw.remove((int) (Math.random() * draw.size())));
	}
	public void exhaustRandomCardFromDiscard() {
		exhausted.add(discard.remove((int) (Math.random() * discard.size())));
	}
	public void exhaustRandomCardFromHand() {
		exhausted.add(hand.remove((int) (Math.random() * hand.size())));
	}
	
	public void exhaustFromHand(int index) {
		exhausted.add(getHand().get(index));
		getHand().remove(index);
	}

	public void discardCard(int[] handIndexes) {
		for(int handIndex: handIndexes) {
			if(getHand().size() > handIndex) {
				discard.add(getHand().get(handIndex));
				getHand().remove(handIndex);
			}
		}
	}

	public void discardCard(int handIndex) {
		if(getHand().size() > handIndex) {
			discard.add(getHand().get(handIndex));
			getHand().remove(handIndex);
		}
	}

	public void discardRandomCard() {
		if(getHand().size() > 0) {
			int index = (int) (Math.random() * getHand().size());
			discardCard(new int[] {index});
		}
	}

	public void discardHand() {
		discard.addAll(getHand());
		getHand().removeAll(getHand());
	}

	public Message playCard(int index, int target) {
		CardResult result = null;
		boolean energyUsed = false;
		try {
			if(index < getHand().size()) {
				Card card = getHand().get(index);
				if(card != null && card.cost <= getCurEnergy()) {
					result = card.prePlay(this, index);
					energyUsed = true;
					setCurEnergy(getCurEnergy() - card.cost);
					if(!card.upgraded) {
						card.play(this, target);
					}else {
						card.playUpgraded(this, target);
					}
					return new Message("pcok", null);
				}
			}
		} catch (CardFailException e) {
			e.printStackTrace();
			if(result != null) {
				if(result.equals(CardResult.EXHAUST)) {
					hand.add(index, exhausted.get(exhausted.size()-1));
				}else if(result.equals(CardResult.DISCARD)) {
					hand.add(index, discard.get(discard.size()-1));
				}else if(result.equals(CardResult.REMOVE)) {
					hand.add(index, removed.get(removed.size()-1));
				}
			}
			if(energyUsed) {
				setCurEnergy(getCurEnergy() + hand.get(index).cost);
			}
		} catch (ActionInteruptException aie) {
			aie.printStackTrace();
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
		return "Player: ("+ getCurEnergy() + "/" + getMaxEnergy() + " E) " + name + "\n\t"
				+ "Health: (" + block + " B) " + curHealth + "/" + maxHealth + "\n\t"
				+ "Hand: " + getHand() + "\n\t"
				+ "Draw: " + draw + "\n\t"
				+ "Discard: " + discard + "\n\t"
				+ "Exhausted: " + exhausted + "\n\t"
				+ "Status: " + effects + "\n\t"
				+ "Done: " + readyToEndTurn+ "\n\t"
				+ "Relics: " + relics;

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Player && ((Player) obj).name.equals(name);
	}

	public int getCurEnergy() {
		return curEnergy;
	}

	public void setCurEnergy(int curEnergy) {
		this.curEnergy = curEnergy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<Card> getDeck() {
		ArrayList<Card> retDeck = new ArrayList<Card>();
		retDeck.addAll(deck);
		return retDeck;
	}
}
