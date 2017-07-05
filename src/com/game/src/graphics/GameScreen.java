package com.game.src.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.game.src.constants.GameConstants;
import com.game.src.main.Game;

public class GameScreen {
	Textures gameTextures;
	Game game;
	
	public GameScreen(Game game){
		this.game = game;
	}
	
	public void displayLives(Graphics g, int numberOfLives){
		int spaceBetweenLives = GameConstants.getSpaceBetweenLives();
		int coordXbase = GameConstants.getCoordXlives();
		int coordYbase = GameConstants.getCoordYlives();
		for(int i = 0; i < numberOfLives; i++){
			g.drawImage(Textures.heart, coordXbase + spaceBetweenLives*i, coordYbase, null);
		}
	}
	
	public void displayScoreGame(Graphics g, int score){
		int coordXbase = GameConstants.getCoordXscore();
		int coordYbase = GameConstants.getCoordYstrings();
		
		Font fnt0 = new Font("arial", Font.PLAIN, GameConstants.getGameScreenFontSize());
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString(Integer.toString(score), coordXbase, coordYbase);
	}
	
	public void displayTimer(Graphics g, int timer){
		int coordXbase = GameConstants.getCoordXtimer();
		int coordYbase = GameConstants.getCoordYstrings();
		
		Font fnt0 = new Font("arial", Font.PLAIN, GameConstants.getGameScreenFontSize());
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString(Integer.toString(timer), coordXbase, coordYbase);
	}
	
	public void displayScoreFinal(Graphics g, int score){
		int coordXbase = GameConstants.getCoordXwinScore();
		int coordYbase = GameConstants.getCoordYwinScore();
		
		Font fnt0 = new Font("arial", Font.BOLD, GameConstants.getWinScreenFontSize());
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString(Integer.toString(score), coordXbase, coordYbase);
	}
	
}
