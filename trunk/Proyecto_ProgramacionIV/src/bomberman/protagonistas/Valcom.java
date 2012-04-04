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
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
	}

	public void mover() {
		super.mover();
//		posX += deltaX;
//		posY += deltaY;
	}
}
