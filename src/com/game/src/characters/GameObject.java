package com.game.src.characters;

import java.awt.Rectangle;

public class GameObject {
	public double coordX, coordY;
	
	public GameObject(double coordX, double coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle( (int)coordX, (int)coordY, width, height);
	}
}
