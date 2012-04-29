package bomberman.protagonistas;

import bomberman.enumeraciones.TiposLlama;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Esta clase representa las llamas que generan cuando
 * se produce una explosión de una bomba.
 * @author David
 * @version 1.0
 */
public class Llama extends SpriteDinamico {

	//El tipo de llama.
	private TiposLlama tipo;

	/**
	 * Constructor principal de la clase Llama.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 * @param tip - TipoLlama
	 */
	public Llama(Escenario esce, float x, float y, Jugador jug, TiposLlama tip) {
		super(esce, x, y, jug);
		this.escenario = esce;
		this.tipo = tip;
		this.velocidadPic = 10;

		/*
		 * Dependiendo de la clase de Llama tendrá
		 * diferentes imágenes.
		 */
		switch (tipo) {
		// Centro
		case CENTRAL:
			spritesImag = new String[] { "llama.gif_1", "llama.gif_2",
					"llama.gif_3", "llama.gif_4" };
			break;
		// Intermedio - vertical
		case INTER_VER:
			spritesImag = new String[] { "llama.gif_21", "llama.gif_22",
					"llama.gif_23", "llama.gif_24" };
			break;
		// Intermedio - horizontal
		case INTER_HOR:
			spritesImag = new String[] { "llama.gif_9", "llama.gif_10",
					"llama.gif_11", "llama.gif_12" };
			break;
		// Punta - derecha
		case PUNTA_DER:
			spritesImag = new String[] { "llama.gif_17", "llama.gif_18",
					"llama.gif_19", "llama.gif_20" };
			break;
		// Punta - izquierda
		case PUNTA_IZQ:
			spritesImag = new String[] { "llama.gif_13", "llama.gif_14",
					"llama.gif_15", "llama.gif_16" };
			break;
		// Punta - arriba
		case PUNTA_ARRIBA:
			spritesImag = new String[] { "llama.gif_25", "llama.gif_26",
					"llama.gif_27", "llama.gif_28" };
			break;
		// Punta - abajo
		case PUNTA_ABJ:
			spritesImag = new String[] { "llama.gif_29", "llama.gif_30",
					"llama.gif_31", "llama.gif_32" };
			break;
		}
		this.anchura = CASILLA;
		this.altura = CASILLA;
	}

	/**
	 * Sobreescribe el método mover() y si se choca
	 * con algo determina que tiene que hacer.
	 */
	public void mover() {
		super.mover();
		seChoca(this.getPosX(), this.getPosY());
		/*
		 * Si llegamos a la última imagen del
		 * array entonces hay que acabar la llama.
		 */
		if (imagActual == (spritesImag.length - 1))
			this.destruir();
	}

	/**
	 * Determina que hacer en caso de choque.
	 * @param spr - Sprite
	 * @return boolean - Si se ha chocado con
	 * algo o no.
	 */
	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Muro) {
			if (((Muro) spr).isDestructible())
				spr.procDestruccion();
		} else if (spr instanceof Enemigo || spr instanceof Bomberman)
			spr.procDestruccion();
		return true;
	}
}
