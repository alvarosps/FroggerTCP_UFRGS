package com.game.src.constants;

public class GameConstants {
	//Screen Resolution 1280x720
	
	private static final int vehicleLanes = 6;
	private static final int endLane = 1;
	private static final int startLane = 1;
	private static final int dataLane = 1;
	
	private static final int numberOfLanes = startLane + vehicleLanes + endLane + dataLane;
	
	private static final int spriteSheetSize = 80;
	private static final int screenHeight = numberOfLanes * spriteSheetSize;
	private static final int screenWidth = screenHeight / 9 * 16;
	private static final String gameTitle = "Frogger";
	
	private int frogLives = 3;
	
	private static final int frogInitialXCoord = screenWidth/2 - spriteSheetSize/2;
	private static final int frogInitialYCoord = screenHeight - spriteSheetSize/2;
	
	private static final int frogSpeedX = 5;
	
	private static final int vehicleLaneCoordX = screenWidth;
	
	private static int vehicleLaneCoordY[] = new int[6];
	
	private static final int finalCoordXmiddle = frogInitialXCoord - 10;
	private static final int finalCoordXleft = finalCoordXmiddle / 3;
	private static final int finalCoordXright = finalCoordXmiddle + 2 * finalCoordXmiddle / 3;
	private static final int finalCoordY = screenHeight - (spriteSheetSize * 8);
	
	private static final int finalVehicleLaneCoordY = screenHeight - (spriteSheetSize * 7);
	
	private static final int coordXtimer = 1180;
	private static final int coordXscore = 380;
	private static final int coordXlives = 10;
	private static final int coordYlives = 0;
	private static final int coordYstrings = 60;
	private static final int spaceBetweenLives = 60;
	private static final int gameScreenFontSize = 50;
	
	private static final int coordXwinScore = 550;
	private static final int coordYwinScore = 500;
	private static final int winScreenFontSize = 70;
	
	private static final String gameBGM = "/sounds/GameTheme.wav";			////// NEW
	private static final String leaveFX = "/sounds/MissionComplete.wav"; 	////// NEW
	private static final String carHorn = "/sounds/CarHorn.wav";			////// NEW

	
	public static String getGameBGM(){	////// NEW
		return gameBGM;
	}
	
	public static String getLeaveFX(){	////// NEW
		return leaveFX;
	}
	
	public static String getCarHorn(){	////// NEW
		return carHorn;
	}
	
	public static int getCoordXwinScore() {
		return coordXwinScore;
	}

	public static int getCoordYwinScore() {
		return coordYwinScore;
	}

	public static int getWinScreenFontSize() {
		return winScreenFontSize;
	}

	public static int getGameScreenFontSize(){
		return gameScreenFontSize;
	}
	
	public static int getSpaceBetweenLives(){
		return spaceBetweenLives;
	}
	
	public static int getCoordXtimer() {
		return coordXtimer;
	}

	public static int getCoordXscore() {
		return coordXscore;
	}

	public static int getCoordXlives() {
		return coordXlives;
	}

	public static int getCoordYlives() {
		return coordYlives;
	}

	public static int getCoordYstrings() {
		return coordYstrings;
	}

	public static int getFinalCoordXmiddle() {
		return finalCoordXmiddle;
	}

	public static int getFinalCoordXleft() {
		return finalCoordXleft;
	}

	public static int getFinalCoordXright() {
		return finalCoordXright;
	}

	public static int getFinalCoordY() {
		return finalCoordY;
	}
	
	public static int getVehicleLaneCoordX(){
		return vehicleLaneCoordX;
	}
	
	public static int getFinalVehicleLaneCoordY(){
		return finalVehicleLaneCoordY;
	}
	
	public static void initVehicleLaneCoordY(){
		for (int i = 0; i < 6; i++)
				vehicleLaneCoordY[i] = screenHeight - (spriteSheetSize * (i+2));
	}
	
	public static int[] getVehicleLaneCoordY(){
		return vehicleLaneCoordY;
	}
	
	public static int getFrogSpeedX(){
		return frogSpeedX;
	}
	
	public static int getSpriteSheetSize() {
		return spriteSheetSize;
	}

	public static int getFrogInitialXCoord() {
		return frogInitialXCoord;
	}

	public static int getFrogInitialYCoord() {
		return frogInitialYCoord;
	}

	public static enum STATE{
		MENU,
		GAME,
		HIGHSCORE,
		HELP,
		INTRO,
		GAMEOVER,
		WIN
	}

	public static enum LaneNumber{
		FIRST,
		SECOND
	}

	public int getFrogLives() {
		return frogLives;
	}

	public void setFrogLives(int frogLives) {
		this.frogLives = frogLives;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static String getGameTitle() {
		return gameTitle;
	}
	
}
