package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
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
		/*
		 * NOTA: No ha habido tiempo para implementar
		 * más píldoras. Es lo único que no nos ha dado
		 * tiempo.
		 */
		// Multibomba
		case 1:
			spritesImag = new String[] { "pildora_bomba.png_1" };
			spritesImagDest = new String[] { "nada.png_1" };
			break;
		// Patines
		case 2:
			spritesImag = new String[]{"pildora_patines.gif_1"};
			spritesImagDest = new String[] { "nada.png_1" };
			break;
		// Detonador
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
			spritesImag = new String[]{"pildora_vida.gif_1"};
			spritesImagDest = new String[] { "nada.png_1" };
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
		if(tipo == 1)
			this.bomberman.setMaxBomba(this.bomberman.getMaxBomba() + 1);
		else if(tipo == 2)
			this.bomberman.setVelocidad(this.bomberman.getVelocidad() + 100);
		else if(tipo == 6)
			ControlPrincipal.getJugadorUno().setVidas(ControlPrincipal.getJugadorUno().getVidas() + 1);
	}
}
