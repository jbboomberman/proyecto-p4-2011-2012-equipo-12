package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

/**
 * Clase puerta que representa la puerta que
 * nos permitirá acabar el nivel.
 * @author David
 * @version 1.0
 */
public class Puerta extends SpriteEstatico {

	/**
	 * Constructor principal de la clase Puerta.
	 * @param esce - Escenario
	 * @param jug - Jugador
	 * @param x - float
	 * @param y - float
	 */
	public Puerta(Escenario esce, Jugador jug, float x, float y) {
		super(esce, jug, x, y);
		this.spritesImag = new String[] { "puerta.png_1" };
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
}
