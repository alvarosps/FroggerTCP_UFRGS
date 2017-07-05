package com.game.src.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.game.src.constants.GameConstants;
import com.game.src.main.Game;

public class FrameStarter {
	
	private static int WIDTH = GameConstants.getScreenWidth();
	private static int HEIGHT = GameConstants.getScreenHeight();
	private static String TITLE = GameConstants.getGameTitle();
	
	public static void initFrame(Game game){
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT ));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
