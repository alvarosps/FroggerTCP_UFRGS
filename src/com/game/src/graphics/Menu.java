package com.game.src.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.constants.GameConstants.STATE;
import com.game.src.constants.MenuConstants;
import com.game.src.main.Game;

public class Menu {

	public Rectangle playButton = new Rectangle(MenuConstants.getButtonpositionwidth(), MenuConstants.getPlayButtonHeight(), MenuConstants.getButtonwidth(), MenuConstants.getButtonheight());
	public Rectangle highscoresButton = new Rectangle(MenuConstants.getButtonpositionwidth(), MenuConstants.getHighscoresButtonHeight(), MenuConstants.getButtonwidth(), MenuConstants.getButtonheight());
	public Rectangle helpButton = new Rectangle(MenuConstants.getButtonpositionwidth(), MenuConstants.getHelpButtonHeight(), MenuConstants.getButtonwidth(), MenuConstants.getButtonheight());
	public Rectangle quitButton = new Rectangle(MenuConstants.getButtonpositionwidth(), MenuConstants.getQuitButtonHeight(), MenuConstants.getButtonwidth(), MenuConstants.getButtonheight());

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (Game.getState() == STATE.MENU) {
			
			g2d.draw(playButton);
			
			g2d.draw(highscoresButton);
			
			g2d.draw(helpButton);
			
			g2d.draw(quitButton);
		}
	}
}
