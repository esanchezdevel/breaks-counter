package com.esanchez.devel.breakscounter.window;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SecondWindow {

	public static void show(Stage stage) {

		stage.setTitle("Second Window");
		
		Button button = new Button("Return to main window");
		
		button.setOnAction(e -> MainWindow.show(stage));
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		
		RootWindow.rootLayout.setCenter(root);	}
}
