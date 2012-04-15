package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

public class Enemigo extends SpriteDinamico{

	protected int puntos;
	
	public Enemigo(Escenario esce, float x, float y, Jugador jug){
		super(esce, x, y, jug);
	}
	
	public void procDestruccion(){
		if(!seDestruir){
		this.serEliminado();
		super.procDestruccion();
		}
	}
	public void serEliminado(){
		jugador.setPuntuacion(jugador.getPuntuacion() + puntos);
		jugador.setPuntuNivel(jugador.getPuntuNivel() + puntos);
		escenario.setPuntuacion();
	}
	
	public boolean determinarChoque(Sprite spr){
		if(spr instanceof Llama)
			this.procDestruccion();
		return true;
	}
}
