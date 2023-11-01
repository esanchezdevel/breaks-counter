package com.esanchez.devel.breakscounter.window;

import com.esanchez.devel.breakscounter.util.Constants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RootWindow extends Application {
	
	// The BorderPane is used to put the windows content in the center
	private static final BorderPane rootLayout = new BorderPane();
	private Stage stage;
	
	public static BorderPane getRootLayout() {
		return rootLayout;
	}
	
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
		BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
		Background background = new Background(backgroundFill);
		rootLayout.setBackground(background);
		
		// Configure the application icon
		Image iconImage = new Image(getClass().getResourceAsStream("/images/icon.png"));
		stage.getIcons().add(iconImage);
		
		MainWindow.show(stage);
		
		// Create the scene and show the window
		Scene scene = new Scene(rootLayout, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		
		// Not allow the user to resize the window
		stage.setResizable(false);
		
		stage.setScene(scene);
		stage.show();
	}
}
