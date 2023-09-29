package com.esanchez.devel.breakscounter.window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SecondWindow extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Second Window");
		
		Button button = new Button("Return to main window");
		
		button.setOnAction(e -> stage.close());
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		
		Scene scene = new Scene(root, 300, 250);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
