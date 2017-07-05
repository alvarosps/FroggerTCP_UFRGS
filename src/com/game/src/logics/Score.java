package com.game.src.logics;

import com.game.src.main.Game;

public class Score {
	Game game;
	
	private int score;
	
	public Score(Game game){
		this.game = game;
		this.score = 0;
	}
	
	public int updateScoreInGame(int timer, int numberOfLeavesMarked){
		int scoreInGame = 10*(100 - timer) + 500*numberOfLeavesMarked;
		if(scoreInGame < 0)
			scoreInGame = 0;
		return scoreInGame;
	}
	
	public int scoreBonus(int numberOfLives){
		int scoreBonus = 1000*numberOfLives;
		return scoreBonus;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
}
