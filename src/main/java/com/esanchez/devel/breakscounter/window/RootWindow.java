package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootWindow extends Application {
	
	public static BorderPane rootLayout;
	private Stage stage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
	
		this.stage = stage;
		this.stage.setTitle(Constants.APP_TITLE);
		
		initRootLayout();
	}
	
	private void initRootLayout() {
		// The BorderPane is used to put the windows content in the center
		rootLayout = new BorderPane();

		MainWindow.show(stage);
		
		// Create the scene and show the window
		Scene scene = new Scene(rootLayout, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		
		stage.setScene(scene);
		stage.show();
	}
}
