package bomberman.protagonistas;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Valcom extends SpriteDinamico {

	public Valcom(Escenario esce, int x, int y) {
		super(esce);
		this.posX = x;
		this.posY = y;
		deltaX = 10;
		deltaY = 10;
		spritesImag = new String []{"enemies.png_1", "enemies.png_2", "enemies.png_3", 
				"enemies.png_4", "enemies.png_5", "enemies.png_6"};
		spritesImagDest = new String []{"valcom_dest.png_1", "valcom_dest.png_2", 
				"valcom_dest.png_3", "valcom_dest.png_4", "valcom_dest.png_5"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}

	public void mover() {
		super.mover();
//		posX += deltaX;
//		posY += deltaY;
	}
}
