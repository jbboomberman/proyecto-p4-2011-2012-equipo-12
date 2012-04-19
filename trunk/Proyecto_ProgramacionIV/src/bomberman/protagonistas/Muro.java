package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Muro extends SpriteEstatico {

	private boolean destructible;

	public Muro(Escenario esce, float x, float y, boolean destruc, Jugador jug) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
		this.destructible = destruc;
		if (destructible) {
			spritesImag = new String[] { "muro.jpg_1" };
			spritesImagDest = new String[] { "muro.jpg_2", "muro.jpg_3",
					"muro.jpg_4", "muro.jpg_5", "muro.jpg_6", "muro.jpg_7",
					"muro.jpg_8" };
		} else
			spritesImag = new String[] { "indestructible.jpg_1" };
		this.anchura = ManagerImagen.getImagen("indestructible.jpg_1")
				.getWidth();
		this.altura = ManagerImagen.getImagen("indestructible.jpg_1")
				.getHeight();
		velocidadPic = 5;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}
}
