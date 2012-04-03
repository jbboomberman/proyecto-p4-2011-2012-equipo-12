package bomberman.managers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase estática que tiene como objetivo manejar toda la parte de sonido del
 * videojuego.
 * 
 * @author David
 * @version 1.0
 */
public class ManagerSonido {

	private static URL url = null;

	private static void playClip(String nom) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException,
			InterruptedException {
		class AudioListener implements LineListener {
			private boolean done = false;

			@Override
			public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					done = true;
					notifyAll();
				}
			}

			public synchronized void waitUntilDone()
					throws InterruptedException {
				while (!done) {
					wait();
				}
			}
		}
		url = ManagerSonido.class.getClassLoader().getResource(
				"bomberman/resources/" + nom);
		AudioListener listener = new AudioListener();
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(url);
		try {
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			try {
				// FloatControl gainControl = (FloatControl)
				// clip.getControl(FloatControl.Type.MASTER_GAIN);
				// gainControl.setValue(6.0f);
				clip.start();
				listener.waitUntilDone();
			} finally {
				clip.close();
			}
		} finally {
			audioInputStream.close();
		}
	}

	public static void main(String[] args) {
		try {
			ManagerSonido.playClip("levelintrosong.wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}