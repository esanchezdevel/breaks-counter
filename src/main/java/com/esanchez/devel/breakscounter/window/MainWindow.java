package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle(Constants.APP_TITLE);
		
		Pane root = new Pane();
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
		
		Button button = new Button("Go to next window");
		
		button.setOnAction(e -> showSecondWindow(stage));
		
		root.getChildren().addAll(title, button);
		
		// Create the scene and show the window
		Scene scene = new Scene(root, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		
		stage.setScene(scene);
		stage.show();
		
        // Place the elements in the right place when we have all elements size available
        Platform.runLater(() -> {
            title.setLayoutX((Constants.APP_WIDTH / 2) - (title.getWidth() / 2));
    		title.setLayoutY(50);

    		button.setLayoutX((Constants.APP_WIDTH / 2) - (button.getWidth() / 2));
    		button.setLayoutY(title.getHeight() + 70);
        });
	}
	
	private void showSecondWindow(Stage stage) {
		SecondWindow secondWindow = new SecondWindow();
		try {
			secondWindow.start(new Stage());
		} catch (Exception e) {
			System.out.println("Error starting second window");
			e.printStackTrace();
		}
	}
}
