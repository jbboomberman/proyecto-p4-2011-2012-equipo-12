package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Representa a los Sprites que son estáticos ya
 * que no se mueven por la pantalla.
 * @author David
 * @version 1.0
 */
public class SpriteEstatico extends Sprite {

	/**
	 * Constructor principal de la clase SpriteEstatico
	 * @param esce - Escenario
	 * @param jug - Jugador
	 * @param x - float
	 * @param y - float
	 */
	public SpriteEstatico(Escenario esce, Jugador jug, float x, float y) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
	}
}
