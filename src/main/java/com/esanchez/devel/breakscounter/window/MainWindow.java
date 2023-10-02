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

	private static double startButtonX = 0.0;
	private static double startButtonY = 320.00;

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

		Button startButton = new Button("Start");
		startButton.setFont(fontButton);
		startButton.setTextFill(Color.WHITE);
		startButton.setStyle("-fx-background-color: #2874a6; -fx-border-color: #17202a; -fx-border-width: 0 2 2 0;");
		startButton.setPrefWidth(100);

		startButton.setOnAction(e -> SecondWindow.show(stage));

		layout.getChildren().addAll(title, description, formHoursLabel, formHoursCombo, formMinutesLabel,
				formMinutesCombo, startButton);

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

			if (startButton.getWidth() > 0) {
				startButtonX = (Constants.APP_WIDTH / 2) - (startButton.getWidth() / 2);
			}

			startButton.setLayoutX(startButtonX);
			startButton.setLayoutY(startButtonY);
		});
	}
}
