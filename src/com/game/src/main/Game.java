package com.game.src.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.game.src.characters.Leaves;
import com.game.src.characters.Player;
import com.game.src.constants.GameConstants;
import com.game.src.constants.GameConstants.LaneNumber;
import com.game.src.constants.GameConstants.STATE;
import com.game.src.graphics.BufferedImageLoader;
import com.game.src.graphics.ExtraScreens;
import com.game.src.graphics.FrameStarter;
import com.game.src.graphics.GameOverScreen;
import com.game.src.graphics.GameScreen;
import com.game.src.graphics.Menu;
import com.game.src.graphics.Textures;
import com.game.src.graphics.WinScreen;
import com.game.src.userInteraction.KeyInput;
import com.game.src.userInteraction.MouseInput;

import com.game.src.logics.Controller;
import com.game.src.logics.GameHighscores;
import com.game.src.logics.Score;
import com.game.src.media.Sound;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean gameIsRunning = false;
	private Thread thread;
	
	//Start images buffer
	private BufferedImage gameImage = new BufferedImage(GameConstants.getScreenWidth(), GameConstants.getScreenHeight(), BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage menubackground = null;
	private BufferedImage introbackground = null; 
	private BufferedImage highscoresbackground = null;
	private BufferedImage winbackground = null;
	private BufferedImage gameoverbackground = null;
	private BufferedImage helpbackground = null;
	
	private static STATE state = STATE.INTRO;
	
	private Player frog;
	private Controller gameController;
	private Textures gameTextures;
	private Menu menu;
	
	private Score gameScore;
	
	private GameHighscores gameHighscores;
	
	public static Thread threadBGM = new Sound();		////// NEW
	
	private GameScreen gameScreen;
	private WinScreen winScreen;
	private GameOverScreen gameOverScreen;
	private ExtraScreens extraScreens;
	
	private boolean gameWon = false;
	private boolean endGame = false;
	
	private int gameTimer;
	private boolean gameTimerStarted = false;
	
	private static boolean firstTimeRunningGame = true;
	
	private StringBuilder nameInput;
	private int charPosNameInput;

	
	public void init() throws IOException{
		requestFocus();
		if(firstTimeRunningGame){
			BufferedImageLoader loader = new BufferedImageLoader();
			
			//Images of the game
			spriteSheet = loader.loadImage("/spriteSheet.png");
			background = loader.loadImage("/background.png");
			menubackground = loader.loadImage("/menubackground.png"); 
			introbackground = loader.loadImage("/introbackground.png"); 
			highscoresbackground = loader.loadImage("/highscoresbackground.png");
			helpbackground = loader.loadImage("/helpbackground.png");
			winbackground = loader.loadImage("/youwon.png");
			gameoverbackground = loader.loadImage("/gameover.png");
		}
		
		gameTextures = new Textures(this);
		
		gameController = new Controller(this, gameTextures);
		
		frog = new Player(GameConstants.getFrogInitialXCoord(), GameConstants.getFrogInitialYCoord(), gameTextures, this, gameController);
		
		menu = new Menu();
		
		gameScore = new Score(this);
		
		gameHighscores = new GameHighscores();
		
		gameScreen = new GameScreen(this);
		
		winScreen = new WinScreen(this);
		
		gameOverScreen = new GameOverScreen(this);
		
		extraScreens = new ExtraScreens(this);
		
		GameConstants.initVehicleLaneCoordY();
		
		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this, winScreen, gameOverScreen));
		
		//ENEMY CREATION
		gameController.createEnemy(3, "bike", LaneNumber.FIRST);
		gameController.createEnemy(2, "car", LaneNumber.FIRST);
		gameController.createEnemy(1, "truck", LaneNumber.FIRST);
		gameController.createEnemy(1, "truck", LaneNumber.SECOND);
		gameController.createEnemy(2, "car", LaneNumber.SECOND);
		gameController.createEnemy(3, "bike", LaneNumber.SECOND);
		
		//LEAVES
		gameController.createLeaves();
		
		nameInput = new StringBuilder("----------");
		charPosNameInput = 0;
	}
	
	public void start(){
		if(gameIsRunning)
			return;
		gameIsRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		if(!gameIsRunning)
			return;
		gameIsRunning = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Timer definitions
		final double amountOfTicks = 60.0;
		long 	lastTime = System.nanoTime();
		double 	ns = 1000000000 / amountOfTicks;
		double 	delta = 0;
		@SuppressWarnings("unused")
		int 	updates = 0;
		@SuppressWarnings("unused")
		int 	frames = 0;
		long 	timer = System.currentTimeMillis();
		
		//Game loop
		while(gameIsRunning){
			if(!gameTimerStarted && getState() == STATE.GAME){
				gameTimer = 0;
				gameTimerStarted = true;
			}
			//Timer construction to update each second
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1){
				if(!endGame){
					tick(gameTimer);
					updates++;
					delta--;
				}else{
					if(gameWon){
						Game.setState(STATE.WIN);
					}
					else{
						Game.setState(STATE.GAMEOVER);
					}
				}
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				//Reset updates and frames each second, and get Ticks/FPS control on terminal
				timer += 1000;
				//System.out.println(updates + "Ticks, FPS " + frames + " Frog lives: " + frog.getNumberOfLives() + " Score: " + gameScore.getScore() + " Leaves marked: " + frog.getNumberOfLeavesMarked());
				updates = 0;
				frames = 0;
				gameTimer++;
			}
		}
		stop();
		
	}
	
	public void tick(int timer){
		//Method to run each game tick
		if(state == STATE.GAME){
			frog.tick();
			gameController.tick();
			gameScore.setScore(gameScore.updateScoreInGame(timer, frog.getNumberOfLeavesMarked()));
			if(frog.getNumberOfLives() == 0 || gameWon){
				endGame = true;
				if(gameWon){
					gameScore.setScore(gameScore.getScore() + gameScore.scoreBonus(frog.getNumberOfLives()));
					state = STATE.WIN;
				}
				else
					state = STATE.GAMEOVER;
			}
		}
	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3); // Triple Buffering, increases performance
			return;
		}
		
		//All game rendering goes below here
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(gameImage, 0, 0, getWidth(), getHeight(), this);
		if (getState() == STATE.INTRO) {
			g.drawImage(introbackground, 0, 0, this);
		} else if (getState() == STATE.GAME) {
			// Game screen rendering
			g.drawImage(background, 0, 0, this);
			
			frog.render(g);
			gameController.render(g);
			gameScreen.displayLives(g, frog.getNumberOfLives());
			gameScreen.displayScoreGame(g, gameScore.getScore());
			gameScreen.displayTimer(g, gameTimer);
			// OTHER RENDERS(HEALTH BAR, ETC)
		} else if (getState() == STATE.MENU) {		
			// Menu screen rendering
			g.drawImage(menubackground, 0, 0, this);
			menu.render(g);
		} else if (getState() == STATE.HIGHSCORE) {		
			// Menu screen rendering
			g.drawImage(highscoresbackground, 0, 0, this);
			extraScreens.drawHighscoresButton(g);
		}else if(getState() == STATE.HELP){
			g.drawImage(helpbackground, 0, 0, this);
			extraScreens.drawHelpButton(g);
		}else if(getState() == STATE.WIN){
			g.drawImage(winbackground, 0, 0, this);
			gameScreen.displayScoreFinal(g, gameScore.getScore());
			WinScreen.drawButtons(g);
			g.drawString(nameInput.substring(0), 550, 570);
		}else if(getState() == STATE.GAMEOVER){
			g.drawImage(gameoverbackground, 0, 0, this);
			GameOverScreen.drawButtons(g);
		}
		
		//All game rendering goes above here
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent event){
		int key = event.getKeyCode();
		
		if(getState() == STATE.INTRO){
			if (key == KeyEvent.VK_ENTER){
				Game.setState(STATE.MENU);
			}
		}else if(getState() == STATE.GAME && frog.getIsAlive()){
			if (key == KeyEvent.VK_RIGHT) {
				if (frog.getCoordY() != GameConstants.getFinalCoordY())
					frog.setSpeedX(GameConstants.getFrogSpeedX());
			} else if (key == KeyEvent.VK_LEFT) {
				if (frog.getCoordY() != GameConstants.getFinalCoordY())
					frog.setSpeedX(-GameConstants.getFrogSpeedX());
			} else if(key == KeyEvent.VK_DOWN && !frog.getIsChangingLane()){
				if(frog.getCoordY() != GameConstants.getFrogInitialYCoord()){
					frog.setIsChangingLane(true);
					frog.setCoordY(frog.getCoordY() + Player.getSpeedY()); 
				}else{
					frog.setCoordY(GameConstants.getFrogInitialYCoord());
				}
			} else if(key == KeyEvent.VK_UP && !frog.getIsChangingLane()){
				if(frog.getCoordY() != GameConstants.getFinalCoordY()){
					if(frog.getCoordY() == GameConstants.getFinalVehicleLaneCoordY()){
						if(frog.checkIfNextIsLeave()){
							frog.setIsChangingLane(true);
							frog.setCoordY(frog.getCoordY() - Player.getSpeedY());
						}
					}else{
						frog.setIsChangingLane(true);
						frog.setCoordY(frog.getCoordY() - Player.getSpeedY());						
					}
				}
			}	
		}else if(getState() == STATE.WIN){
			char charPressed = event.getKeyChar();
			System.out.println(charPressed);
			if(charPosNameInput < nameInput.length()){
				if(charPosNameInput > 0 && key == KeyEvent.VK_BACK_SPACE){
					nameInput.replace(charPosNameInput, charPosNameInput, "-");
					charPosNameInput--;
				}else{
					nameInput.setCharAt(charPosNameInput, charPressed);
					charPosNameInput++;
				}
			}else{
				if(key == KeyEvent.VK_BACK_SPACE){
					nameInput.replace(charPosNameInput, charPosNameInput, "-");
					charPosNameInput--;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent event){
		int key = event.getKeyCode();
		
		if(state == STATE.MENU){
			
		}else if(getState() == STATE.GAME && frog.getIsAlive()){
			if (key == KeyEvent.VK_RIGHT){
				frog.setSpeedX(0);
			} else if(key == KeyEvent.VK_LEFT){
				frog.setSpeedX(0);
			} else if(key == KeyEvent.VK_DOWN){
				frog.setIsChangingLane(false);
			} else if(key == KeyEvent.VK_UP){
				frog.setIsChangingLane(false);
			}
		}
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
		
	}
	
	public static void main(String args[]){
		Sound.setSound(GameConstants.getGameBGM());
		threadBGM.start();	//Start Sound Thread
		
		newGame(firstTimeRunningGame);
	}
	
	public static void newGame(boolean firstTimeRunningGame){
		Game game = new Game();
		
		if(firstTimeRunningGame){
			FrameStarter.initFrame(game);
			firstTimeRunningGame = false;
		}
		
		game.start();
	}
	
	public void resetVariables(){
		gameImage = new BufferedImage(GameConstants.getScreenWidth(), GameConstants.getScreenHeight(), BufferedImage.TYPE_INT_RGB);
		spriteSheet = null;
		background = null;
		menubackground = null;
		introbackground = null; 
		highscoresbackground = null;
		winbackground = null;
		gameoverbackground = null;
		gameWon = false;
		endGame = false;
		gameTimerStarted = false;
		Leaves.setFrogOnLeaveLeft(false);
		Leaves.setFrogOnLeaveMiddle(false);
		Leaves.setFrogOnLeaveRight(false);
	}
	
	public void setGameState(STATE state){
		Game.setState(state);
	}
	
	public void setGameWon(boolean gameWon){
		this.gameWon = gameWon;
	}
	
	public void setEndGame(boolean endGame){
		this.endGame = endGame;
	}

	public static STATE getState() {
		return state;
	}

	public static void setState(STATE state) {
		Game.state = state;
	}
	
	public static boolean getFirstTimeRunningGame(){
		return firstTimeRunningGame;
	}

	public Score getGameScore() {
		return gameScore;
	}

	public GameHighscores getGameHighscores() {
		return gameHighscores;
	}
	
	public String getNameInput(){
		return nameInput.substring(0, charPosNameInput);
	}
	

}
