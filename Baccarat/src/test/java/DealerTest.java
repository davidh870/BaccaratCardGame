import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

class DealerTest extends BaccaratGame{

	static BaccaratDealer Dealer;
	static ArrayList<BaccaratGame.Card> bankerHand;
	static ArrayList<BaccaratGame.Card> playerHand;
	static BaccaratGameLogic gameLogic;
	static double currentBet;
	static double totalWinnings;
	static Card one;
	static BaccaratGame.Card two;
	static BaccaratGame.Card three;
	static BaccaratGame.Card four;



	@BeforeEach
	void setup() {
		Dealer = new BaccaratDealer();
		bankerHand = new ArrayList<BaccaratGame.Card>(3);
		playerHand = new ArrayList<BaccaratGame.Card>(3);
		gameLogic = new BaccaratGameLogic();
		currentBet = 0;
		totalWinnings = 0;
	}


	@Test
	void testType() {
		assertEquals("BaccaratDealer", Dealer.getClass().getName(), "Incorrect class");
	}

	@Test
	void deckTest() {
		Dealer.generateDeck();
		assertEquals(52, Dealer.Decksize(), "Incorrect deck size");
	}

	@Test
	void shuffleTest() {
		Dealer.generateDeck();
		Dealer.shuffleDeck();
		BaccaratDealer newDealer = new BaccaratDealer();
		newDealer.generateDeck();
		assertNotEquals(newDealer.deck.get(0), Dealer.deck.get(0), "not shuffled maybe");
	}

	@Test
	void newCardTest() {
		Card test = new Card("C", 1);
		assertEquals(1, test.value, "not correct value");
	}

	//Draw tests also made to test Dealhand since it is difficult use assertEquals to test random draws
	@Test
	void drawTest() {
		Dealer.deck.add(new BaccaratGame.Card("S",8));
		assertEquals(0, bankerHand.size(), "not empty");
		bankerHand.add(Dealer.drawOne());
		assertEquals(8, bankerHand.get(0).value, "not correct card");
		assertEquals("S", bankerHand.get(0).suite, "not correct card");
	}

	@Test
	void drawTest1() {
		Dealer.deck.add(new BaccaratGame.Card("H",5));
		playerHand.add(Dealer.drawOne());
		assertEquals(5, playerHand.get(0).value, "not correct card");
		assertEquals("H", playerHand.get(0).suite, "not correct card");
		Dealer.deck.add(new BaccaratGame.Card("D",2));
		playerHand.add(Dealer.drawOne());
		assertEquals(2, playerHand.get(1).value, "not correct card");
		assertEquals("D", playerHand.get(1).suite, "not correct card");

	}

	@Test
	void dealHandTest() {
		Dealer.generateDeck();
		playerHand = Dealer.dealHand();
		bankerHand = Dealer.dealHand();
		assertEquals(2, playerHand.size(), "not correct length");
		assertEquals(2, bankerHand.size(), "not correct length");
	}



}
