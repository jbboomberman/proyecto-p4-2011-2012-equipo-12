package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class SpriteEstatico extends Sprite {

	SpriteEstatico(Escenario esce, Jugador jug, float x, float y) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
	}
}
