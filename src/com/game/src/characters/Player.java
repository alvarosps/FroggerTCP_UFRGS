package com.game.src.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.src.constants.GameConstants;
import com.game.src.graphics.Textures;
import com.game.src.libs.Animation;
import com.game.src.logics.Controller;
import com.game.src.logics.Physics;
import com.game.src.main.Game;
import com.game.src.media.Sound;

public class Player extends GameObject implements FriendlyEntity{

	private int speedX = 0;	
	
	private static final int speedY = GameConstants.getSpriteSheetSize(); 
	
	@SuppressWarnings("unused")
	private Textures playerTextures;
	@SuppressWarnings("unused")
	private Game game;
	private Controller gameController;
	private Animation playerAnimation;
	
	private final static int speedOfAnimation = 2;
	private boolean isChangingLane = false;
	
	private boolean isAlive = true;
	private int numberOfLives = 3;
	
	private boolean justCollided = false;
	
	private int numberOfLeavesMarked;
	private boolean leftLeaveChecked = false;
	private boolean middleLeaveChecked = false;
	private boolean rightLeaveChecked = false;
	
	public Player(double coordX, double coordY, Textures playerTextures, Game game, Controller gameController){
		super(coordX, coordY);
		
		this.playerTextures = playerTextures;
		this.game = game;
		this.gameController = gameController;
		
		numberOfLeavesMarked = 0;
		//SpriteSheet spriteSheet = new SpriteSheet(game.getSpriteSheet());
		
		playerAnimation = new Animation(speedOfAnimation, Textures.frogJumpingAnimation[0], Textures.frogJumpingAnimation[1], Textures.frogJumpingAnimation[2], Textures.frogJumpingAnimation[3], Textures.frogJumpingAnimation[4], Textures.frogJumpingAnimation[5]); 
		//player = spriteSheet.grabImage(1, "frog");
	}
	
	public boolean checkIfNextIsLeave(){
		int leftCoordX = GameConstants.getFinalCoordXleft();
		int middleCoordX = GameConstants.getFinalCoordXmiddle();
		int rightCoordX = GameConstants.getFinalCoordXright();
		
		if((int)coordX  + 10 >= leftCoordX && (int)coordX - 10 <= leftCoordX )
			return true;
		else if((int)coordX  + 10 >= middleCoordX && (int)coordX - 10 <= middleCoordX )
			return true;
		else if((int)coordX  + 10 >= rightCoordX && (int)coordX - 10 <= rightCoordX )
			return true;
		
		return false;
	}
	
	public void checkNumberOfLeavesMarked(){
		if(Leaves.getFrogOnLeaveLeft() && !leftLeaveChecked){
			numberOfLeavesMarked += 1;
			leftLeaveChecked = true;
		}
		if(Leaves.getFrogOnLeaveMiddle() && !middleLeaveChecked){
			numberOfLeavesMarked += 1;
			middleLeaveChecked = true;
		}
		if(Leaves.getFrogOnLeaveRight() && !rightLeaveChecked){
			numberOfLeavesMarked += 1;
			rightLeaveChecked = true;
		}
	}
	
