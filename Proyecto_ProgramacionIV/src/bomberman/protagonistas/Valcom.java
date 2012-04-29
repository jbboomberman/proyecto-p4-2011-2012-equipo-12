package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que representa al enemigo Valcom.
 * @author David
 * @version 1.0
 */
public class Valcom extends Enemigo {

	/**
	 * Constructor principal de la clase Valcom
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 */
	public Valcom(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		this.posX = x;
		this.posY = y;
		deltaX = 100;
		deltaY = 100;
		velocidad = 100;
		this.puntos = 100;
		spritesImag = new String[] { "valcom.png_1", "valcom.png_2",
				"valcom.png_3" };
		spritesImagDest = new String[] { "valcom_dest.gif_1",
				"destruccion.png_1", "destruccion.png_2", "destruccion.png_3",
				"destruccion.png_4", "destruccion.png_5" };
	}

	/**
	 * Sobreescribe el método mover() para que
	 * el personaje sea capaz de moverse.
	 */
	public void mover() {
		//Llamamos al método de la clase padre.
		super.mover();
		
		//Si es la primera vez que se mueve.
		if (primeraVezMover) {
			lados = this.alLado();
			/*
			 * Si se puede mover entonces la variable
			 * primeraVezMover pasa a ser false. 
			 */
			if(!(lados[0] == false && lados[1] == false && lados[2] == false && lados[3] == false))
					primeraVezMover = false;
		}
		
		//Si tiene la parte derecha o izquierda libre entramos
		if (lados[0] == true || lados[1] == true) {
			//Si no se choca vamos para la derecha.
			if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY)) {
				posX += deltaX * tiempoTranscurrido;
			//Si no para la izquierda.
			} else {
				deltaX = -(deltaX);
				posX += deltaX * tiempoTranscurrido;
			}
		//Si tiene la parte de arriba o abajo libre
		} else if (lados[2] == true || lados[3] == true) {
			//Si no se choca vamos para abajo
			if (!seChoca(posX, posY + (deltaY * tiempoTranscurrido))) {
				posY += deltaY * tiempoTranscurrido;
			//Si no se choca vamos para arriba
			} else {
				deltaY = -(deltaY);
				posY += deltaY * tiempoTranscurrido;
			}
		}
	}
}
