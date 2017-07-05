package com.game.src.constants;

import java.awt.Font;

public class MenuConstants {

	// fonts
	private static final Font titleFont = new Font("arial", Font.BOLD, 50);
	private static final Font buttonFont = new Font("arial", Font.BOLD, 30);

	// title positioning
	private static final int menuPositionWidth = (GameConstants.getScreenWidth() / 2) - 150;
	private static final int menuPositionHeight = 360;
	private static final int hichscorePositionWidth = (GameConstants.getScreenWidth() / 2) - 150;
	private static final int highscorePositionHeight = 150;

	// menu buttons
	private static final int spaceBetweenButtons = 75;
	private static final int buttonPositionWidth = GameConstants.getScreenHeight() / 2 + 170;
	private static final int playButtonHeight = 350;
	private static final int highscoresButtonHeight = playButtonHeight + spaceBetweenButtons;
	private static final int helpButtonHeight = highscoresButtonHeight + spaceBetweenButtons;
	private static final int quitButtonHeight = helpButtonHeight + spaceBetweenButtons;
	private static final int buttonWidth = 250;
	private static final int buttonHeight = 60;
	
	//help menu button
	private static final int menuHelpCoordX = GameConstants.getScreenWidth() - 200;
	private static final int menuHelpCoordY = GameConstants.getScreenHeight() - 80;
	
	//highscores menu button
	private static final int menuHighscoresCoordX = GameConstants.getScreenWidth() - 170;
	private static final int menuHighscoresCoordY = GameConstants.getScreenHeight() - 65;
	
	//help and highscores menu button sizes
	private static final int menuButtonWidth = 125;
	private static final int menuButtonHeight = 40;
	
	public static int getMenuHelpCoordX() {
		return menuHelpCoordX;
	}

	public static int getMenuHelpCoordY() {
		return menuHelpCoordY;
	}

	public static int getMenuHighcoresCoordX() {
		return menuHighscoresCoordX;
	}

	public static int getMenuHighscoresCoordY() {
		return menuHighscoresCoordY;
	}

	public static int getMenuButtonWidth() {
		return menuButtonWidth;
	}

	public static int getMenuButtonHeight() {
		return menuButtonHeight;
	}

	public static Font getTitlefont() {
		return titleFont;
	}

	public static Font getButtonfont() {
		return buttonFont;
	}

	public static int getMenupositionwidth() {
		return menuPositionWidth;
	}

	public static int getMenupositionheight() {
		return menuPositionHeight;
	}

	public static int getHichscorepositionwidth() {
		return hichscorePositionWidth;
	}

	public static int getHighscorepositionheight() {
		return highscorePositionHeight;
	}

	public static int getButtonpositionwidth() {
		return buttonPositionWidth;
	}

	public static int getButtonwidth() {
		return buttonWidth;
	}

	public static int getButtonheight() {
		return buttonHeight;
	}

	public static int getPlayButtonHeight() {
		return playButtonHeight;
	}

	public static int getHighscoresButtonHeight() {
		return highscoresButtonHeight;
	}

	public static int getHelpButtonHeight() {
		return helpButtonHeight;
	}
	
	public static int getQuitButtonHeight(){
		return quitButtonHeight;
	}

}
