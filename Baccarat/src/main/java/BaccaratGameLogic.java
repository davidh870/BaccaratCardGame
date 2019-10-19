import java.util.ArrayList;

public class BaccaratGameLogic extends BaccaratGame{

  public String whoWon(ArrayList<Card> bankerHand, ArrayList<Card> playerHand) {
    Card playerCard = new Card();
    
    int bankerTotal = handTotal(bankerHand);
    int playerTotal = handTotal(playerHand);

    // Check if anyone won from the first two cards drawn in terms of natural win
    if(playerTotal == 8 || playerTotal == 9 || bankerTotal == 8 || bankerTotal == 9) {
    	if(playerTotal == bankerTotal) {
    		return "Draw";
    	}
    	else if(playerTotal > bankerTotal) {
    		return "Player";
    	}
    	else {
    		return "Banker";
    	}
    }
    
    
    
    if(handTotal(bankerHand) == 8 || handTotal(bankerHand) == 9) {
    	return "Banker";
    }
    
    if (evaluatePlayerDraw(playerHand) == true) {
      playerCard = theDealer.drawOne();
      playerHand.add(playerCard);
    }
    if (evaluateBankerDraw(bankerHand, playerCard) == true) {
      bankerHand.add(theDealer.drawOne());
    }

    // Check who won after any draws or not
    bankerTotal = handTotal(bankerHand);
    playerTotal = handTotal(playerHand);
    
    if( playerTotal == bankerTotal) {
    	 return "Draw";
    }
    if (playerTotal > bankerTotal) {
      return "Player";
    }
    else {
      return "Banker";
    }

  }

  public int handTotal(ArrayList<Card> hand) {
    int total = 0;
    for (int i = 0; i < 2; i++) {
      if ( hand.get(i).value < 10) {
    	  total = total +  hand.get(i).value;
      }
    }
    if (total > 9) {
      total -= 10;
    }
    
    if(hand.size() == 3) {
    	if (hand.get(2).value < 10) {
      	  total = total +  hand.get(2).value;
        }
    }
    return total;
  }

  public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
    int total = handTotal(hand);
    if (total > 6) {
      return false;
    }
    else if (total < 3) {
      return true; //set true for the hand being less than 6
    }
    else {
      switch (playerCard.value) {
        case -1:
          if (total == 5 || total == 4 || total == 3) {
            return true;
          }
        case 0:
          if (total == 3) {
            return true;
          }
        case 1:
          if (total == 3) {
            return true;
          }
        case 2:
          if (total == 4 || total == 3) {
            return true;
          }
        case 3:
          if (total == 4 || total == 3) {
            return true;
          }
        case 4:
          if (total == 5 || total == 4 || total == 3) {
            return true;
          }
        case 5:
          if (total == 5 || total == 4 || total == 3) {
            return true;
          }
        case 6:
          if (total == 6 || total == 5 || total == 4 || total == 3) {
            return true;
          }
        case 7:
          if (total == 6 || total == 5 || total == 4 || total == 3) {
            return true;
          }
        case 8:
            return false;
        case 9:
          if (total == 3) {
            return true;
          }
         default :
        	 return false;
      }
      


    }
  }

  public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
    int total = handTotal(hand);
    if (total < 6) {
      return true; //set true for the hand being less than 6
    }
    return false;
  }
}