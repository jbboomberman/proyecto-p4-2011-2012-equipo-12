package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que representa las píldoras del juego.
 * 
 * @author David
 * @version 1.0
 */
public class Pildora extends SpriteEstatico {

	// Tipo de píldora
	private int tipo;
	// Bomberman al que afectará la píldora.
	private Bomberman bomberman;

	/**
	 * Constructor principal de la clase Pildora.
	 * 
	 * @param esce
	 *            - Escenario
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @param tip
	 *            - int
	 * @param bomber
	 *            - Bomberman
	 * @param jug
	 *            - Jugador
	 */
	public Pildora(Escenario esce, float x, float y, int tip, Bomberman bomber,
			Jugador jug) {
		super(esce, jug, x, y);
		this.tipo = tip;
		this.bomberman = bomber;
		switch (tipo) {
		// Multibomba
		case 1:
			spritesImag = new String[] { "pildora_bomba.png_1" };
			spritesImagDest = new String[] { "nada.png_1" };
			break;
		// Detonador
		case 2:
			break;
		// Patines
		case 3:
			break;
		// Wall-walker
		case 4:
			break;
		// SalvaLlama
		case 5:
			break;
		// Vida
		case 6:
			break;
		default:
			System.out.println("Error, no existe ese tipo de píldora");
			break;
		}
		this.posX = x;
		this.posY = y;
		this.altura = CASILLA;
		this.anchura = CASILLA;
	}

	/**
	 * Método que se utiliza para cuando se tenga que destruir la píldora.
	 */
	public void procDestruccion() {
		super.procDestruccion();
		this.bomberman.setMaxBomba(this.bomberman.getMaxBomba() + 1);
	}
}
