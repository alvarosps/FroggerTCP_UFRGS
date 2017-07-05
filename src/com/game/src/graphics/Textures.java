package com.game.src.graphics;

import java.awt.image.BufferedImage;

import com.game.src.main.Game;

public class Textures {
	private SpriteSheet spriteSheet;
	
	//Create arrays for animation or different spriteSheets
	public static BufferedImage playerFrog;
	public static BufferedImage frogDead;
	
	public static BufferedImage[] frogJumpingAnimation = new BufferedImage[6];
	
	public static BufferedImage enemyBike;
	public static BufferedImage enemyCar;
	public static BufferedImage enemyTruck;
	
	public static BufferedImage finish;
	
	public static BufferedImage heart;
	
	/*
	public BufferedImage[] playerFrog = new BufferedImage[8];
	public BufferedImage[] enemyBike = new BufferedImage[3];
	public BufferedImage[] enemyCar = new BufferedImage[3];
	public BufferedImage[] friendlyTurtle = new BufferedImage[6];
	*/
	
	public Textures(Game game){
		spriteSheet = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures(){
		
		/*Player Animation States
		for(int i = 0; i <8; i++)
			playerFrog[i] = spriteSheet.grabImage(i + 1, "frog");
		for(int i = 0; i < 3; i++)
			enemyBike[i] = spriteSheet.grabImage(i + 1, "bike");
		for(int i = 0; i < 3; i++)
			enemyCar[i] = spriteSheet.grabImage(i + 1,  "car");
		*/
		playerFrog	= spriteSheet.grabImage(1, "frog");
		
		enemyBike 	= spriteSheet.grabImage(1, "bike");
		enemyCar 	= spriteSheet.grabImage(1,  "car");
		enemyTruck = spriteSheet.grabImage(1, "truck");
		
		frogDead = spriteSheet.grabImage(9, "frog");
		
		frogJumpingAnimation[0] = spriteSheet.grabImage(1, "frog");
		frogJumpingAnimation[1] = spriteSheet.grabImage(2, "frog");
		frogJumpingAnimation[2] = spriteSheet.grabImage(3, "frog");
		frogJumpingAnimation[3] = spriteSheet.grabImage(4, "frog");
		frogJumpingAnimation[4] = spriteSheet.grabImage(3, "frog");
		frogJumpingAnimation[5] = spriteSheet.grabImage(2, "frog");
		
		finish = spriteSheet.grabImage(10, "frog");
		
		heart = spriteSheet.grabImage("heart");
	}
}
