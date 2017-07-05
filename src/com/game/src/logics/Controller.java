package com.game.src.logics;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.characters.BikeEnemy;
import com.game.src.characters.CarEnemy;
import com.game.src.characters.EnemyEntity;
import com.game.src.characters.FriendlyEntity;
import com.game.src.characters.Leaves;
import com.game.src.characters.TruckEnemy;
import com.game.src.constants.GameConstants;
import com.game.src.constants.GameConstants.LaneNumber;
import com.game.src.graphics.Textures;
import com.game.src.main.Game;

public class Controller {
	private LinkedList<FriendlyEntity> friendlyEntityArray = new LinkedList<FriendlyEntity>();
	private LinkedList<EnemyEntity> enemyEntityArray = new LinkedList<EnemyEntity>();
	
	FriendlyEntity friendlyEntity;
	EnemyEntity enemyEntity;
	
	Textures controllerTextures;
	
	private Game game;
	
	public Controller(Game game, Textures controllerTextures){
		this.game = game;
		this.controllerTextures = controllerTextures;
	}
	
	public void createEnemy(int numberOfEnemies, String enemyType, LaneNumber laneNumber){
		int spaceBetweenEnemies = 500;
		int delaySECONDbike = 200;
		int delaySECONDcar = 150;
		int delaySECONDtruck = 500;
		
		int startCoordX = GameConstants.getVehicleLaneCoordX();
		int[] startCoordY = GameConstants.getVehicleLaneCoordY();
		
		switch(enemyType){
			case "bike":
				for(int i = 0; i < numberOfEnemies; i++){
					if(laneNumber == LaneNumber.FIRST)
						addEntity(new BikeEnemy(startCoordX - spaceBetweenEnemies*i, startCoordY[0], controllerTextures, game, this));
					else if(laneNumber == LaneNumber.SECOND)
						addEntity(new BikeEnemy(startCoordX - spaceBetweenEnemies*i + delaySECONDbike, startCoordY[5], controllerTextures, game, this));
				}
				break;
			case "car":
				for(int i = 0; i < numberOfEnemies; i++){
					if(laneNumber == LaneNumber.FIRST)
						addEntity(new CarEnemy(startCoordX - spaceBetweenEnemies*i, startCoordY[1], controllerTextures, game, this));
					else if(laneNumber == LaneNumber.SECOND)
						addEntity(new CarEnemy(startCoordX - spaceBetweenEnemies*i + delaySECONDcar, startCoordY[4], controllerTextures, game, this));
				}
				break;
			case "truck":
				for(int i = 0; i < numberOfEnemies; i++){
					if(laneNumber == LaneNumber.FIRST)
						addEntity(new TruckEnemy(startCoordX - spaceBetweenEnemies*i, startCoordY[2], controllerTextures, game, this));
					else if(laneNumber == LaneNumber.SECOND)
						addEntity(new TruckEnemy(startCoordX - spaceBetweenEnemies*i+ delaySECONDtruck, startCoordY[3], controllerTextures, game, this));
				}
				break;
		}//have to use something like addEntity(new Enemy(r.nextInt(Game.WIDTH*Game.SCALE), -10, tex, game, this)); for each
	}
	
	public void createLeaves(){
		addEntity(new Leaves((double)GameConstants.getFinalCoordXleft(), (double)GameConstants.getFinalCoordY(), game));
		addEntity(new Leaves((double)GameConstants.getFinalCoordXmiddle(), (double)GameConstants.getFinalCoordY(), game));
		addEntity(new Leaves((double)GameConstants.getFinalCoordXright(), (double)GameConstants.getFinalCoordY(), game));
	}
	
	public void tick(){		
		for(int i = 0; i < friendlyEntityArray.size(); i++){
			friendlyEntity = friendlyEntityArray.get(i);
			friendlyEntity.tick();
		}
		for(int i = 0; i < enemyEntityArray.size(); i++){
			enemyEntity = enemyEntityArray.get(i);
			enemyEntity.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < friendlyEntityArray.size(); i++){
			friendlyEntity = friendlyEntityArray.get(i);
			
			friendlyEntity.render(g);
		}
		for(int i = 0; i < enemyEntityArray.size(); i++){
			enemyEntity = enemyEntityArray.get(i);
			enemyEntity.render(g);
		}
	}
	
	public void addEntity(FriendlyEntity entityObject){
		friendlyEntityArray.add(entityObject);
	}
	
	public void addEntity(EnemyEntity entityObject){
		enemyEntityArray.add(entityObject);
	}
	
	public void removeEntity(FriendlyEntity entityObject){
		friendlyEntityArray.remove(entityObject);
	}
	
	public void removeEntity(EnemyEntity entityObject){
		enemyEntityArray.remove(entityObject);
	}
	
	public LinkedList<FriendlyEntity> getFriendlyEntity(){
		return friendlyEntityArray;
	}
	
	public LinkedList<EnemyEntity> getEnemyEntity(){
		return enemyEntityArray;
	}
}
