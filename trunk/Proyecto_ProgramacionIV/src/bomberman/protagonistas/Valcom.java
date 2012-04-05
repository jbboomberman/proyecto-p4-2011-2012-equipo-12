package bomberman.protagonistas;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Valcom extends SpriteDinamico {

	public Valcom(Escenario esce, float x, float y) {
		super(esce, x, y);
		this.posX = x;
		this.posY = y;
		deltaX = 10;
		deltaY = 10;
		spritesImag = new String []{"valcom.png_1", "valcom.png_2", "valcom.png_3", 
				"valcom.png_4", "valcom.png_5", "valcom.png_6"};
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
