package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Valcom extends Enemigo {

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
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
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
