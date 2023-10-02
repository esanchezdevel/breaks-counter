package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWindow {

	// store positions in static variables because the width and height of the
	// elements are not accessible when we change from one window to another
	private static double titleX = 0.0;
	private static double titleY = 50.0;

	private static double descriptionX = 0.0;
	private static double descriptionY = 120.00;

	private static double formHoursLabelX = 0.0;
	private static double formHoursLabelY = 200.0;

	private static double formHoursComboX = 0.0;
	private static double formHoursComboY = 200.0;

	private static double formMinutesLabelX = 0.0;
	private static double formMinutesLabelY = 260.0;

	private static double formMinutesComboX = 0.0;
	private static double formMinutesComboY = 260.0;

	private static double startStopButtonX = 0.0;
	private static double startStopButtonY = 320.00;

	private static boolean isStarted = false;
	
	private static Button startStopButton;
	
	public static void show(Stage stage) {
		Pane layout = new Pane();

		Font fontTitle = Font.font("Verdana", FontWeight.BOLD, 24);
		Font fontText = Font.font("Verdana", 12);
		Font fontButton = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12);

		Color customBlue = Color.web("#2874a6");
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setFont(fontTitle);
		title.setTextFill(customBlue);

		Label description = new Label("Choose the frequency of how often you want to be notified to take a break:");
		description.setFont(fontText);

		// Options available to configure by the user:
		// 1. Hours
		// 2. Minutes
		Label formHoursLabel = new Label("Hours:");
		formHoursLabel.setFont(fontText);

		ComboBox<String> formHoursCombo = new ComboBox<>();
		formHoursCombo.getItems().addAll("00", "01", "02");
		formHoursCombo.setValue("00");

		Label formMinutesLabel = new Label("Minutes:");
		formMinutesLabel.setFont(fontText);

		ComboBox<String> formMinutesCombo = new ComboBox<>();

		formMinutesCombo.getItems().addAll("00", "10", "20", "30", "40", "50");
		formMinutesCombo.setValue("00");

		startStopButton = new Button("Start");
		startStopButton.setFont(fontButton);
		startStopButton.setTextFill(Color.WHITE);
		startStopButton.setStyle("-fx-background-color: #2874a6; -fx-border-color: #17202a; -fx-border-width: 0 2 2 0;");
		startStopButton.setPrefWidth(100);

		//startButton.setOnAction(e -> SecondWindow.show(stage));
		startStopButton.setOnAction(e -> {
			if (!isStarted)
				isStarted = true;
			else
				isStarted = false;
			
			startStopNotifications(stage);
		});

		layout.getChildren().addAll(title, description, formHoursLabel, formHoursCombo, formMinutesLabel,
				formMinutesCombo, startStopButton);

		RootWindow.rootLayout.setCenter(layout);

		// Place the elements in the right place when we have all elements size
		// available
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

			formHoursLabelX = 220;
			formHoursLabel.setLayoutX(formHoursLabelX);
			formHoursLabel.setLayoutY(formHoursLabelY);

			formHoursComboX = 300;
			formHoursCombo.setLayoutX(formHoursComboX);
			formHoursCombo.setLayoutY(formHoursComboY);

			formMinutesLabelX = 220;
			formMinutesLabel.setLayoutX(formMinutesLabelX);
			formMinutesLabel.setLayoutY(formMinutesLabelY);

			formMinutesComboX = 300;
			formMinutesCombo.setLayoutX(formMinutesComboX);
			formMinutesCombo.setLayoutY(formMinutesComboY);

			if (startStopButton.getWidth() > 0) {
				startStopButtonX = (Constants.APP_WIDTH / 2) - (startStopButton.getWidth() / 2);
			}

			startStopButton.setLayoutX(startStopButtonX);
			startStopButton.setLayoutY(startStopButtonY);
		});
	}
	
	private static void startStopNotifications(Stage stage) {
		System.out.println("Start/Stop Notifications with isStarted: " + isStarted);
		
		// Print the right text on the START/STOP button
		startStopButton.setText(isStarted ? "Stop" : "Start");
		
		if (isStarted) {
			// Logic to start the process
			// TODO calculate milliseconds of time configured by user
			double waitTime = 30000; // 30seconds to test
			
			isStarted = true;

			Thread processThread = new Thread(() -> {
				System.out.println("Starting processThread...");
				long startTime = System.currentTimeMillis();
				while (isStarted) {
					long currentTime = System.currentTimeMillis();
					if (currentTime - startTime > waitTime) {
						System.out.println("Show notification");
						// TODO show new window with the notification
						startTime = currentTime;
					}
				}
				System.out.println("Finishing thread...");
			});
			processThread.start();
		} else {
			// Setting isStarted to "false" we can stop the process executed in the thread
			System.out.println("Setting isStarted to false...");
			isStarted = false;
		}
	}
}
