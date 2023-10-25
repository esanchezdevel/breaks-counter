package com.esanchez.devel.breakscounter.window;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.esanchez.devel.breakscounter.util.Constants;
import com.esanchez.devel.breakscounter.util.CustomFonts;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow {

	private static final Logger logger = LogManager.getLogger(MainWindow.class);
	
	private static final int WAIT_TIME = 30000;
	
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
	private static ComboBox<String> formHoursCombo;
	private static ComboBox<String> formMinutesCombo;
	
	private static Thread processThread;
	
	private MainWindow() {
		
	}
	
	public static void show(Stage stage) {
		Pane layout = new Pane();

		Color customBlue = Color.web("#2874a6");
		
		// Create window elements
		Label title = new Label(Constants.APP_TITLE);
		title.setFont(CustomFonts.title());
		title.setTextFill(customBlue);

		Label description = new Label("Choose the frequency of how often you want to be notified to take a break:");
		description.setFont(CustomFonts.text());

		// Options available to configure by the user:
		// 1. Hours
		// 2. Minutes
		Label formHoursLabel = new Label("Hours:");
		formHoursLabel.setFont(CustomFonts.text());

		formHoursCombo = new ComboBox<>();
		formHoursCombo.getItems().addAll("00", "01", "02");
		formHoursCombo.setValue("00");

		Label formMinutesLabel = new Label("Minutes:");
		formMinutesLabel.setFont(CustomFonts.text());

		formMinutesCombo = new ComboBox<>();
		formMinutesCombo.getItems().addAll("00", "10", "20", "30", "40", "50");
		formMinutesCombo.setValue("00");

		startStopButton = new Button("Start");
		startStopButton.setFont(CustomFonts.button());
		startStopButton.setTextFill(Color.WHITE);
		startStopButton.setStyle("-fx-background-color: #2874a6; -fx-border-color: #17202a; -fx-border-width: 0 2 2 0;");
		startStopButton.setPrefWidth(100);

		startStopButton.setOnAction(event -> {
			if (!isStarted)
				isStarted = true;
			else
				isStarted = false;
			
			startStopNotifications();
		});

		stage.setOnCloseRequest(event -> {
            logger.info("User is closing the window...");

            if (isStarted) {
            	isStarted = false;
            	processThread.interrupt();
            }
        });
		
		layout.getChildren().addAll(title, description, formHoursLabel, formHoursCombo, formMinutesLabel,
				formMinutesCombo, startStopButton);

		RootWindow.getRootLayout().setCenter(layout);

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
	
	private static void startStopNotifications() {
		logger.info("Start/Stop Notifications with isStarted: {}", isStarted);
		
		// Print the right text on the START/STOP button
		startStopButton.setText(isStarted ? "Stop" : "Start");
		
		if (isStarted) {
			// Start the process to show notifications
			final long waitTime = calculateWaitTime();
			
			isStarted = true;

			// Asynchronous thread where control when the notification window has to be shown
			processThread = new Thread(() -> {
				logger.info("Starting processThread...");
				long startTime = System.currentTimeMillis();
				while (isStarted) {
					waitSeconds();
					
					if (processThread.isInterrupted()) {
						logger.info("The thread was interrupted. Exit");
						break;
					}
					
					long currentTime = System.currentTimeMillis();
					if (currentTime - startTime > waitTime) {
						logger.info("Show new notification: {}", LocalDateTime.now());

						Platform.runLater(() -> {
							NotificationWindow notificationWindow = new NotificationWindow();
							showNotification(notificationWindow);							
						});
						
						startTime = currentTime;
					}
				}
				logger.info("Finishing thread...");
			});
			processThread.start();
		} else {
			// Setting isStarted to "false" we can stop the process executed in the thread
			logger.info("Setting isStarted to false...");
			isStarted = false;
			processThread.interrupt();
		}
	}

	private static void showNotification(NotificationWindow notificationWindow) {
		try {
			notificationWindow.start(new Stage());
		} catch (Exception e) {
			logger.info("Error starting notification window");
			e.printStackTrace();
		}
	}

	private static void waitSeconds() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			logger.info("Thread interrupted while waiting for next iteration");
			Thread.currentThread().interrupt();
		}
	}

	private static long calculateWaitTime() {
		long hours = Integer.parseInt(formHoursCombo.getValue());
		long minutes = Integer.parseInt(formMinutesCombo.getValue());
		
		logger.info("User selected Hours: {}, Minutes: {}", hours, minutes);
		
		long hoursMilliseconds = hours * 60 * 60 * 1000;
		long minutesMilliseconds = minutes *60 * 1000;
		
		return hoursMilliseconds + minutesMilliseconds;
	}
}
