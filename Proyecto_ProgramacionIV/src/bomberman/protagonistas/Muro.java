package bomberman.protagonistas;

import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Muro extends SpriteEstatico {

	private boolean destructible;

	public Muro(Escenario esce, float x, float y, boolean destruc) {
		super(esce);
		this.posX = x;
		this.posY = y;
		this.destructible = destruc;
		if (destructible)
			spritesImag = new String[] { "muro.jpg_1", "muro.jpg_2",
					"muro.jpg_3" };
		else
			spritesImag = new String[] { "indestructible.jpg_1" };
		this.anchura = ManagerImagen.getImagen("indestructible.jpg_1")
				.getWidth();
		this.altura = ManagerImagen.getImagen("indestructible.jpg_1")
				.getWidth();
	}
}
