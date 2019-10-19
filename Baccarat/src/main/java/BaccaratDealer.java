import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer extends BaccaratGame{
	
	public ArrayList<Card> deck = new ArrayList<Card>();
	  
	  
	/* Generates a new deck */
	public void generateDeck() {
		
		deck.clear(); // Clears deck array list before adding cards
		
		/* Create Cards of CLUBS */
		for(int i = 1; i < 14; i++) {
			deck.add(new Card("C", i));
		}
		/* Create Cards of DIAMONDS */
		for(int i = 1; i < 14; i++) {
			deck.add(new Card("D", i));
		}
		/* Create Cards of HEARTS */
		for(int i = 1; i < 14; i++) {
			deck.add(new Card("H", i));
		}
		/* Create Cards of SPADES */
		for(int i = 1; i < 14; i++) {
			deck.add(new Card("S", i));
		}
		
	};
	

	// Returns deck size
	public int Decksize() {
	    return deck.size();
	}
	
	//draws a single card at random from the deck.
	public Card drawOne() {
		Random random = new Random();
	    int Random =  random.nextInt() % Decksize();
	    Card card = deck.get(Math.abs(Random));
	    deck.remove(Math.abs(Random));
	    return card;
	}
	
	//shuffles the entire deck and returns a new array of cards
	public void shuffleDeck(){
		generateDeck(); // Generate Deck
	    Collections.shuffle(this.deck); // Shuffle

	}
	  
	  
	// adds two cards to each input of hand.
    public ArrayList<Card> dealHand() {
    	ArrayList<Card> hand = new ArrayList<Card>(); 
    	
    	hand.add(drawOne());
	    hand.add(drawOne());
	    return hand;
	}

	// resets the game by removing all playing cards
    public void resetCards() {
		for (int i = 0; i < bankerHand.size(); i++) {
			deck.add(bankerHand.get(i));
	        bankerHand.remove(i);
		}
	    for (int i = 0; i < playerHand.size(); i++) {
	        deck.add(playerHand.get(i));
	        playerHand.remove(i);
		}
	}


}