	public void tick(){
		coordX += speedX;
		
		checkNumberOfLeavesMarked();
		
		int spriteSize = GameConstants.getSpriteSheetSize();
		int screenHeight = GameConstants.getScreenHeight();
		int screenWidth = GameConstants.getScreenWidth();
		
		if(coordY < spriteSize)
			coordY = spriteSize;
		if(coordY > screenHeight - spriteSize)
			coordY = screenHeight - spriteSize;
		
		/*
		 * Allows the frog to cross the screen's horizontal borders
		 */
		if(coordX < -spriteSize)
			coordX = screenWidth - Math.abs(coordX) % screenWidth;
		if(coordX > screenWidth - spriteSize)
			coordX = Math.abs(coordX) % screenWidth;
		
		
		//COLLISIONS HERE
		//Enemy collision
		boolean soundPlayed = false;
		for(int i = 0; i < gameController.getEnemyEntity().size(); i++){
			EnemyEntity tempEnemyEntity = gameController.getEnemyEntity().get(i);
			if(Physics.Collision(this, tempEnemyEntity)){
				isAlive = false;
				justCollided = true;
				isChangingLane = false;
				if(!soundPlayed){
					Game.threadBGM = new Sound();					
					Sound.setSound(GameConstants.getCarHorn());		
					Game.threadBGM.start();
					soundPlayed = true;
				}
				break;
			}
				
		}
		//Leaves collision
		for(int i = 0; i < gameController.getFriendlyEntity().size(); i++){
			FriendlyEntity tempFriendlyEntity = gameController.getFriendlyEntity().get(i);
			if(Physics.Collision(this, tempFriendlyEntity)){
				if(tempFriendlyEntity.getCoordX() == GameConstants.getFinalCoordXleft() && !leftLeaveChecked){
					Leaves.setFrogOnLeaveLeft(true);
					Game.threadBGM = new Sound(); 
					Sound.setSound(GameConstants.getLeaveFX());	
					Game.threadBGM.start();	
				}
				else if(tempFriendlyEntity.getCoordX() == GameConstants.getFinalCoordXmiddle() && !middleLeaveChecked){
					Leaves.setFrogOnLeaveMiddle(true);
					Game.threadBGM = new Sound();
					Sound.setSound(GameConstants.getLeaveFX());	
					Game.threadBGM.start();
				}
				else if(tempFriendlyEntity.getCoordX() == GameConstants.getFinalCoordXright() && !rightLeaveChecked){
					Leaves.setFrogOnLeaveRight(true);
					Game.threadBGM = new Sound();			
					Sound.setSound(GameConstants.getLeaveFX());	
					Game.threadBGM.start();			
				}
				coordX = GameConstants.getFrogInitialXCoord();
				coordY = GameConstants.getFrogInitialYCoord();
			}
		}
		
		
		//this is just to make the reset in the next loop, allowing the player to see the spritesheet of frogDead for a second
		if(!justCollided){
			if(!isAlive){
				numberOfLives -= 1;
				
									
				
				if(numberOfLives != 0){
					coordX = GameConstants.getFrogInitialXCoord();
					coordY = GameConstants.getFrogInitialYCoord();
					isAlive = true;
				}
			}
		}else
			justCollided = false;
		
		
		//Animation
		if(isChangingLane)
			playerAnimation.runAnimation();
		
	}
	
	public void render(Graphics g){
		if(isAlive && numberOfLives != 0){
			if(!isChangingLane)
				g.drawImage(Textures.playerFrog, (int)coordX, (int)coordY, null);
			else
				playerAnimation.drawAnimation(g, (int)coordX, (int)coordY, 0);
		}
		else
			g.drawImage(Textures.frogDead, (int)coordX, (int)coordY, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle( (int)coordX, (int)coordY, GameConstants.getSpriteSheetSize(), GameConstants.getSpriteSheetSize());
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}
	
	public void setCoordY(double coordY){
		this.coordY = coordY;
	}
	
	public boolean getIsChangingLane(){
		return isChangingLane;
	}
	
	public void setIsChangingLane(boolean isChangingLane){
		this.isChangingLane = isChangingLane;
	}
	
	public Animation getPlayerAnimation(){
		return playerAnimation;
	}
	
	public int getSpeedX(){
		return speedX;
	}
	public void setSpeedX(int speedX){
		this.speedX = speedX;
	}
	
	public static int getSpeedY(){
		return speedY;
	}
	
	public int getNumberOfLives(){
		return numberOfLives;
	}
	
	public void setNumberOfLives(int numberOfLives){
		this.numberOfLives = numberOfLives;
	}
	
	public boolean getIsAlive(){
		return isAlive;
	}
	
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	public int getNumberOfLeavesMarked(){
		return numberOfLeavesMarked;
	}
	
}
