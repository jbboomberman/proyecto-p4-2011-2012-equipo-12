package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class Doria extends Enemigo {

	public Doria(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		this.puntos = 2000;
	}
}
