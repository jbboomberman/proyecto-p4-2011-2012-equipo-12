package bomberman.protagonistas;

import java.util.Collections;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que representa al enemigo Dahl.
 * @author David
 * @version 1.0
 */
public class Dahl extends Enemigo {
	
	/**
	 * Constructor principal de la clase Dahl.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 */
	public Dahl(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 100;
		deltaY = 100;
		velocidad = 100;
		this.puntos = 200;
		spritesImag = new String[] { "dahl.gif_1", "dahl.gif_2", "dahl.gif_3" };
		spritesImagDest = new String[] { "dahl.gif_4", "dahl.gif_5",
				"destruccion.png_1", "destruccion.png_2", "destruccion.png_3",
				"destruccion.png_4", "destruccion.png_5" };
		this.anchura = CASILLA;
		this.altura = CASILLA;
		aleatorizacion.add(velocidad);
		aleatorizacion.add(-velocidad);
	}

	/**
	 * M�todo que sobreescribe el m�todo mover() de
	 * la clase padre.
	 */
	public void mover() {
		/*
		 * Llamamos al m�todo mover() de la clase padre
		 * para que cambie de sprite.
		 */
		super.mover();

		/*
		 * S�lo movemos el personaje si no se va
		 * a chocar con nada.
		 */
		if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY
				+ (deltaY * tiempoTranscurrido))) {
			posX += deltaX * tiempoTranscurrido;
			posY += deltaY * tiempoTranscurrido;
		}

		/*
		 * En caso de que este en una intersecci�n
		 * podemos cambiar el rumbo.
		 */
		if (estaInterseccion()) {
			//Miramos que lados tenemos libres.
			lados = this.alLado();

			//Si tenemos derecha o arriba libres.
			if (lados[0] && lados[1]) {
				/*
				 * Elegimos al azar una velocidad
				 * (Positiva o negativa)
				 */
				Collections.shuffle(aleatorizacion);
				deltaX = aleatorizacion.get(0);
			 // Si s�lo tenemos la derecha libre
			} else if (lados[0]) {
				deltaX = velocidad;
			//Si s�lo tenemos la izquierda libre.
			} else if (lados[1]) {
				deltaX = -velocidad;
			//Si no tenemos ni derecha ni izquierda libre
			} else {
				deltaX = 0;
			}

			//Si tenemos arriba y abajo libres.
			if (lados[2] && lados[3]) {
				/*
				 * Elegimos al azar una velocidad
				 * (Positiva o negativa)
				 */
				Collections.shuffle(aleatorizacion);
				deltaY = aleatorizacion.get(0);
			//Si s�lo tenemos arriba libre
			} else if (lados[2]) {
				deltaY = -velocidad;
			//Si s�lo tenemos abajo libre.
			} else if (lados[3]) {
				deltaY = velocidad;
			//Si no tenemos ninguna libre.
			} else {
				deltaY = 0;
			}

			/*
			 * En caso de que tengamos que elegir entre
			 * ir horizontalmente o verticalmente lo
			 * elegimos al azar.
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
}
