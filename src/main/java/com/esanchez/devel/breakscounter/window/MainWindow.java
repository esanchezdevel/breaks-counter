package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWindow {

	// store positions in static variables because the width and height of the
	// elements are not accesible when we change from one window to another
	private static double titleX = 0.0;
	private static double titleY = 50.0;
	
	private static double descriptionX = 0.0;
	private static double descriptionY = 120.00;
	
	private static double formHoursLabelX = 0.0;
	private static double formHoursLabelY = 240.0;
	
	private static double formHoursComboX = 0.0;
	private static double formHoursComboY = 240.0;
	
	private static double formMinutesLabelX = 0.0;
	private static double formMinutesLabelY = 300.0;
	
	private static double formMinutesComboX = 0.0;
	private static double formMinutesComboY = 300.0;
	
	private static double buttonX = 0.0;
	private static double buttonY = 0.0;
	
	public static void show(Stage stage) {
		Pane layout = new Pane();
		
		Font fontTitle = Font.font("Verdana", FontWeight.BOLD, 24);
		Font fontText = Font.font("Verdana", 12);
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setFont(fontTitle);

		Label description = new Label("Choose the frequency of how often you want to be notified to take a break:");
		description.setFont(fontText);
		
		// Options available to configure by the user:
		// 1. Hours
		// 2. Minutes
		
		Label formHoursLabel = new Label("Hours:");
		formHoursLabel.setFont(fontText);
		
		ComboBox<String> formHoursCombo = new ComboBox<>();
		formHoursCombo.getItems().addAll("0", "1", "2");
		formHoursCombo.setValue("0");
		
		Label formMinutesLabel = new Label("Minutes:");
		formMinutesLabel.setFont(fontText);

		ComboBox<String> formMinutesCombo = new ComboBox<>();

        for (int i = 0; i <= 59; i++) {
        	formMinutesCombo.getItems().add(String.valueOf(i));
        }
		formMinutesCombo.setValue("0");
		
		Button button = new Button("Start");
		
		button.setOnAction(e -> SecondWindow.show(stage));
		
		layout.getChildren().addAll(title, description, formHoursLabel, formHoursCombo, formMinutesLabel, formMinutesCombo, button);
		
		RootWindow.rootLayout.setCenter(layout);
		
        // Place the elements in the right place when we have all elements size available
        Platform.runLater(() -> {
        	if (title.getWidth() > 0) {
              titleX = (Constants.APP_WIDTH / 2) - (title.getWidth() / 2);           		
        	}
        	title.setLayoutX(titleX);
    		title.setLayoutY(titleY);
    		
    		if (description.getWidth() > 0) {
    			descriptionX = (Constants.APP_WIDTH / 2) - (description.getWidth() / 2);
    		}
    		description.setLayoutX(descriptionX);
    		description.setLayoutY(descriptionY);

   			formHoursLabelX = 100;
    		formHoursLabel.setLayoutX(formHoursLabelX);
    		formHoursLabel.setLayoutY(formHoursLabelY);
    		
   			formHoursComboX = 300;
    		formHoursCombo.setLayoutX(formHoursComboX);
    		formHoursCombo.setLayoutY(formHoursComboY);

   			formMinutesLabelX = 100;
    		formMinutesLabel.setLayoutX(formMinutesLabelX);
    		formMinutesLabel.setLayoutY(formMinutesLabelY);
    		
   			formMinutesComboX = 300;
    		formMinutesCombo.setLayoutX(formMinutesComboX);
    		formMinutesCombo.setLayoutY(formMinutesComboY);
    		
    		if (button.getWidth() > 0) {
    			buttonX = (Constants.APP_WIDTH / 2) - (button.getWidth() / 2); 
    		}

        	if (title.getHeight() > 0) {
        		buttonY = 700;
        	}
    		
    		button.setLayoutX(buttonX);
    		button.setLayoutY(buttonY);
        });
	}
}
