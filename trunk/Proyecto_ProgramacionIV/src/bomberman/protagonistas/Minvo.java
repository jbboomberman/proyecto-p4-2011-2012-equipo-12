package bomberman.protagonistas;

import java.util.Collections;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Minvo extends Enemigo {

	public Minvo(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 10;
		deltaY = 10;
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
	
	public void mover() {
		super.mover();
		if (primeraVezMover) {
			sitioLado = this.alLado();
			primeraVezMover = false;
		}
		if (sitioLado[0] == true || sitioLado[1] == true) {
			if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY)) {
				posX += deltaX * tiempoTranscurrido;
			} else {
				deltaX = -(deltaX);
				posX += deltaX * tiempoTranscurrido;
			}
		} else if (sitioLado[2] == true || sitioLado[3] == true) {
			if (!seChoca(posX, posY + (deltaY * tiempoTranscurrido))) {
				posY += deltaY * tiempoTranscurrido;
			} else {
				deltaY = -(deltaY);
				posY += deltaY * tiempoTranscurrido;
			}
		}
	}
}
