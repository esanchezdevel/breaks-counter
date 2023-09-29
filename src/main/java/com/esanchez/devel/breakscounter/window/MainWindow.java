package com.esanchez.devel.breakscounter.window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

	public static void main(String[] args) {
		System.out.println("Launching main window");
		launch(args);
		System.out.println("Main window launched");
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Breaks Counter");
		
		Button button = new Button("Go to next window");
		
		button.setOnAction(e -> showSecondWindow(stage));
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		
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
