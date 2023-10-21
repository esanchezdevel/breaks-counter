package com.esanchez.devel.breakscounter.util;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CustomFonts {

	private CustomFonts() {
		
	}
	
	public static Font title() {
		return Font.font(Constants.FONT_VERDANA, FontWeight.BOLD, 24);
	}
	
	public static Font text() {
		return Font.font(Constants.FONT_VERDANA, 12);
	}
	
	public static Font button() {
		return Font.font(Constants.FONT_VERDANA, FontWeight.BOLD, FontPosture.ITALIC, 12);
	}
}
