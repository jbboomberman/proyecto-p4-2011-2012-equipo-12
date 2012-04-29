package bomberman.protagonistas;

import java.util.ArrayList;
import java.util.Collections;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Dahl extends Enemigo {
	
	public Dahl(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 10;
		deltaY = 10;
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

	public void mover() {
		super.mover();

		if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY
				+ (deltaY * tiempoTranscurrido))) {
			posX += deltaX * tiempoTranscurrido;
			posY += deltaY * tiempoTranscurrido;
		}

		if (estaInterseccion()) {
			lados = this.alLado();

			if (lados[0] && lados[1]) {
				Collections.shuffle(aleatorizacion);
				deltaX = aleatorizacion.get(0);
			//Derecha
			} else if (lados[0]) {
				deltaX = velocidad;
			//Izquierda
			} else if (lados[1]) {
				deltaX = -velocidad;
			} else {
				deltaX = 0;
			}

			if (lados[2] && lados[3]) {
				Collections.shuffle(aleatorizacion);
				deltaY = aleatorizacion.get(0);
			//Arriba
			} else if (lados[2]) {
				deltaY = -velocidad;
			//Abajo
			} else if (lados[3]) {
				deltaY = velocidad;
			} else {
				deltaY = 0;
			}

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
