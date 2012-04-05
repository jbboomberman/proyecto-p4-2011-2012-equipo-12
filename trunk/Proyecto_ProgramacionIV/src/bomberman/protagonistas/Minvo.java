package bomberman.protagonistas;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Minvo extends SpriteDinamico {

	public Minvo(Escenario esce, float x, float y) {
		super(esce, x, y);
		deltaX = 10;
		deltaY = 10;
		spritesImag = new String []{"minvo.gif_1", "minvo.gif_2", "minvo.gif_3"};
		spritesImagDest = new String []{"minvo_dest.gif_1", "fase_dest.png_1", "fase_dest.png_2", 
				"fase_dest.png_3", "fase_dest.png_4"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
}
