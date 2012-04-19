package bomberman.managers;

import java.io.IOException;
import java.net.*;
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
	private static String nombre = null;
	private static boolean bucle;
	private static Clip clip;

	public static void playClip(String nom, boolean loop) throws IOException,
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
		bucle = loop;
		nombre = nom;
		new Thread(new Runnable() {
			public void run() {
				url = ManagerSonido.class.getClassLoader().getResource(
						"bomberman/resources/" + nombre);
				AudioListener listener = new AudioListener();
				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(url);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					clip = AudioSystem.getClip();
					clip.addLineListener(listener);
					clip.open(audioInputStream);
					try {
						// FloatControl gainControl = (FloatControl)
						// clip.getControl(FloatControl.Type.MASTER_GAIN);
						// gainControl.setValue(6.0f);
						if (bucle)
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						else
							clip.start();
						listener.waitUntilDone();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						clip.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						audioInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void pararLoop() {
		clip.stop();
	}

	public static void main(String[] args) {
		try {
			ManagerSonido.playClip("levelintrosong.wav", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
