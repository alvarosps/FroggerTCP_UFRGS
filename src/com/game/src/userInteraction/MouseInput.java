package com.game.src.userInteraction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import com.game.src.constants.MenuConstants;
import com.game.src.graphics.GameOverScreen;
import com.game.src.graphics.WinScreen;
import com.game.src.constants.FinalScreenConstants;
import com.game.src.constants.GameConstants.STATE;
import com.game.src.main.Game;

public class MouseInput implements MouseListener{
	
	Game game;
	
	WinScreen winScreen;
	GameOverScreen gameOverScreen;
	
	
	public MouseInput(Game game, WinScreen winScreen, GameOverScreen gameOverScreen){
		this.game = game;
		this.winScreen = winScreen;
		this.gameOverScreen = gameOverScreen;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		//public Rectangle playButton = new Rectangle(Game.WIDTH / 2 +120,150,100,50);
		//public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 +120,250,100,50);
		//public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 +120,350,100,50);
		
		if (Game.getState() == STATE.MENU) {

			// Play Button
			if (mx >= MenuConstants.getButtonpositionwidth() && mx <= MenuConstants.getButtonpositionwidth() + MenuConstants.getButtonwidth()) {
				if (my >= MenuConstants.getPlayButtonHeight() && my <= MenuConstants.getPlayButtonHeight() + MenuConstants.getButtonheight()) {
					// Play button
					Game.setState(STATE.GAME);
				}
			}

			// HighScores
			if (mx >= MenuConstants.getButtonpositionwidth() && mx <= MenuConstants.getButtonpositionwidth() + MenuConstants.getButtonwidth()) {
				if (my >= MenuConstants.getHighscoresButtonHeight() && my <= MenuConstants.getHighscoresButtonHeight() + MenuConstants.getButtonheight()) {
					// Highscores button
					Game.setState(STATE.HIGHSCORE);
				}
			}

			// Help Button
			if (mx >= MenuConstants.getButtonpositionwidth() && mx <= MenuConstants.getButtonpositionwidth() + MenuConstants.getButtonwidth()) {
				if (my >= MenuConstants.getHelpButtonHeight() && my <= MenuConstants.getHelpButtonHeight() + MenuConstants.getButtonheight()) {
					// Help button
					Game.setState(STATE.HELP);
				}
			}
			// Quit Button
			if (mx >= MenuConstants.getButtonpositionwidth() && mx <= MenuConstants.getButtonpositionwidth() + MenuConstants.getButtonwidth()) {
				if (my >= MenuConstants.getQuitButtonHeight() && my <= MenuConstants.getQuitButtonHeight() + MenuConstants.getButtonheight()) {
					// Quit button
					if(Game.getFirstTimeRunningGame())
						System.exit(1);
					else
						game.stop();
				}
			}
		}
		if (Game.getState() == STATE.HIGHSCORE) {
			if(mx >= MenuConstants.getMenuHighcoresCoordX() && mx <= MenuConstants.getMenuHighcoresCoordX() + MenuConstants.getMenuButtonWidth()){
				if(my >= MenuConstants.getMenuHighscoresCoordY() && my <= MenuConstants.getMenuHighscoresCoordY() + MenuConstants.getMenuButtonHeight()){
					Game.setState(STATE.MENU);
				}
			}
		}
		if(Game.getState() == STATE.HELP){
			if(mx >= MenuConstants.getMenuHelpCoordX() && mx <= MenuConstants.getMenuHelpCoordX() + MenuConstants.getMenuButtonWidth()){
				if(my >= MenuConstants.getMenuHelpCoordY() && my <= MenuConstants.getMenuHelpCoordY() + MenuConstants.getMenuButtonHeight()){
					Game.setState(STATE.MENU);
				}
			}
		}
		if(Game.getState() == STATE.WIN){
			if(mx >= FinalScreenConstants.getWinScreenQuitButtonCoordX() && mx <= FinalScreenConstants.getButtonMenuWidth() + FinalScreenConstants.getWinScreenQuitButtonCoordX()){
				if(my >= FinalScreenConstants.getWinScreenQuitButtonCoordY() && my <= FinalScreenConstants.getWinScreenQuitButtonCoordY() + FinalScreenConstants.getButtonMenuHeight()){
					//Quit Button
					game.stop();
				}
			}
			if(mx >= FinalScreenConstants.getWinScreenMenuButtonCoordX() && mx <= FinalScreenConstants.getButtonMenuWidth() + FinalScreenConstants.getWinScreenMenuButtonCoordX()){
				if(my >= FinalScreenConstants.getWinScreenMenuButtonCoordY() && my <= FinalScreenConstants.getWinScreenMenuButtonCoordY() + FinalScreenConstants.getButtonMenuHeight()){
					//Menu Button
					Game.setState(STATE.MENU);
					try {
						winScreen.winScreenMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(mx >= FinalScreenConstants.getWinScreenSaveButtonCoordX() && mx <= FinalScreenConstants.getWinScreenSaveButtonCoordX() + FinalScreenConstants.getButtonSaveWidth()){
				if(my >= FinalScreenConstants.getWinScreenSaveButtonCoordY() && my <= FinalScreenConstants.getWinScreenSaveButtonCoordY() + FinalScreenConstants.getButtonSaveHeight()){
					//Save Button
					game.getGameHighscores().addHighscore(game.getNameInput(), game.getGameScore().getScore());
				}
			}
		}
		if(Game.getState() == STATE.GAMEOVER){
			if(mx >= FinalScreenConstants.getGameOverScreenQuitButtonCoordX() && mx <= FinalScreenConstants.getGameOverScreenQuitButtonCoordX() + FinalScreenConstants.getButtonMenuWidth()){
				if(my >= FinalScreenConstants.getGameOverScreenQuitButtonCoordY() && my <= FinalScreenConstants.getGameOverScreenQuitButtonCoordY() + FinalScreenConstants.getButtonMenuHeight()){
					//Quit Button
					game.stop();
				}
			}
			if(mx >= FinalScreenConstants.getGameOverScreenMenuButtonCoordX() && mx <= FinalScreenConstants.getGameOverScreenMenuButtonCoordX() + FinalScreenConstants.getButtonMenuWidth()){
				if(my >= FinalScreenConstants.getGameOverScreenMenuButtonCoordY() && my <= FinalScreenConstants.getGameOverScreenMenuButtonCoordY() + FinalScreenConstants.getButtonMenuHeight()){
					//Menu
					Game.setState(STATE.MENU);
					try {
						gameOverScreen.gameOverScreenMenu();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

