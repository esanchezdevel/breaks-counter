package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow {

	// store positions in static variables because the width and height of the
	// elements are not accesible when we change from one window to another
	private static double titleX = 0.0;
	private static double buttonX = 0.0;
	private static double buttonY = 0.0;
	
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
        	if (title.getWidth() > 0) {
              titleX = (Constants.APP_WIDTH / 2) - (title.getWidth() / 2);           		
        	}
        	title.setLayoutX(titleX);
    		title.setLayoutY(50);

    		if (button.getWidth() > 0) {
    			buttonX = (Constants.APP_WIDTH / 2) - (button.getWidth() / 2); 
    		}

        	if (title.getHeight() > 0) {
        		buttonY = title.getHeight() + 70;
        	}
    		
    		button.setLayoutX(buttonX);
    		button.setLayoutY(buttonY);
        });
	}
}
