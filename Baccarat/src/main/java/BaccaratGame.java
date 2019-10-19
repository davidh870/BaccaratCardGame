import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BaccaratGame extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	static public Stage window; // Create a window for switching scenes
	static public Scene startScene; // Create a start scene
	static public Scene gameScene; // Create a game scene
	
	/* Create Dealer */
    public static BaccaratDealer theDealer = new BaccaratDealer();
    
    /* Create Array list for banker hand and player hand */
	public static ArrayList<Card> bankerHand;
	public static ArrayList<Card> playerHand;
	
	/* Create an instance of game logic */
	public static BaccaratGameLogic gameLogic = new BaccaratGameLogic(); 
	
	
	/* Create bet and winnings */
	public static double currentBet = 0;
	public static double totalWinnings = 0;
	
	

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		startScene = StartScreen.startCreate(); // Initialize start screen
		gameScene = GameScreen.gameCreate(); // Initialize game screen
		
		window = primaryStage; // Set window as primary stage at start
		primaryStage.setTitle("Welcome To Baccarat");
		
		
		window.setScene(startScene); // Start with start screen
		window.show(); // Display Start Screen Scene
	}
	
	// Class for cards
	public static class Card {
		int value; 
		String suite;
		String imageName;

		public Card(String theSuite,int theValue) {
			this.value = theValue;
			this.suite = theSuite;
			this.imageName = String.valueOf(this.value) + theSuite + ".png";
		}


		Card() {
			this.value = -1;
			this.suite = "N/A";
			this.imageName = "N/A";
		}
	}

}
