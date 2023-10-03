package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NotificationWindow extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Notification");

		Pane layout = new Pane();

		// Create window elements
		Label title = new Label("Notification");

		layout.getChildren().addAll(title);

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

		stage.setScene(scene);
		stage.show();
	}
}
