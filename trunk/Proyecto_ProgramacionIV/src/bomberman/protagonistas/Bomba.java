package bomberman.protagonistas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import bomberman.enumeraciones.TiposLlama;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que Bomba que representa a la bombas que pone el personaje principal.
 * 
 * @author David
 * @version 1.0
 */
public class Bomba extends SpriteEstatico {

	// Temporizador de la bomba
	private Timer temporizador;
	// Para saber si ha sido pisada o no.
	private boolean pisada;
	// Bomberman que la ha puesto
	private Bomberman bomberman;
	// Alcance de la explosión
	private int alcanceExpansion;

	/**
	 * Constructor principal de la clase Bomba.
	 * 
	 * @param esce
	 *            - Escenario
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @param bomber
	 *            - Bomberman
	 * @param jug
	 *            - Jugador
	 * @param alcance
	 *            - int
	 */
	public Bomba(Escenario esce, float x, float y, Bomberman bomber,
			Jugador jug, int alcance) {
		super(esce, jug, x, y);
		// Los nombres de las imágenes de la bomba
		spritesImag = new String[] { "bombs.gif_1", "bombs.gif_2",
				"bombs.gif_3" };
		this.posX = x;
		this.posY = y;
		this.alcanceExpansion = alcance;
		this.altura = CASILLA;
		this.anchura = CASILLA;
		pisada = true;
		this.bomberman = bomber;
		this.jugador = jug;
		temporizador = new Timer();
		/*
		 * El temporador tendrá 3 segundos y cuando los alcance explotará la
		 * bomba.
		 */
		temporizador.schedule(new CargarExplosion(), 3000);
	}

	/**
	 * Sobreescribimos el método pintar para saber si el Bomberman ha dejado de
	 * pisar la bomba.
	 */
	public void paint(Graphics2D g) {
		super.paint(g);
		if (!this.colision(bomberman) && pisada)
			pisada = false;
	}

	/**
	 * Devuelve si puede ser pisada.
	 * 
	 * @return pisada - boolean
	 */
	public boolean isPisada() {
		return pisada;
	}

	/**
	 * Modificar el parámetro para que pueda ser pisada.
	 * 
	 * @param pisada
	 *            - boolean
	 */
	public void setPisada(boolean pisada) {
		this.pisada = pisada;
	}

	/**
	 * Método que hará que la bomba explote.
	 */
	public void explotar() {
		// Cancelamos el temporizador.
		temporizador.cancel();
		// try {
		// ManagerSonido.playClip("explosion.wav", false);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// Calculamos hasta dónde puede ir la llama en cada lado.
		int[] maxLlama = calcularDistancias();
		// Colocamos las llamas.
		colocarLlamas(maxLlama);
		// Restamos el número de bombas puestas.
		bomberman.setNumBomba(bomberman.getNumBomba() - 1);
		// La destruimos.
		this.destruir();
	}

	/**
	 * Cuando el temporizador acabe se ejecutará el método run().
	 * 
	 * @author David
	 * @version 1.0
	 */
	class CargarExplosion extends TimerTask {

		public void run() {
			explotar();
		}
	}

