package bomberman.managers;

import java.io.IOException;
import java.net.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase que tiene como objetivo manejar toda la parte de sonido del videojuego.
 * 
 * @author David
 * @version 1.0
 */
public class ManagerSonido {

	// URL de la canción
	private static URL url = null;
	// Nombre de la canción
	private static String nombre = null;
	// Si queremos con bucle o no.
	private static boolean bucle;
	private static Clip clip;

	/**
	 * Método que se encarga de hacer sonar una canción. Recibe como parámetro
	 * el nombre de la canción y si queremos tocarla en bucle o no.
	 * 
	 * @param nom
	 *            - String
	 * @param loop
	 *            - boolean
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	public static void playClip(String nom, boolean loop) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException,
			InterruptedException {

		/**
		 * Esta clase se implementa para evitar errores de que el sonido se pare
		 * cuando le de la gana.
		 * 
		 * @author David
		 * @version 1.0
		 */
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
		/*
		 * Lo ejecutamos en un hilo aparte para que no interfiera con los
		 * gráficos del juego.
		 */
		new Thread(new Runnable() {
			public void run() {
				// Cargamos la canción
				url = ManagerSonido.class.getClassLoader().getResource(
						"bomberman/resources/" + nombre);
				AudioListener listener = new AudioListener();
				// Un stream de audio
				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(url);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					// Obtenemos el clip
					clip = AudioSystem.getClip();
					clip.addLineListener(listener);
					// Hacemos sonar el audio
					clip.open(audioInputStream);
					try {
						// FloatControl gainControl = (FloatControl)
						// clip.getControl(FloatControl.Type.MASTER_GAIN);
						// gainControl.setValue(6.0f);
						// Si se quiere hacer sonar en bucle
						if (bucle)
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						else
							clip.start();
						// Hacer sonar hasta que acabe
						listener.waitUntilDone();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						// Cerramos el clip
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

	/**
	 * Este método estático nos permite parar un loop cuando queramos.
	 */
	public static void pararLoop() {
		if(clip != null)
			clip.stop();
	}
}
