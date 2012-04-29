package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Calse que representa al enemigo Minvo.
 * @author David
 * @version 1.0
 */
public class Minvo extends Enemigo {

	/**
	 * Constructor principal de la clase Minvo.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 */
	public Minvo(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 100;
		deltaY = 100;
		this.puntos = 400;
		spritesImag = new String[] { "minvo.gif_1", "minvo.gif_2",
				"minvo.gif_3" };
		spritesImagDest = new String[] { "minvo_dest.gif_1",
				"destruccion.png_1", "destruccion.png_2", "destruccion.png_3",
				"destruccion.png_4", "destruccion.png_5" };
		this.anchura = CASILLA;
		this.altura = CASILLA;
		this.velocidad = 200;
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
