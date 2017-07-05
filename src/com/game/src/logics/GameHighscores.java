package com.game.src.logics;

import java.io.BufferedOutputStream;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import javax.swing.filechooser.FileSystemView;


public class GameHighscores implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int scoreMaxSize = 10;
	private LinkedHashMap<String, Integer> highscores = new LinkedHashMap<String, Integer>();
	private String highscoresFilePath = "saves/gameHighscores.ser";
	
	/*Get Documents folder path
	private String PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
	private String directoryName = PATH.concat("/saves");
	private String fileName = "/gameHighscores.ser";
	
	private String filePath = directoryName.concat(fileName);
	
	File highscoresFile = new File(filePath);
	
	public GameHighscores() throws IOException{
		if(!highscoresFile.exists())
			highscoresFile.createNewFile();
	}
	
	public void checkIfFolderExistsElseCreateFolder(){
		File directory = new File(directoryName);
		if(!directory.exists())
			directory.mkdir();
	}
*/
	// adds a <playerName, playerScore> map entry to the highscores if
	// applicable
	// then leaves it reverse-sorted and saved in the .ser file
	public void addHighscore(String playerName, int playerScore) {
		this.loadHighscores();

		if (!isHighscoreFull()) {
			this.highscores.put(playerName, playerScore);
		} else if (isScoreHighscore(playerScore)) {
			this.sortHighscores();
			this.highscores.remove(this.getFirstEntry().getKey());
			this.highscores.put(playerName, playerScore);
		}
		this.sortReverseHighscores();
		this.saveHighscores();
	}

	// determines whether the score is good enough to be a highscore
	public boolean isScoreHighscore(int playerScore) {
		this.sortHighscores();
		return (this.getFirstEntry().getValue() < playerScore);

	}

	// used for Highscore capacity control
	public boolean isHighscoreFull() {
		return (this.highscores.size() >= this.scoreMaxSize);
	}

	// gets the first entry in the linkedhashmap highscores
	public Entry<String, Integer> getFirstEntry() {
		return this.highscores.entrySet().iterator().next();
	}

	/*
	 * private Entry<String, Integer> getLastEntry() { Entry<String, Integer>
	 * lastEntry = null; while (this.highscores.entrySet().iterator().hasNext())
	 * { lastEntry = this.highscores.entrySet().iterator().next(); } return
	 * lastEntry; }
	 */

	// sorts the highscores map by value in order
	public void sortHighscores() {
		Map<String, Integer> sortedHighscores = new LinkedHashMap<>();
		this.highscores.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue())
				.forEachOrdered(x -> sortedHighscores.put(x.getKey(), x.getValue()));
		this.highscores = (LinkedHashMap<String, Integer>) sortedHighscores;
	}

	// sorts the highscores map by value in reverse order
	public void sortReverseHighscores() {
		Map<String, Integer> sortedHighscores = new LinkedHashMap<>();
		this.highscores.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.forEachOrdered(x -> sortedHighscores.put(x.getKey(), x.getValue()));
		this.highscores = (LinkedHashMap<String, Integer>) sortedHighscores;
	}

	// loads from the .ser file the linkedhashmap to the highscores variable
	@SuppressWarnings("unchecked")
	public void loadHighscores() {
		try {
			FileInputStream fileIn = new FileInputStream(this.highscoresFilePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			this.highscores = (LinkedHashMap<String, Integer>) in.readObject();

			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException comparator) {
			System.out.println("Class not found!");
			comparator.printStackTrace();
			return;
		}
	}

	// saves the current highscores HashMap to a .ser file
	public void saveHighscores() {
		try {
			FileOutputStream fileOut = new FileOutputStream(this.highscoresFilePath);
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));

			out.writeObject(this.highscores);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in " + this.highscoresFilePath + ".");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	// erases highscore variable
	public void clearHighscores() {
		this.highscores.clear();
	}

	public void eraseHighscores() {
		this.clearHighscores();
		this.saveHighscores();
	}

	public List<String> getNamesList(){
		this.loadHighscores();
		List<String> namesList = new ArrayList<String>(this.highscores.keySet());		
		return namesList;
	}	
	
	public List<Integer> getScoresList(){
		this.loadHighscores();
		List<Integer> scoresList = new ArrayList<Integer>(this.highscores.values());		
		return scoresList;
	}
	
	public LinkedHashMap<String, Integer> getHighscores() {
		return highscores;
	}
	
	public static void main(String[] args) throws IOException{
		GameHighscores myHighscores = new GameHighscores();
		myHighscores.addHighscore("derp", 100);
		myHighscores.addHighscore("lurp", 200);
		System.out.println(myHighscores.highscores);
		System.out.println(myHighscores.getNamesList());
		System.out.println(myHighscores.getScoresList());
	}
}
