package com.game.src.logics;

import com.game.src.characters.FriendlyEntity;
import com.game.src.characters.EnemyEntity;

public class Physics {
	public static boolean Collision(FriendlyEntity friendlyEntityArray, EnemyEntity enemyEntityArray){
	
		if( friendlyEntityArray.getBounds().intersects(enemyEntityArray.getBounds()) ){
			return true;
		}
		
		return false;
	}
	
	public static boolean Collision(EnemyEntity enemyEntityArray, FriendlyEntity friendlyEntityArray){
		
		if( enemyEntityArray.getBounds().intersects(friendlyEntityArray.getBounds()) ){
			return true;
	
		}
		
		return false;
	}
	
	public static boolean Collision(FriendlyEntity friendlyEntityArray, FriendlyEntity friendlyEntity2Array){
		if(friendlyEntityArray.getBounds().intersects(friendlyEntity2Array.getBounds())){
			return true;
		}
		return false;
	}
}
