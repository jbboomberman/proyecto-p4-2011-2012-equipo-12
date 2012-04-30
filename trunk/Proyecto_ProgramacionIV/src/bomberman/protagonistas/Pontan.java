package bomberman.protagonistas;

import java.util.Collections;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Esta clase representa al enemigo Pontan.
 * 
 * @author David
 * @version 1.0
 */
public class Pontan extends Enemigo {

	/**
	 * Constructor principal de la clase Pontan
	 * 
	 * @param esce
	 *            - Escenario
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @param jug
	 *            - Jugador
	 */
	public Pontan(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		this.puntos = 1000;
		this.posX = x;
		this.posY = y;
		deltaX = 100;
		deltaY = 100;
		velocidad = 100;
		spritesImag = new String[] { "pontan.png_1", "pontan.png_2",
				"pontan.png_3" };
		spritesImagDest = new String[] { "pontan_dest.gif_1",
				"destruccion.png_1", "destruccion.png_2", "destruccion.png_3",
				"destruccion.png_4", "destruccion.png_5" };
		this.anchura = CASILLA;
		this.altura = CASILLA;
		aleatorizacion.add(velocidad);
		aleatorizacion.add(-velocidad);
	}

	/**
	 * Método que sobreescribe el método mover() de la clase padre.
	 */
	public void mover() {
		/*
		 * Llamamos al método mover() de la clase padre para que cambie de
		 * sprite.
		 */
		super.mover();

		/*
		 * Sólo movemos el personaje si no se va a chocar con nada.
		 */
		if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY
				+ (deltaY * tiempoTranscurrido))) {
			posX += deltaX * tiempoTranscurrido;
			posY += deltaY * tiempoTranscurrido;
		}

		/*
		 * En caso de que este en una intersección podemos cambiar el rumbo.
		 */
		if (estaInterseccion()) {
			// Miramos que lados tenemos libres.
			lados = this.alLado();

			// Si tenemos derecha o arriba libres.
			if (lados[0] && lados[1]) {
				/*
				 * Elegimos al azar una velocidad (Positiva o negativa)
				 */
				Collections.shuffle(aleatorizacion);
				deltaX = aleatorizacion.get(0);
				// Si sólo tenemos la derecha libre
			} else if (lados[0]) {
				deltaX = velocidad;
				// Si sólo tenemos la izquierda libre.
			} else if (lados[1]) {
				deltaX = -velocidad;
				// Si no tenemos ni derecha ni izquierda libre
			} else {
				deltaX = 0;
			}

			// Si tenemos arriba y abajo libres.
			if (lados[2] && lados[3]) {
				/*
				 * Elegimos al azar una velocidad (Positiva o negativa)
				 */
				Collections.shuffle(aleatorizacion);
				deltaY = aleatorizacion.get(0);
				// Si sólo tenemos arriba libre
			} else if (lados[2]) {
				deltaY = -velocidad;
				// Si sólo tenemos abajo libre.
			} else if (lados[3]) {
				deltaY = velocidad;
				// Si no tenemos ninguna libre.
			} else {
				deltaY = 0;
			}

			/*
			 * En caso de que tengamos que elegir entre ir horizontalmente o
			 * verticalmente lo elegimos al azar.
			 */
			if (deltaX != 0 && deltaY != 0) {
				Collections.shuffle(aleatorizacion);
				int tempNum = aleatorizacion.get(0);
				if (tempNum > 0) {
					deltaX = 0;
				} else
					deltaY = 0;
			}
		}
	}

	/**
	 * Tenemos que redefinir el método determinarChoque ya que puede traspasar
	 * muros destructibles.
	 */
	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Llama)
			this.procDestruccion();
		else if (spr instanceof Bomberman)
			spr.procDestruccion();
		else if (spr instanceof Muro) {
			if (((Muro) spr).isDestructible())
				// Si es destructible que pase por enecima.
				return false;
		}
		return true;
	}
}
