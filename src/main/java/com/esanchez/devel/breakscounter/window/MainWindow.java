package com.esanchez.devel.breakscounter.window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private static final String TITLE = "Breaks Counter";
	
	public static void main(String[] args) {
		System.out.println("Launching main window");
		launch(args);
		System.out.println("Main window launched");
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle(TITLE);
		
		Pane root = new Pane();
		
		Label title = new Label(TITLE);
		title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
		title.setLayoutX(10);
		title.setLayoutY(10);
		
		Button button = new Button("Go to next window");
		button.setLayoutX(100);
		button.setLayoutY(50);
		
		button.setOnAction(e -> showSecondWindow(stage));
		
		root.getChildren().addAll(title, button);
		
		Scene scene = new Scene(root, 300, 250);
		
		stage.setScene(scene);
		System.out.println("Showing main window");
		stage.show();
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
