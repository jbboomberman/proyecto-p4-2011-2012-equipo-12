package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class Enemigo extends SpriteDinamico{

	protected int puntos;
	
	public Enemigo(Escenario esce, float x, float y, Jugador jug){
		super(esce, x, y, jug);
	}
	
	public void serEliminado(){
		jugador.setPuntuacion(jugador.getPuntuacion() + puntos);
		jugador.setPuntuNivel(jugador.getPuntuNivel() + puntos);
	}
}
