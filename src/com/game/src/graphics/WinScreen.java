package com.game.src.graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import com.game.src.constants.FinalScreenConstants;
import com.game.src.constants.GameConstants.STATE;
import com.game.src.main.Game;

public class WinScreen {
	Game game;
	public WinScreen(Game game){
		this.game = game;
	}
	
	public static Rectangle menuButton = new Rectangle(FinalScreenConstants.getWinScreenMenuButtonCoordX(), FinalScreenConstants.getWinScreenMenuButtonCoordY(), FinalScreenConstants.getButtonMenuWidth(), FinalScreenConstants.getButtonMenuHeight());
	public static Rectangle quitButton = new Rectangle(FinalScreenConstants.getWinScreenQuitButtonCoordX(), FinalScreenConstants.getWinScreenQuitButtonCoordY(), FinalScreenConstants.getButtonMenuWidth(), FinalScreenConstants.getButtonMenuHeight());
	public static Rectangle saveButton = new Rectangle(FinalScreenConstants.getWinScreenSaveButtonCoordX(), FinalScreenConstants.getWinScreenSaveButtonCoordY(), FinalScreenConstants.getButtonSaveWidth(), FinalScreenConstants.getButtonSaveHeight());
	
	public static void drawMenuButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(menuButton);
	}
	
	public static void drawQuitButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(quitButton);
	}
	
	public static void drawSaveButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(saveButton);
	}
	
	public static void drawButtons(java.awt.Graphics g){
		if(Game.getState() == STATE.WIN){
			drawMenuButton(g);
			drawQuitButton(g);
			drawSaveButton(g);
		}
	}
	
	public void winScreenMenu() throws IOException{
		//Game.setState(STATE.MENU);
		game.resetVariables();
		game.init();
	}
	
}
