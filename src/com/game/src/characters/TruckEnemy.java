package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.src.constants.GameConstants;
import com.game.src.graphics.Textures;
import com.game.src.logics.Controller;
import com.game.src.main.Game;

public class TruckEnemy extends Enemy{
	private static final int truckSpeed = 4;
	
	@SuppressWarnings("unused")
	private Game game;
	@SuppressWarnings("unused")
	private Controller gameController;
	
	public TruckEnemy(double coordX, double coordY, Textures enemyTextures, Game game, Controller gameController){
		super(coordX, coordY, enemyTextures, game, gameController);
	}

	public void tick() {
		int spriteSize = 4 * GameConstants.getSpriteSheetSize();
		int screenWidth = GameConstants.getScreenWidth();
		
		coordX -= truckSpeed;
		if(coordX < -spriteSize){
			coordX = screenWidth;
			if(coordY == GameConstants.getVehicleLaneCoordY()[2])
				coordY = GameConstants.getVehicleLaneCoordY()[3];
			else if(coordY == GameConstants.getVehicleLaneCoordY()[3])
				coordY = GameConstants.getVehicleLaneCoordY()[2];
		}
	}

	public void render(Graphics g) {
		g.drawImage(Textures.enemyTruck, (int)coordX, (int)coordY, null);
	}

	public Rectangle getBounds() {
		return new Rectangle( (int)coordX, (int)coordY,  GameConstants.getSpriteSheetSize() * 4, GameConstants.getSpriteSheetSize());
	}
	
	public int getTruckSpeed(){
		return truckSpeed;
	}
}
