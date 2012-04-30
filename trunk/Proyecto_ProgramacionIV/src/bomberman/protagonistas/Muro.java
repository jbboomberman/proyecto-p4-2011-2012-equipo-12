package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que representa al objeto Muro. Representa tanto al muro destructible
 * como el no destructible.
 * 
 * @author David
 * @version 1.0
 */
public class Muro extends SpriteEstatico {

	// Para saber si es destructible o indestructible.
	private boolean destructible;

	/**
	 * Constructor principal de la clase Muro.
	 * 
	 * @param esce
	 *            - Escenario
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @param destruc
	 *            - boolean
	 * @param jug
	 *            - Jugador
	 */
	public Muro(Escenario esce, float x, float y, boolean destruc, Jugador jug) {
		super(esce, jug, x, y);
		this.posX = x;
		this.posY = y;
		this.destructible = destruc;
		/*
		 * Dependiendo de si es destructible o indestructible tendrá diferentes
		 * imágenes.
		 */
		if (destructible) {
			spritesImag = new String[] { "muro.jpg_1" };
			spritesImagDest = new String[] { "muro.jpg_2", "muro.jpg_3",
					"muro.jpg_4", "muro.jpg_5", "muro.jpg_6", "muro.jpg_7",
					"muro.jpg_8" };
		} else
			spritesImag = new String[] { "indestructible.jpg_1" };
		this.anchura = CASILLA;
		this.altura = CASILLA;
		velocidadPic = 5;
	}

	/**
	 * Devuleve la variable destructible.
	 * 
	 * @return destructible - boolean
	 */
	public boolean isDestructible() {
		return destructible;
	}

	/**
	 * Modifica la variable destructible.
	 * 
	 * @param destructible
	 *            - boolean
	 */
	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}
}
