package com.game.src.media;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Thread{
	
	public static Mixer mixer;
	public static Clip clip;
	private static final int audioMainDevice = 0;
	private static String sound;
	
	private static void playSound(String soundName){
		
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();		
		mixer = AudioSystem.getMixer(mixInfos[audioMainDevice]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{ clip = (Clip)mixer.getLine(dataInfo); }
		catch(LineUnavailableException lue) { lue.printStackTrace(); }
		
		try {
			URL soundURL = Sound.class.getResource(soundName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
			clip.open(audioStream);
		}
		catch(LineUnavailableException lue) { lue.printStackTrace(); }
		catch(UnsupportedAudioFileException uafe) { uafe.printStackTrace(); }
		catch(IOException ioe) { ioe.printStackTrace(); }
		
		clip.start();
		
		do{
			try { Thread.sleep(10); } // Wait a few milliseconds before checking so the audio actually plays
			catch(InterruptedException ie) { 
				ie.printStackTrace(); 
				clip.stop();
			}
		} while (clip.isActive());
	}  
	
	public void run(){
		playSound(sound);
	}
	
	public static void setSound(String sound){
		Sound.sound = sound;
	}
	
}