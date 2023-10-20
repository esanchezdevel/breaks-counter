package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;
import com.esanchez.devel.breakscounter.util.CustomFonts;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationWindow extends Application {

	private static final double WINDOW_WIDTH = 400.00;
	private static final double WINDOW_HEIGHT = 100.00;
	
	private static final String MESSAGE = "It's time to take a Break!!!";
	
	private double messageX = 0.00;
	private double messageY = WINDOW_HEIGHT / 2;
	
	private double imageDrinkX = WINDOW_WIDTH - 100;
	private double imageDrinkY = 0.00;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle(Constants.APP_TITLE);

		Pane layout = new Pane();

		BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		
		// Create window elements
		Label message = new Label(MESSAGE);
		message.setFont(CustomFonts.text());

		
        ClassLoader classLoader = getClass().getClassLoader();
        String imageDrinkUrl = classLoader.getResource("images/dog-drink-water.gif").toExternalForm();
        String imageExerciseUrl = classLoader.getResource("images/estiramientos1.gif").toExternalForm();
        
		Image imageDrink = new Image(imageDrinkUrl);
		Image imageExercise = new Image(imageExerciseUrl);
		
		ImageView imageViewDrink = new ImageView(imageDrink);
		imageViewDrink.setFitWidth(100);
		imageViewDrink.setFitHeight(100);
		
		ImageView imageViewExercise = new ImageView(imageExercise);
		imageViewExercise.setFitWidth(100);
		imageViewExercise.setFitHeight(100);
		
		layout.getChildren().addAll(message, imageViewDrink, imageViewExercise);

		// Create the scene and show the window
		Scene scene = new Scene(layout, 400, 100);

		// Set window position
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getBounds();

		double screenWidth = bounds.getWidth();
		double screenHeight = bounds.getHeight();
		double windowWidth = 400;
		double windowHeight = 100;

		double x = screenWidth - windowWidth - 8;
		double y = screenHeight - windowHeight - 72;

		stage.setX(x);
		stage.setY(y);

		// Not allow the user to resize the window
		stage.setResizable(false);

		// Logic to close the window after 30seconds
		Duration duration = Duration.seconds(30);
		
		KeyFrame keyFrame = new KeyFrame(duration, event -> {
			stage.close();
		});
		
		Timeline timeline = new Timeline(keyFrame);
		timeline.play();
		
		// Show the window
		stage.setScene(scene);
		stage.toFront();
		stage.setAlwaysOnTop(true);
		stage.requestFocus();
		stage.show();
		
		// Place the elements in the right place when we have all elements size
		// available
		Platform.runLater(() -> {
			if (message.getWidth() > 0) {
				messageX = (WINDOW_WIDTH / 2) - (message.getWidth() / 2);
			}
			message.setLayoutX(messageX);
			message.setLayoutY(messageY);
			
			imageViewDrink.setLayoutX(imageDrinkX);
			imageViewDrink.setLayoutY(imageDrinkY);
		});
	}
}