	/**
	 * Calcula la distancia que pueden recorrer las llamas en cada lado.
	 * 
	 * @return int[] - La distancia que pueden recorrer las llamas en cada lado.
	 */
	private int[] calcularDistancias() {

		int[] tempArray = new int[4];
		int contTrozos = 0;

		/*
		 * Creamos un objeto de la clase Rectangle que nos servirá para
		 * comprobar si hay algún muro o enemigo en el camino que tendrá que
		 * tomar la llama.
		 */
		Rectangle tempRect = new Rectangle(new Dimension(CASILLA, CASILLA));
		// La posición de la bomba.
		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		boolean[] encon = { false, false, false, false };

		// Comprobamos la distancia que hay arriba
		while (!encon[0]) {
			/*
			 * Vamos quitando posiciones hasta que lo encontremos.
			 */
			tempRect.y -= this.getAltura();
			if (contTrozos < alcanceExpansion) {
				/*
				 * Comprobamos con los Sprites que hay en la ventana a ver si
				 * alguno se interpone en nuestro camino.
				 */
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);
					/*
					 * Si alguno se interpone y es de tipo Muro (el único que
					 * para la llama.
					 */
					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {
						encon[0] = true;
						// Si es indestructible
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[0] = (int) (this.getPosY() - tempRect2.y - CASILLA)
									/ CASILLA;
						} else {
							/*
							 * Hay que sumarle uno para que alcance el muro
							 * destructible.
							 */
							tempArray[0] = (int) ((this.getPosY() - tempRect2.y - CASILLA) / CASILLA) + 1;
						}
						break;
					}
				}
				contTrozos++;
				/*
				 * Si hemos llegado al límite y no hemos encontrado nada
				 * entonces la llama sólo llega al límite.
				 */
			} else {
				encon[0] = true;
				tempArray[0] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;

		// Comporbamos la distancia que hay abajo.
		while (!encon[1]) {
			/*
			 * Vamos añadiendo posiciones hasta que lo encontremos.
			 */
			tempRect.y += this.getAltura();
			if (contTrozos < alcanceExpansion) {
				/*
				 * Comprobamos con los Sprites que hay en la ventana a ver si
				 * alguno se interpone en nuestro camino.
				 */
				for (Sprite sprTemp : escenario.getLista()) {
					/*
					 * Si alguno se interpone y es de tipo Muro (el único que
					 * para la llama.
					 */
					Rectangle tempRect2 = getRectangle(sprTemp);
					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[1] = true;
						// Si es indestructible
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[1] = (int) (tempRect2.y - this.getPosY() - CASILLA)
									/ CASILLA;
						} else {
							/*
							 * Hay que sumarle uno para que alcance el muro
							 * destructible.
							 */
							tempArray[1] = (int) ((tempRect2.y - this.getPosY() - CASILLA) / CASILLA) + 1;
						}
						break;
					}
				}
				contTrozos++;
				/*
				 * Si hemos llegado al límite y no hemos encontrado nada
				 * entonces la llama sólo llega al límite.
				 */
			} else {
				encon[1] = true;
				tempArray[1] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;

		// Comprobamos la distancia que hay a la derecha
		while (!encon[2]) {
			/*
			 * Vamos añadiendo posiciones hasta que lo encontremos.
			 */
			tempRect.x += this.getAltura();
			if (contTrozos < alcanceExpansion) {
				/*
				 * Comprobamos con los Sprites que hay en la ventana a ver si
				 * alguno se interpone en nuestro camino.
				 */
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);
					/*
					 * Si alguno se interpone y es de tipo Muro (el único que
					 * para la llama.
					 */
					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[2] = true;
						// Si es indestructible
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[2] = (int) (tempRect2.x - this.getPosX() - CASILLA)
									/ CASILLA;
						} else {
							/*
							 * Hay que sumarle uno para que alcance el muro
							 * destructible.
							 */
							tempArray[2] = (int) ((tempRect2.x - this.getPosX() - CASILLA) / CASILLA) + 1;
						}
						break;
					}
				}
				contTrozos++;
				/*
				 * Si hemos llegado al límite y no hemos encontrado nada
				 * entonces la llama sólo llega al límite.
				 */
			} else {
				encon[2] = true;
				tempArray[2] = alcanceExpansion;
			}
		}

		tempRect.x = (int) this.getPosX();
		tempRect.y = (int) this.getPosY();
		contTrozos = 0;

		// Comprobamos la distancia que hay a la izquierda
		while (!encon[3]) {
			/*
			 * Vamos quitando posiciones hasta que lo encontremos.
			 */
			tempRect.x -= this.getAltura();
			if (contTrozos < alcanceExpansion) {
				/*
				 * Comprobamos con los Sprites que hay en la ventana a ver si
				 * alguno se interpone en nuestro camino.
				 */
				for (Sprite sprTemp : escenario.getLista()) {
					Rectangle tempRect2 = getRectangle(sprTemp);
					/*
					 * Si alguno se interpone y es de tipo Muro (el único que
					 * para la llama.
					 */
					if (tempRect.intersects(tempRect2)
							&& sprTemp instanceof Muro) {

						encon[3] = true;
						// Si es indestructible
						if (!((Muro) sprTemp).isDestructible()) {
							tempArray[3] = (int) (this.getPosX() - tempRect2.x - CASILLA)
									/ CASILLA;
						} else {
							/*
							 * Hay que sumarle uno para que alcance el muro
							 * destructible.
							 */
							tempArray[3] = (int) ((this.getPosX() - tempRect2.x - CASILLA) / CASILLA) + 1;
						}
						break;
					}
				}
				contTrozos++;
				/*
				 * Si hemos llegado al límite y no hemos encontrado nada
				 * entonces la llama sólo llega al límite.
				 */
			} else {
				encon[3] = true;
				tempArray[3] = alcanceExpansion;
			}
		}
		return tempArray;
	}

	/**
	 * Colocamos las llamas cuando la bomba explota.
	 * 
	 * @param distancias
	 *            - int[] - Las distancias a las que puede llegar.
	 */
	public void colocarLlamas(int[] distancias) {

		// Creamos la llama central
		Llama tempLlama = new Llama(escenario, this.getPosX(), this.getPosY(),
				jugador, TiposLlama.CENTRAL);
		escenario.añadirSprite(tempLlama);

		/*
		 * Arriba, creamos las llamas que vayan hacia arriba pero sin ser la
		 * última.
		 */
		for (int i = 0; i < distancias[0] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
					.getPosY() - (((int) tempLlama.getAltura()) * (i + 1)),
					jugador, TiposLlama.INTER_VER));
		}

		/*
		 * Abajo, creamos las llamas que vayan hacia abajo pero sin ser la
		 * última.
		 */
		for (int i = 0; i < distancias[1] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
					.getPosY() + (((int) tempLlama.getAltura()) * (i + 1)),
					jugador, TiposLlama.INTER_VER));
		}

		/*
		 * Derecha, creamos las llamas que vayan hacia derecha pero sin ser la
		 * última.
		 */
		for (int i = 0; i < distancias[2] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX()
					+ (((int) tempLlama.getAnchura()) * (i + 1)), this
					.getPosY(), jugador, TiposLlama.INTER_HOR));
		}

		/*
		 * Izquierda, creamos las llamas que vayan hacia izquierda pero sin ser
		 * la última.
		 */
		for (int i = 0; i < distancias[3] - 1; i++) {
			escenario.añadirSprite(new Llama(escenario, this.getPosX()
					- (((int) tempLlama.getAnchura()) * (i + 1)), this
					.getPosY(), jugador, TiposLlama.INTER_HOR));
		}

		// Creamos la llama punta arriba
		escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
				.getPosY() - (((int) tempLlama.getAltura()) * distancias[0]),
				jugador, TiposLlama.PUNTA_ARRIBA));

		// Creamos la llama punta abajo
		escenario.añadirSprite(new Llama(escenario, this.getPosX(), this
				.getPosY() + (((int) tempLlama.getAltura()) * distancias[1]),
				jugador, TiposLlama.PUNTA_ABJ));

		// Creamos la llama punta derecha
		escenario.añadirSprite(new Llama(escenario, this.getPosX()
				+ (((int) tempLlama.getAnchura()) * distancias[2]), this
				.getPosY(), jugador, TiposLlama.PUNTA_DER));

		// Creamos la llama punta izquierda
		escenario.añadirSprite(new Llama(escenario, this.getPosX()
				- (((int) tempLlama.getAnchura()) * distancias[3]), this
				.getPosY(), jugador, TiposLlama.PUNTA_IZQ));
	}
}
