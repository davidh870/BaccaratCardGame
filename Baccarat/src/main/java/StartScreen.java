import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StartScreen extends BaccaratGame{
	/* Creates a start screen scene and returns the scene */
	public static Scene startCreate() {
		/* Load Image */
		Image startBackground = new Image("StartScreen.jpg", 1280, 720, false, false);
		
		/* Create a background from startBackground IMAGE */
	    Background background = new Background(new BackgroundImage(startBackground, null, null, null, null));
	    
		/* Create Border Pane */
		BorderPane startBorder = new BorderPane();
	
		/* Create buttons and text field */
		Button startButton = new Button("Start");
		Button quitButton = new Button("Quit");
		
		/* Create vBox with buttons */
		VBox vBox = new VBox(8); // Add 8 spacing between buttons
		vBox.getChildren().addAll(startButton,quitButton);
		vBox.setAlignment(Pos.CENTER);
		
		/* Set button actions */
		// Switch to game scene when player presses start button 
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				window.setScene(gameScene);
				window.show();
			}
		});
		
		// Game quits when player presses quit
		quitButton.setOnAction(e -> Platform.exit());
		
		
		
		/* Add vBox to the Border pane */
		startBorder.setBackground(background);
		startBorder.setCenter(vBox);
		
		/* Create Start Scene */
		Scene startScene = new Scene(startBorder,1280,720);
		
		return startScene;
	}
	
}
