package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWindow {

	// store positions in static variables because the width and height of the
	// elements are not accesible when we change from one window to another
	private static double titleX = 0.0;
	private static double descriptionX = 0.0;
	private static double buttonX = 0.0;
	private static double buttonY = 0.0;
	
	public static void show(Stage stage) {
		Pane layout = new Pane();
		
		Font fontTitle = Font.font("Verdana", FontWeight.BOLD, 24);
		Font fontText = Font.font("Verdana", 12);
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setFont(fontTitle);
		
		String descriptionString = "Choose the frequency of how often you want to be notified to take a break:";
		Label description = new Label(descriptionString);
		description.setFont(fontText);
		
		
		// Options available to configure by the user:
		// 1. Hours
		// 2. Minutes
		
		Button button = new Button("Go to next window");
		
		button.setOnAction(e -> SecondWindow.show(stage));
		
		layout.getChildren().addAll(title, description, button);
		
		RootWindow.rootLayout.setCenter(layout);
		
        // Place the elements in the right place when we have all elements size available
        Platform.runLater(() -> {
        	if (title.getWidth() > 0) {
              titleX = (Constants.APP_WIDTH / 2) - (title.getWidth() / 2);           		
        	}
        	title.setLayoutX(titleX);
    		title.setLayoutY(50);
    		
    		if (description.getWidth() > 0) {
    			descriptionX = (Constants.APP_WIDTH / 2) - (description.getWidth() / 2);
    		}
    		description.setLayoutX(descriptionX);
    		description.setLayoutY(title.getHeight() + 70);

    		if (button.getWidth() > 0) {
    			buttonX = (Constants.APP_WIDTH / 2) - (button.getWidth() / 2); 
    		}

        	if (title.getHeight() > 0) {
        		buttonY = title.getHeight() + 100;
        	}
    		
    		button.setLayoutX(buttonX);
    		button.setLayoutY(buttonY);
        });
	}
}
