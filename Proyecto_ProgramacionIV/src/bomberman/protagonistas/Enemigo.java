package bomberman.protagonistas;

import java.util.ArrayList;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Clase que representa a los Sprites que son enemigos
 * del personaje principal que es Bomberman. Tendrán un
 * método propio que se llama 'serEliminado' que
 * modificará la puntuación del jugador.
 * @author David
 * @version 1.0 
 */
public class Enemigo extends SpriteDinamico {

	//Puntos del enemigo
	protected int puntos;
	//Nos servirá para aleatorizar el moviemiento
	protected ArrayList<Integer> aleatorizacion;

	/**
	 * Contructor principal de la clase Enemigo.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 */
	public Enemigo(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		aleatorizacion = new ArrayList<Integer>();
	}

	/**
	 * Este método determina que hacer en caso de
	 * que el Enemigo tenga que morir.
	 */
	public void procDestruccion() {
		/*
		 * No tiene que estar muriendose porque
		 * no se pueden estar muriendo dos veces.
		 */
		if (!seDestruir) {
			this.serEliminado();
			super.procDestruccion();
		}
	}

	/**
	 * Aumenta la puntuación del usuario cuando
	 * el Enemigo se este muriendo.
	 */
	public void serEliminado() {
		jugador.setPuntuacion(jugador.getPuntuacion() + puntos);
		jugador.setPuntuNivel(jugador.getPuntuNivel() + puntos);
		escenario.setPuntuacion();
	}

	/**
	 * Determina que hacer en caso de que se choque
	 * con algún objeto.
	 * @return boolean - Si se ha chocado o no.
	 */
	public boolean determinarChoque(Sprite spr) {
		//Si es Llama nos morimos
		if (spr instanceof Llama)
			this.procDestruccion();
		//Si es Bomberman se muere Bomberman.
		else if(spr instanceof Bomberman)
			spr.procDestruccion();
		return true;
	}
}
