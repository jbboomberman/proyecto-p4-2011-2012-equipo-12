package bomberman.protagonistas;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Dahl extends SpriteDinamico {

	public Dahl(Escenario esce, float x, float y) {
		super(esce, x, y);
		deltaX = 10;
		deltaY = 10;
		spritesImag = new String []{"dahl.gif_1", "dahl.gif_2", "dahl.gif_3"};
		spritesImagDest = new String []{"dahl_dest.png_1", "dahl_dest.png_2", "fase_dest.png_1", "fase_dest.png_2", 
				"fase_dest.png_3", "fase_dest.png_4"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
}
