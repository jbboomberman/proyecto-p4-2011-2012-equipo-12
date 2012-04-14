package bomberman.protagonistas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;
import bomberman.managers.ManagerSonido;
import bomberman.ventanas.VentanaJuego;

public class Bomba extends SpriteEstatico {

	private Timer temporizador;
	private boolean pisada;
	private Bomberman bomberman;
	private final int ANCHURA_LLAMA = 32;
	private final int ALTURA_LLAMA = 32;
	private int alcanceExpansion;

	public Bomba(Escenario esce, float x, float y, Bomberman bomber,
			Jugador jug, int alcance) {
		super(esce, jug);
		spritesImag = new String[] { "bombs.gif_1", "bombs.gif_2",
				"bombs.gif_3" };
		this.posX = x;
		this.posY = y;
		this.alcanceExpansion = alcance;
		// ¿Se puede automatizar?
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		pisada = true;
		this.bomberman = bomber;
		this.jugador = jug;
		// ////
		temporizador = new Timer();
		temporizador.schedule(new LoadExplode(), 3000);
	}

	public void paint(Graphics2D g) {
		super.paint(g);
		if (!this.colision(bomberman) && pisada)
			pisada = false;
	}

	public boolean isPisada() {
		return pisada;
	}

	public void setPisada(boolean pisada) {
		this.pisada = pisada;
	}

	public void explotar() {
		temporizador.cancel();
		try {
			ManagerSonido.playClip("explosion.wav", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int[] maxLlama = calcularDistancias();
		colocarLlamas(maxLlama);
		bomberman.setNumBomba(bomberman.getNumBomba() - 1);
		this.destruir();
	}

	class LoadExplode extends TimerTask {

		public void run() {
			explotar();
		}
	}

	private int[] calcularDistancias() {

		int[] tempArray = new int[4];
		int contTrozos = 0;

		/*
		 * Creamos un objeto de la clase Rectangle que nos servirá para
		 * comprobar si hay algún muro o enemigo en el camino que tendrá que
		 * tomar la llama.
		 */
		Rectangle tempRect = new Rectangle(new Dimension(32, 32));

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();

		// Comprobamos la distancia que hay arriba
		boolean[] encon = { false, false, false, false };
		// CUIDADO CON MÁXIMO
		while (!encon[0]) {

			/*
			 * Vamos añadiendo posiciones hasta que lo encontremos.
			 */
			tempRect.y -= this.getAltura();
			if (contTrozos < alcanceExpansion) {
				/*
				 * Comprobamos con los Sprites que hay en la ventana a ver si
				 * alguno se interpone en nuestro camino.
				 */
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);
					// Si alguno se interpone

					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[0] = true;
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[0] = (int) (this.getPosY() - tempRect2.y - ALTURA_LLAMA)
									/ ALTURA_LLAMA;
						} else {
							tempArray[0] = (int) ((this.getPosY() - tempRect2.y - ALTURA_LLAMA) / ALTURA_LLAMA) + 1;
						}
					}
					break;
				}
				contTrozos++;
			} else {
				encon[0] = true;
				tempArray[0] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;
		while (!encon[1]) {
			// Comprobamos la distancia que hay abajo
			tempRect.y += this.getAltura();
			if (contTrozos < alcanceExpansion) {
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);
					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[1] = true;
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[1] = (int) (tempRect2.y - this.getPosY() - ALTURA_LLAMA)
									/ ALTURA_LLAMA;
						} else {
							tempArray[1] = (int) ((tempRect2.y - this.getPosY() - ALTURA_LLAMA) / ALTURA_LLAMA) + 1;
						}
					}
					break;
				}
				contTrozos++;
			} else {
				encon[1] = true;
				tempArray[1] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;

		while (!encon[2]) {
			// Comprobamos la distancia que hay a la derecha
			tempRect.x += this.getAltura();
			if (contTrozos < alcanceExpansion) {
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);

					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[2] = true;
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[2] = (int) (tempRect2.x - this.getPosX() - ANCHURA_LLAMA)
									/ ANCHURA_LLAMA;
						} else {
							tempArray[2] = (int) ((tempRect2.x - this.getPosX() - ANCHURA_LLAMA) / ANCHURA_LLAMA) + 1;
						}
					}
					break;
				}
				contTrozos++;
			} else {
				encon[2] = true;
				tempArray[2] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;

		while (!encon[3]) {
			// Comprobamos la distancia que hay a la izquierda
			tempRect.x -= this.getAltura();
			if (contTrozos < alcanceExpansion) {
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);

					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[3] = true;
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[3] = (int) (this.getPosX() - tempRect2.x - ANCHURA_LLAMA)
									/ ANCHURA_LLAMA;
						} else {
							tempArray[3] = (int) ((this.getPosX() - tempRect2.x - ANCHURA_LLAMA) / ANCHURA_LLAMA) + 1;
						}
					}
					break;
				}
				contTrozos++;
			} else {
				encon[3] = true;
				tempArray[3] = alcanceExpansion;
			}
		}
		return tempArray;
	}

	public void colocarLlamas(int[] distancias) {
		Llama tempLlama = new Llama(escenario, this.getPosX(), this.getPosY(),
				jugador, 1);
		escenario.añadirSprite(tempLlama);

		// Arriba
		for (int i = 0; i < distancias[0] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
					.getPosY() - (((int) tempLlama.getAltura()) * (i + 1)),
					jugador, 2));
		}

		// Abajo
		for (int i = 0; i < distancias[1] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
					.getPosY() + (((int) tempLlama.getAltura()) * (i + 1)),
					jugador, 2));
		}

		// Derecha
		for (int i = 0; i < distancias[2] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX()
					+ (((int) tempLlama.getAnchura()) * (i + 1)), this
					.getPosY(), jugador, 3));
		}

		// Izquierda
		for (int i = 0; i < distancias[3] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX()
					- (((int) tempLlama.getAnchura()) * (i + 1)), this
					.getPosY(), jugador, 3));
		}

		// Punta-arriba
		escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
				.getPosY() - (((int) tempLlama.getAltura()) * distancias[0]),
				jugador, 6));

		// Punta-abajo
		escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
				.getPosY() + (((int) tempLlama.getAltura()) * distancias[1]),
				jugador, 7));

		// Punta derecha
		escenario.añadirSprite(new Llama(escenario, this.getPosX()
				+ (((int) tempLlama.getAnchura()) * distancias[2]), this
				.getPosY(), jugador, 4));

		// Punta izquierda
		escenario.añadirSprite(new Llama(escenario, this.getPosX()
				- (((int) tempLlama.getAnchura()) * distancias[3]), this
				.getPosY(), jugador, 5));
	}
}
