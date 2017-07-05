package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface FriendlyEntity {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	public double getCoordX();
	public double getCoordY();
}
