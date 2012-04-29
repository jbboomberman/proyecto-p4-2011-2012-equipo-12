package bomberman.protagonistas;

import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class Enemigo extends SpriteDinamico {

	protected int puntos;
	protected ArrayList<Integer> aleatorizacion;
	protected boolean[] lados;

	public Enemigo(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		aleatorizacion = new ArrayList<Integer>();
	}

	public void procDestruccion() {
		if (!seDestruir) {
			this.serEliminado();
			super.procDestruccion();
		}
	}

	public void serEliminado() {
		jugador.setPuntuacion(jugador.getPuntuacion() + puntos);
		jugador.setPuntuNivel(jugador.getPuntuNivel() + puntos);
		escenario.setPuntuacion();
	}

	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Llama)
			this.procDestruccion();
		else if(spr instanceof Bomberman)
			spr.procDestruccion();
		return true;
	}
}
