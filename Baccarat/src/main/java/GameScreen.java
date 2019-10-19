import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;



public class GameScreen extends BaccaratGame {	
	/* String containing who the user bet on */
	static String userBetOn; // By Default Banker is selected
	static VBox roundInfo = new VBox();
	
	
	/* Creates a game screen scene and returns the scene */
	public static Scene gameCreate() {
		userBetOn = "Banker";
		/* Initialize dealer with new deck */
		theDealer.generateDeck();
		
		/* Create a menu bar */
		MenuBar menuBar = new MenuBar();
		Menu menuOne = new Menu("Options");
		MenuItem freshStart = new MenuItem("Fresh Start");
		MenuItem quit = new MenuItem("Quit");
		
		freshStart.setOnAction(e ->{
			totalWinnings = 0;
			currentBet = 0;
			window.setScene(gameCreate());
			window.show();
		});
		
		quit.setOnAction(e -> Platform.exit());
		
		menuOne.getItems().addAll(freshStart, quit);
		menuBar.getMenus().addAll(menuOne);
		
		// Text for winner 
		Text winner = new Text();
		winner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
		winner.setFill(Color.WHITE);
		winner.setStroke(Color.AQUA);
		
		
		
		/* Create a pause transition */
		PauseTransition pausePlayerCards = new PauseTransition(Duration.seconds(1));
		PauseTransition pauseBankerCards = new PauseTransition(Duration.seconds(2));
		PauseTransition pausePlayerThird = new PauseTransition(Duration.seconds(3));
		PauseTransition pauseBankerThird = new PauseTransition(Duration.seconds(4));
		PauseTransition pauseWinnerDisplay = new PauseTransition(Duration.seconds(4));
		
		
		
		/* Create buttons and HBox for who user bet on */
		Button bankButton = new Button("Banker");
		bankButton.setDisable(true);
		Button playerButton = new Button("Player");
		Button drawButton = new Button("Draw");
		
		bankButton.setOnAction(e -> {
			userBetOn = "Banker";
			playerButton.setDisable(false);
			drawButton.setDisable(false);
			bankButton.setDisable(true);
		});
		
		playerButton.setOnAction(e -> {
			userBetOn = "Player";
			bankButton.setDisable(false);
			drawButton.setDisable(false);
			playerButton.setDisable(true);
		});
		
		drawButton.setOnAction(e -> {
			userBetOn = "Draw";
			bankButton.setDisable(false);
			drawButton.setDisable(true);
			playerButton.setDisable(false);
		});
		
		HBox userBetOnHBOX = new HBox();
		userBetOnHBOX.setAlignment(Pos.CENTER);
		userBetOnHBOX.getChildren().addAll(bankButton, playerButton, drawButton);
		
		/* Create VBOX for user input */
		Label bet2placelabel = new Label("Enter Bet: ");
		bet2placelabel.setTextFill(Color.WHITE);
		TextField bet2placeinput = new TextField();
		HBox bet2place = new HBox(1);
		bet2place.getChildren().addAll(bet2placelabel, bet2placeinput);
		
		VBox userInputs = new VBox(4);
		userInputs.getChildren().addAll(userBetOnHBOX, bet2place);
		userInputs.setAlignment(Pos.CENTER);
		
		/* Display Current Statistics */
		Label betplacedlabel = new Label("Bets Placed:      ");
		betplacedlabel.setTextFill(Color.WHITE);
		TextField betplaced = new TextField();
		
		Label totalWinningslabel = new Label("Total Winnings: ");
		totalWinningslabel.setTextFill(Color.WHITE);
		TextField totalWinningsText = new TextField();
		
		HBox betplacedBox = new HBox(1);
		betplacedBox.getChildren().addAll( betplacedlabel, betplaced);
		betplacedBox.setAlignment(Pos.BOTTOM_CENTER);
		
		HBox totalWinningsBox = new HBox(1);
		totalWinningsBox.getChildren().addAll(totalWinningslabel, totalWinningsText);
		betplacedBox.setAlignment(Pos.TOP_CENTER);
		
		ScrollPane scrollpane = new ScrollPane(); // Scroll pane for reporting results for each round		
		
		VBox gameStats = new VBox(8);
		gameStats.getChildren().addAll(totalWinningsBox, betplacedBox, scrollpane);
		
		
		/* Create imageView for player cards */
		Image greenbg = new Image("greenbg.png"); // Green Transparent card
	    ImageView playerCardOne = new ImageView(greenbg);
	    playerCardOne.setFitHeight(200);
	    playerCardOne.setPreserveRatio(true);
	    
		ImageView playerCardTwo = new ImageView(greenbg);
		playerCardTwo.setFitHeight(200);
		playerCardTwo.setPreserveRatio(true);
		
		ImageView playerCardThree = new ImageView(greenbg);
		playerCardThree.setFitHeight(200);
		playerCardThree.setPreserveRatio(true);
		
		/* Create HBOX for 3 CARDS for player */
		HBox playerHandBox = new HBox(4);
		playerHandBox.getChildren().addAll(playerCardOne, playerCardTwo, playerCardThree);
		playerHandBox.setAlignment(Pos.BOTTOM_CENTER);
		
		/* Create imageView for banker cards */
	    ImageView bankerCardOne = new ImageView(greenbg);
	    bankerCardOne.setFitHeight(200);
	    bankerCardOne.setPreserveRatio(true);
	    
		ImageView bankerCardTwo = new ImageView(greenbg);
		bankerCardTwo.setFitHeight(200);
		bankerCardTwo.setPreserveRatio(true);
		
		ImageView bankerCardThree = new ImageView(greenbg);
		bankerCardThree.setFitHeight(200);
		bankerCardThree.setPreserveRatio(true);
		
		/* Create HBOX for 3 CARDS for banker */
		HBox bankerHandBox = new HBox(4);
		bankerHandBox.getChildren().addAll(bankerCardOne, bankerCardTwo, bankerCardThree);
		bankerHandBox.setAlignment(Pos.TOP_CENTER);
		
		/* Text for reveal whose hand */
		Text playerHandInfo = new Text("Player Hand");
		playerHandInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		playerHandInfo.setFill(Color.WHITE);
		
		Text bankerHandInfo = new Text("Banker Hand");
		bankerHandInfo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		bankerHandInfo.setFill(Color.WHITE);
		
		/* Combine playerHandBox and bankerHandBox to a VBOX */
		VBox handBox = new VBox(12);
		handBox.getChildren().addAll(winner, bankerHandInfo, bankerHandBox, playerHandBox, playerHandInfo);
		handBox.setAlignment(Pos.CENTER);
	
		
		/* Background Green */
		Background greenBackground = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
		
		/* Create Border Pane */
		BorderPane gameBorder = new BorderPane();
		gameBorder.setRight(userInputs); // Add user inputs
		gameBorder.setMargin(userInputs, new Insets(5));
		gameBorder.setAlignment(gameStats, Pos.TOP_CENTER);
		gameBorder.setMargin(gameStats, new Insets(5));
		gameBorder.setLeft(gameStats);
		gameBorder.setAlignment(handBox, Pos.CENTER);
		gameBorder.setCenter(handBox); // Add dealer hand
		gameBorder.setAlignment(menuBar, Pos.TOP_CENTER);
		
		gameBorder.setTop(menuBar);
		gameBorder.setBackground(greenBackground);
		
		
		/* Game scene with green background */
		Scene newgameScene = new Scene(gameBorder,1280,720);
		
		// Create dialog to display winner
		Alert alertWinner = new Alert(AlertType.INFORMATION);
		alertWinner.setTitle("");
		alertWinner.setHeaderText(null);
		

		/* Event handler for different actions */
		/// Get bet first */
		bet2placeinput.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent action) {
				// Get user bet
				String tempCurrentBet = bet2placeinput.getText();
				
				// Clears text in user input after getting input
				bet2placeinput.clear();
				
				// Hide Winner display
				winner.setVisible(false);
				
				// Clear player hand
				playerCardOne.setImage(greenbg);
				playerCardTwo.setImage(greenbg);
				playerCardThree.setImage(greenbg);
				// Clear banker hand
				bankerCardOne.setImage(greenbg);
				bankerCardTwo.setImage(greenbg);
				bankerCardThree.setImage(greenbg);
				
				
				
				// If there is a bet update currentBet, display cards, disable userinput, and evaluate winner
				if(!tempCurrentBet.isEmpty()) {

					boolean valid = false;
					for(int i = 0; i < tempCurrentBet.length(); i++) {
						valid = Character.isDigit(tempCurrentBet.charAt(i));
					}
					
					// Check if user input is valid
					if(valid == true) {
						betplaced.setText(tempCurrentBet);
						
						// Disable user input for placing bets
						bet2placeinput.setDisable(true);
						bankButton.setDisable(true);
						playerButton.setDisable(true);
						drawButton.setDisable(true);
						
						
						currentBet = Integer.valueOf(tempCurrentBet); // Update current bet
						
						// Regenerate deck and shuffle if dealer has less than 6 cards
						if(theDealer.Decksize() < 6) {
							theDealer.shuffleDeck();
						}
						
						// Deal two cards to player and banker
						playerHand = theDealer.dealHand();
						bankerHand = theDealer.dealHand();
						
						
						// Pauses player hand for animation
						pausePlayerCards.setOnFinished(e -> {// Display banker hand
								// Display player hand
								playerCardOne.setImage(new Image(playerHand.get(0).imageName));
								playerCardTwo.setImage(new Image(playerHand.get(1).imageName));
							}
						);
						
						// Pauses banker hand for animation
						pauseBankerCards.setOnFinished(e -> {// Display banker hand
							// Display banker hand
							bankerCardOne.setImage(new Image(bankerHand.get(0).imageName));
							bankerCardTwo.setImage(new Image(bankerHand.get(1).imageName)); 
							}
						);
						
						pausePlayerCards.play(); // Starts animation for two cards for player
						pauseBankerCards.play(); // Starts animation for two cards for banker
						
						
						// Evaluate who won
						String winnerString = gameLogic.whoWon(bankerHand, playerHand);
						if(winnerString != "Draw") {
							winner.setText(winnerString + " Won");
						}
						else {
							winner.setText(winnerString);
						}
						
				        
						// Waits after all cards drawn to display winner
						pauseWinnerDisplay.setOnFinished(e -> {
							winner.setVisible(true); // Display winner after all cards shown
							
							// Update total winnings
							if(userBetOn == winnerString) {
								totalWinnings += currentBet;
							}
							else {
								totalWinnings -= currentBet;
								
							}
							
							// Put Scroll text information of each Round
							Text handTotals = new Text();
							handTotals.setText("Player Total: " + gameLogic.handTotal(playerHand) + "   Banker Total: " + gameLogic.handTotal(bankerHand));
							
							Text whoWon = new Text();
							if(winnerString != "Draw") {
								whoWon.setText(winnerString + " Won");
							}
							else {
								whoWon.setText(winnerString);
							}
							
							Text userWon = new Text();
							if(userBetOn == winnerString) {
								userWon.setText("Congrats, you bet " + userBetOn + "! You win!\n");
							}
							else {
								userWon.setText("Sorry, you bet " + userBetOn + "! You lost your bet!\n");
							}
							
							VBox tempInfo = new VBox();
							tempInfo.getChildren().addAll(handTotals, whoWon, userWon);
							
							roundInfo.getChildren().add(0, tempInfo);
							scrollpane.setContent(roundInfo);
				
							totalWinningsText.setText(String.valueOf(totalWinnings));
							bet2placeinput.setDisable(false); // Enable input after displaying winner
							bankButton.setDisable(true); // disable  bankButton
							playerButton.setDisable(false); // Enable player button
							drawButton.setDisable(false); // Enable Draw button
							userBetOn = "Banker"; // Reset Default User bet on banker
						});
						
				        // Wait one second to display third card if drawn for player
						pausePlayerThird.setOnFinished(e -> {
								if(playerHand.size() == 3) {
									playerCardThree.setImage(new Image(playerHand.get(2).imageName));
								}
							}
						);
						
						// Wait one second to display third card if drawn for banker
						pauseBankerThird.setOnFinished(e -> {
								if(bankerHand.size() == 3) {
									bankerCardThree.setImage(new Image(bankerHand.get(2).imageName));
								}
								
							}
						);
						
						pausePlayerThird.play(); // Resume animation for third card drawn if any for player
						pauseBankerThird.play(); // Resume animation for third card drawn if any for banker
						pauseWinnerDisplay.play(); // Resume animation for displaying winner
				
					}
				}
				
			}
		}); 
		
		
		
		return newgameScene;
	}
	
}
