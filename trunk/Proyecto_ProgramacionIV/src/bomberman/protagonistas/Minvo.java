package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Minvo extends Enemigo {

	public Minvo(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 10;
		deltaY = 10;
		this.puntos = 400;
		spritesImag = new String []{"minvo.gif_1", "minvo.gif_2", "minvo.gif_3"};
		spritesImagDest = new String []{"minvo_dest.gif_1", "destruccion.png_1", "destruccion.png_2", 
				"destruccion.png_3", "destruccion.png_4", "destruccion.png_5"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
}
