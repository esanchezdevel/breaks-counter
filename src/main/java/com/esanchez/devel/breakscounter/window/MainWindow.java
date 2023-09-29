package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow {

	public static void show(Stage stage) {
		Pane layout = new Pane();
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
		
		Button button = new Button("Go to next window");
		
		button.setOnAction(e -> SecondWindow.show(stage));
		
		layout.getChildren().addAll(title, button);
		
		RootWindow.rootLayout.setCenter(layout);
		
        // Place the elements in the right place when we have all elements size available
        Platform.runLater(() -> {
            title.setLayoutX((Constants.APP_WIDTH / 2) - (title.getWidth() / 2));
    		title.setLayoutY(50);

    		button.setLayoutX((Constants.APP_WIDTH / 2) - (button.getWidth() / 2));
    		button.setLayoutY(title.getHeight() + 70);
        });
	}
}
