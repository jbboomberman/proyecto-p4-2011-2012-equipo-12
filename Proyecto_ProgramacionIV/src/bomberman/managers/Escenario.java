package bomberman.managers;

import java.awt.Canvas;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Sprite;

/**
 * Interfaz que definir� los m�todos comunes de
 * para la gesti�n del juego.
 * @author David
 * @version 1.0
 */
public interface Escenario extends ImageObserver {

	/**
	 * Devolver el Canvas donde tiene lugar
	 * el juego
	 * @return Canvas
	 */
	public Canvas getPanel();

	/**
	 * Devuelve el bomberman del juego
	 * @return
	 */
	public Bomberman getBomberman();

	/**
	 * A�ade un sprite a la lista
	 * @param spr - Sprite
	 */
	public void a�adirSprite(Sprite spr);

	/**
	 * Devuelve la lista con todos los Sprites
	 * @return ArrayList<Sprite>
	 */
	public ArrayList<Sprite> getLista();

	/**
	 * Actualiza la puntuaci�n del juego
	 */
	public void setPuntuacion();

	/**
	 * Hace que la partida se acabe
	 * @param acabar - boolean
	 */
	public void setAcabarPartida(boolean acabar);

	/**
	 * Hace que el nivel se haya superado
	 * @param estado - boolean
	 */
	public void setSuperadoNivel(boolean estado);

	/**
	 * Busca a un personaje que este en la misma posici�n
	 * que el personaje por par�metro. Sirve para que la
	 * puerta sepa si tiene un muro encima o no.
	 * Devuelve los personajes que esten colisionando con
	 * el personaje enviado por par�metro.
	 * @return tempArray - ArrayList<Sprite>
	 */
	public ArrayList<Sprite> buscarPersonajePos(Class clase, Sprite spr);

	/**
	 * Devuelve el n�mero de rivales que quedan en este nivel
	 * @return int - N�umero de rivales
	 */
	public int rivalesQuedan();
}
