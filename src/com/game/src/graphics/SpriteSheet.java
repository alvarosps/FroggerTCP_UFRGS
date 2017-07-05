package com.game.src.graphics;

import java.awt.image.BufferedImage;

import com.game.src.constants.GameConstants;

public class SpriteSheet {
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col*width) - width, (row*height) - height, width, height);		
		return img;
	}
	public BufferedImage grabImage(int row, String character){
		int height = GameConstants.getSpriteSheetSize();
		int width = height;
		int col = 0;
		
		row = row*80 - 80;
		
		switch(character){
			case "frog":
				col = 0;
				break;
			case "bike":
				width *= 2;
				col = 80;
				break;
			case "car":
				width *= 2;
				col = 240;
				break;
			case "truck":
				width *= 4;
				col = 400;
				break;
			default:
				System.out.println("Erro na definicao de largura e coluna da imagem " + character);
				break;
		}
		BufferedImage img = image.getSubimage(col, row, width, height);		
		return img;
	}
	
	public BufferedImage grabImage(String type){
		int row = 0;
		int col = 0;
		int width = GameConstants.getSpriteSheetSize();
		int height = width;
		switch(type){
			case "heart":
				row = 4*GameConstants.getSpriteSheetSize() - GameConstants.getSpriteSheetSize();
				col = 80;
				break;
			default:
				System.out.println("Erro na abertura do sprite sheet de " + type);
				break;
		}
		BufferedImage img = image.getSubimage(col, row, width, height);
		return img;
	}
}
