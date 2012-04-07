package bomberman.protagonistas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.ventanas.VentanaJuego;

public class Bomba extends SpriteEstatico {

	private Timer temporizador;
	private boolean pisada;
	private Bomberman bomberman;

	public Bomba(Escenario esce, float x, float y, Bomberman bomber, Jugador jug) {
		super(esce, jug);
		spritesImag = new String[] { "bombs.gif_1", "bombs.gif_2",
				"bombs.gif_3" };
		this.posX = x;
		this.posY = y;
		// ¿Se puede automatizar?
		this.altura = 33;
		this.anchura = 33;
		pisada = true;
		this.bomberman = bomber;
		// ////
		// temporizador = new Timer();
		// temporizador.schedule(new LoadExplode(), 3000);
	}
	
	public void paint(Graphics2D g){
		super.paint(g);
		if(!this.colision(bomberman) && pisada)
			pisada = false;
	}
	
	public boolean isPisada() {
		return pisada;
	}

	public void setPisada(boolean pisada) {
		this.pisada = pisada;
	}

	public void explotar() {
		// temporizador.cancel();
		int[] maxLlama = calcularDistancias();
		// escenario.añadirSprite(new Llama(escenario, maxLlama, this.getPosX(),
		// this.getPosY()));
	}

	class LoadExplode extends TimerTask {

		public void run() {
			explotar();
		}
	}

	private int[] calcularDistancias() {

		int[] tempArray = new int[4];

		/*
		 * Creamos un objeto de la clase Rectangle que nos servirá para
		 * comprobar si hay algún muro o enemigo en el camino que tendrá que
		 * tomar la llama.
		 */
		Rectangle tempRect = new Rectangle(new Dimension(33, 33));

		for (int i = 0; i < tempArray.length; i++) {
			tempRect.x = (int) this.posX;
			tempRect.y = (int) this.posY;

			// Comprobamos la distancia que hay arriba
			if (i == 0) {
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {

					/*
					 * Vamos añadiendo posiciones hasta que lo encontremos.
					 */
					// AL REVÉS!!!!!!!!!!!11
					tempRect.y -= this.getAltura();
					/*
					 * Comprobamos con los Sprites que hay en la ventana a ver
					 * si alguno se interpone en nuestro camino.
					 */
					for (Sprite sprTemp : escenario.getLista()) {
						Rectangle tempRect2 = new Rectangle(
								(int) sprTemp.getPosX(),
								(int) sprTemp.getPosY(),
								(int) sprTemp.getAnchura(),
								(int) sprTemp.getAltura());
						// Si alguno se interpone
						if (tempRect.intersects(tempRect2)) {
							encon = true;
							// Ya sabemos el tope.
							tempArray[i] = (int) (this.getPosY() - tempRect2.y) / 33;
							break;
						}
					}
				}
				// Comprobamos la distancia que hay abajo
			} else if (i == 1) {
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.y += this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						Rectangle tempRect2 = new Rectangle(
								(int) sprTemp.getPosX(),
								(int) sprTemp.getPosY(),
								(int) sprTemp.getAnchura(),
								(int) sprTemp.getAltura());
						if (tempRect.intersects(tempRect2)) {
							encon = true;
							tempArray[i] = (int) (tempRect2.y - this.getPosY()) / 33;
							break;
						}
					}
				}
				// Comprobamos la distancia que hay a la derecha
			} else if (i == 2) {
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.x += this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						Rectangle tempRect2 = new Rectangle(
								(int) sprTemp.getPosX(),
								(int) sprTemp.getPosY(),
								(int) sprTemp.getAnchura(),
								(int) sprTemp.getAltura());
						if (tempRect.intersects(tempRect2)) {
							encon = true;
							tempArray[i] = (int) (tempRect2.x - this.getPosX()) / 33;
							break;
						}
					}
				}
				// Comprobamos la distancia que hay a la izquierda
			} else {
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.x -= this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						Rectangle tempRect2 = new Rectangle(
								(int) sprTemp.getPosX(),
								(int) sprTemp.getPosY(),
								(int) sprTemp.getAnchura(),
								(int) sprTemp.getAltura());
						if (tempRect.intersects(tempRect2)) {
							encon = true;
							tempArray[i] = (int) (this.getPosX() - tempRect2.x) / 33;
							break;
						}
					}
				}
			}
		}
		return tempArray;
	}
}
