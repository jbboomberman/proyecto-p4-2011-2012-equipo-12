package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class Pildora extends SpriteEstatico {

	private int tipo;
	private final int ANCHURA_PILDORA = 33;
	private final int ALTURA_PILDORA = 33;
	private Bomberman bomberman;

	public Pildora(Escenario esce, float x, float y, int tip, Bomberman bomber,
			Jugador jug) {
		super(esce, jug);
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
		this.altura = ALTURA_PILDORA;
		this.anchura = ANCHURA_PILDORA;
	}

	public void procDestruccion() {
		super.procDestruccion();
		this.bomberman.setMaxBomba(this.bomberman.getMaxBomba() + 1);
	}
}
