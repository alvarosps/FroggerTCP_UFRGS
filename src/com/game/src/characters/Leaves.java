package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.constants.GameConstants;
import com.game.src.graphics.Textures;
import com.game.src.main.Game;

public class Leaves implements FriendlyEntity{
	private static boolean frogOnLeaveLeft = false;
	private static boolean frogOnLeaveMiddle = false;
	private static boolean frogOnLeaveRight = false;
	
	Game game;
	
	private double coordX;
	private double coordY;
	
	public Leaves(double coordX, double coordY, Game game){
		this.game = game;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public void tick() {
		if(frogOnLeaveLeft && frogOnLeaveMiddle && frogOnLeaveRight)
			game.setGameWon(true);
	}

	public void render(Graphics g) {
		if(frogOnLeaveLeft)
			g.drawImage(Textures.finish, GameConstants.getFinalCoordXleft(), GameConstants.getFinalCoordY(), null);
		if(frogOnLeaveMiddle)
			g.drawImage(Textures.finish, GameConstants.getFinalCoordXmiddle(), GameConstants.getFinalCoordY(), null);
		if(frogOnLeaveRight)
			g.drawImage(Textures.finish, GameConstants.getFinalCoordXright(), GameConstants.getFinalCoordY(), null);
	}

	public Rectangle getBounds() {
		return new Rectangle( (int)coordX, (int)coordY, GameConstants.getSpriteSheetSize(), GameConstants.getSpriteSheetSize());
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public static boolean getFrogOnLeaveLeft(){
		return frogOnLeaveLeft;
	}
	
	public static boolean getFrogOnLeaveMiddle(){
		return frogOnLeaveMiddle;
	}
	
	public static boolean getFrogOnLeaveRight(){
		return frogOnLeaveRight;
	}
	
	public static void setFrogOnLeaveLeft(boolean frogOnLeaveLeft){
		Leaves.frogOnLeaveLeft = frogOnLeaveLeft;
	}
	
	public static void setFrogOnLeaveMiddle(boolean frogOnLeaveMiddle){
		Leaves.frogOnLeaveMiddle = frogOnLeaveMiddle;
	}
	
	public static void setFrogOnLeaveRight(boolean frogOnLeaveRight){
		Leaves.frogOnLeaveRight = frogOnLeaveRight;
	}
}
