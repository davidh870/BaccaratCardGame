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
		//Dealer.deck.add(new Card("A", 1));
//		Dealer.generateDeck();
//		assertEquals(52, Dealer.Decksize(), "Incorrect deck size");
		assertEquals("BaccaratDealer", Dealer.getClass().getName(), "Incorrect class");
	}




	@Test
	void newCardTest() {
		Card test = new Card("C", 1);
		assertEquals(1, test.value, "not correct value");
	}

	//Draw tests also made to test Dealhand since it is difficult use assertEquals to test random draws
	@Test
	void drawTest() {
		Dealer.deck.add(new BaccaratGame.Card());
		assertEquals(0, bankerHand.size(), "not empty");
		bankerHand.add(Dealer.drawOne());
		assertEquals(-1, bankerHand.get(0).value, "not correct card");
	}

	@Test
	void drawTest1() {
		Dealer.deck.add(new BaccaratGame.Card());
		playerHand.add(Dealer.drawOne());
		assertEquals(-1, playerHand.get(0).value, "not correct card");
		Dealer.deck.add(new BaccaratGame.Card());
		playerHand.add(Dealer.drawOne());
		assertEquals(-1, playerHand.get(1).value, "not correct card");
	}
//
//	@Test
//	void dealHandTest() {
//		playerHand = Dealer.dealHand();
//		bankerHand = Dealer.dealHand();
//		assertEquals(2, playerHand.size(), "not correct length");
//		assertEquals(2, bankerHand.size(), "not correct length");
//	}
//
//	@Test
//	void resetTest() {
//		playerHand = Dealer.dealHand();
//		bankerHand = Dealer.dealHand();
//		theDealer.resetCards();
//		assertEquals(0, playerHand.size(), "not empty");
//		assertEquals(0, bankerHand.size(), "not empty");
//	}


}
