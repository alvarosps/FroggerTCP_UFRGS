package com.game.src.graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import com.game.src.constants.FinalScreenConstants;
import com.game.src.constants.GameConstants.STATE;
import com.game.src.main.Game;

public class GameOverScreen {
	public static Rectangle quitButton = new Rectangle(FinalScreenConstants.getGameOverScreenQuitButtonCoordX(), FinalScreenConstants.getGameOverScreenQuitButtonCoordY(), FinalScreenConstants.getButtonMenuWidth(), FinalScreenConstants.getButtonMenuHeight());
	public static Rectangle menuButton = new Rectangle(FinalScreenConstants.getGameOverScreenMenuButtonCoordX(), FinalScreenConstants.getGameOverScreenMenuButtonCoordY(), FinalScreenConstants.getButtonMenuWidth(), FinalScreenConstants.getButtonMenuHeight());
	
	Game game;
	
	public GameOverScreen(Game game){
		this.game = game;
	}
	
	public static void drawQuitButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(quitButton);
	}
	
	public static void drawMenuButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(menuButton);
	}
	
	public static void drawButtons(java.awt.Graphics g){
		if(Game.getState() == STATE.GAMEOVER){
			drawQuitButton(g);
			drawMenuButton(g);
		}
	}
	
	public void gameOverScreenMenu() throws IOException{
		//Game.setState(STATE.MENU);
		game.resetVariables();
		game.init();
	}
}
