package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.graphics.Textures;
import com.game.src.logics.Controller;
import com.game.src.main.Game;

public abstract class Enemy extends GameObject implements EnemyEntity{
	@SuppressWarnings("unused")
	private Textures enemyTextures;
	
	@SuppressWarnings("unused")
	private Game game;
	@SuppressWarnings("unused")
	private Controller gameController;
	
	@SuppressWarnings("unused")
	private static int speed;
	
	public Enemy(double coordX, double coordY, Textures enemyTextures, Game game, Controller gameController){
		super(coordX, coordY);
		this.game = game;
		this.gameController = gameController;
		this.enemyTextures = enemyTextures;
		
		
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordX(double coordX){
		this.coordX = coordX;
	}
	
	
}
