package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Puerta extends SpriteEstatico {

	public Puerta(Escenario esce, Jugador jug, float x, float y) {
		super(esce, jug, x, y);
		this.spritesImag = new String[]{"puerta.png_1"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
}
