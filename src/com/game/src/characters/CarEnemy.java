package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.src.constants.GameConstants;
import com.game.src.graphics.Textures;
import com.game.src.logics.Controller;
import com.game.src.main.Game;

public class CarEnemy extends Enemy{
	private static final int carSpeed = 7;
	
	@SuppressWarnings("unused")
	private Textures enemyTextures;
	
	@SuppressWarnings("unused")
	private Game game;
	@SuppressWarnings("unused")
	private Controller gameController;
	
	public CarEnemy(double coordX, double coordY, Textures enemyTextures, Game game, Controller gameController){
		super(coordX, coordY, enemyTextures, game, gameController);
	}

	public void tick() {
		int spriteSize = 2 * GameConstants.getSpriteSheetSize();
		int screenWidth = GameConstants.getScreenWidth();
		
		coordX -= carSpeed;
		if(coordX < -spriteSize)
			coordX = screenWidth;
	}

	public void render(Graphics g) {
		g.drawImage(Textures.enemyCar, (int)coordX, (int)coordY, null);
	}

	public Rectangle getBounds() {
		return new Rectangle( (int)coordX, (int)coordY,  GameConstants.getSpriteSheetSize() * 2, GameConstants.getSpriteSheetSize());
	}
	
	public int getCarSpeed(){
		return carSpeed;
	}
}
