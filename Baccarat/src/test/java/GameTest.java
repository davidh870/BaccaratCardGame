import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameTest extends BaccaratGame{
    static BaccaratDealer Dealer;
    static ArrayList<Card> bankerHand;
    static ArrayList<Card> playerHand;
    static BaccaratGameLogic gameLogic;
    static double currentBet;
    static double totalWinnings;
    static BaccaratGame.Card one;;
    static Card two;
    static Card three;
    static Card four;

    @BeforeEach
    void setup() {
        Dealer = new BaccaratDealer();
        bankerHand = new ArrayList<Card>(3);
        playerHand = new ArrayList<Card>(3);
        gameLogic = new BaccaratGameLogic();
        currentBet = 0;
        totalWinnings = 0;
    }

    @Test
    void testType() {
        assertEquals("BaccaratGameLogic", gameLogic.getClass().getName(), "Incorrect class");
    }

    @Test
    void totalPlayerTest() {
        playerHand.add(new Card("C",2));
        playerHand.add(new Card("C",4));
        assertEquals( 6, gameLogic.handTotal(playerHand), "incorrect total");
    }

    @Test
    void totalBankerTest() {
        bankerHand.add(new Card());
        bankerHand.add(new Card());
        assertEquals( -2, gameLogic.handTotal(bankerHand), "incorrect total");
    }

    @Test
    void evaluatePlayerDrawTest() {
        one = new Card();
        two = new Card();
        playerHand.add(one);
        playerHand.add(two);
        one.value = 5;
        assertEquals(true, gameLogic.evaluatePlayerDraw(playerHand), "incorrect player evaulation");
    }

    @Test
    void evaluatePlayerDrawTest1() {
        one = new Card();
        two = new Card();
        playerHand.add(one);
        playerHand.add(two);
        one.value = 7;
        assertEquals(false, gameLogic.evaluatePlayerDraw(playerHand), "incorrect player evaulation");
    }

    @Test
    void evaluateBankerDrawTest() {
        one = new Card();
        two = new Card();
        three = new Card();
        bankerHand.add(one);
        bankerHand.add(two);
        one.value = 1;
        two.value = 1;
        assertEquals(true, gameLogic.evaluateBankerDraw(bankerHand, three), "incorrect banker evaulation");
    }

    @Test
    void evaluateBankerDrawTest1() {
        one = new Card();
        two = new Card();
        three = new Card();
        bankerHand.add(one);
        bankerHand.add(two);
        one.value = 5;
        two.value = 3;
        assertEquals(false, gameLogic.evaluateBankerDraw(bankerHand, three), "incorrect banker evaulation");
    }

    @Test
    void evaluateBankerDrawTest2() {
        one = new Card("A",2);
        two = new Card("C",2);
        three = new Card();
        bankerHand.add(one);
        bankerHand.add(two);
        assertEquals(true, gameLogic.evaluateBankerDraw(bankerHand, three), "incorrect banker evaulation");
    }

    @Test
    void evaluateBankerDrawTest3() {
        one = new Card("A",2);
        two = new Card("C",2);
        three = new Card("H",-2);
        bankerHand.add(one);
        bankerHand.add(two);
        assertEquals(false, gameLogic.evaluateBankerDraw(bankerHand, three), "incorrect banker evaulation");
    }

    @Test
    void whoWonTest()  { // natural win
        one = new Card("A",8);
        two = new Card("A",0);
        three = new Card("B",0);
        four = new Card("C",0);
        bankerHand.add(one);
        bankerHand.add(two);
        playerHand.add(three);
        playerHand.add(four);
        assertEquals( "Banker", gameLogic.whoWon(bankerHand, playerHand), "Incorrect winner");
    }

    @Test
    void whoWonTest1()  {//natural win
        one = new Card("A",2);
        two = new Card("A",0);
        three = new Card("B",9);
        four = new Card("C",0);
        bankerHand.add(one);
        bankerHand.add(two);
        playerHand.add(three);
        playerHand.add(four);
        assertEquals( "Player", gameLogic.whoWon(bankerHand, playerHand), "Incorrect winner");
    }

    @Test
    void whoWonTest2()  {//Both natural win
        one = new Card("A",8);
        two = new Card("A",0);
        three = new Card("B",9);
        four = new Card("C",0);
        bankerHand.add(one);
        bankerHand.add(two);
        playerHand.add(three);
        playerHand.add(four);
        assertEquals( "Player", gameLogic.whoWon(bankerHand, playerHand), "Incorrect winner");
    }

    @Test
    void whoWonTest3()  {//normal
        one = new Card("A",5);
        two = new Card("A",3);
        three = new Card("B",5);
        four = new Card("C",2);
        bankerHand.add(one);
        bankerHand.add(two);
        playerHand.add(three);
        playerHand.add(four);
        assertEquals( "Banker", gameLogic.whoWon(bankerHand, playerHand), "Incorrect winner");
    }


}
