package com.game.src.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import com.game.src.constants.GameConstants;
import com.game.src.constants.GameConstants.STATE;
import com.game.src.constants.MenuConstants;
import com.game.src.main.Game;

//HELP and HIGHSCORES
public class ExtraScreens {
	public static Rectangle menuButtonHelp = new Rectangle(MenuConstants.getMenuHelpCoordX(), MenuConstants.getMenuHelpCoordY(), MenuConstants.getMenuButtonWidth(), MenuConstants.getMenuButtonHeight());
	public static Rectangle menuButtonHighscores = new Rectangle(MenuConstants.getMenuHighcoresCoordX(), MenuConstants.getMenuHighscoresCoordY(), MenuConstants.getMenuButtonWidth(), MenuConstants.getMenuButtonHeight());
	
	Game game;
	
	public ExtraScreens(Game game){
		this.game = game;
	}
	
	public void drawHelpButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(Game.getState() == STATE.HELP)
			g2d.draw(menuButtonHelp);
	}
	
	public void drawHighscoresButton(java.awt.Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(Game.getState() == STATE.HIGHSCORE)
			g2d.draw(menuButtonHighscores);
		drawHighscores(g);
	}
	
	public void drawHighscores(java.awt.Graphics g){
		int coordX = GameConstants.getScreenWidth() / 3 - 25;
		int coordY = GameConstants.getScreenHeight() / 3 - 25;
		
		int spaceInX = 400;
		int spaceInY = 50;
		
		List<String> namesList = game.getGameHighscores().getNamesList();
		List<Integer> scoresList = game.getGameHighscores().getScoresList();
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		
		for(int i = 0; i < namesList.size(); i++){
			
			g.drawString(namesList.get(i), coordX, coordY + i*spaceInY);
			g.drawString(Integer.toString(scoresList.get(i)), coordX + spaceInX, coordY + i*spaceInY);
		}
	}
	
}
