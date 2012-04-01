package bomberman.protagonistas;

import bomberman.managers.Escenario;

public class Valcom extends SpriteDinamico {

	public Valcom(Escenario esce) {
		super(esce);
		deltaX = 0;
		deltaY = 0;
	}

	public void mover() {
		super.mover();

		posX += deltaX;
		posY += deltaY;
	}
}
