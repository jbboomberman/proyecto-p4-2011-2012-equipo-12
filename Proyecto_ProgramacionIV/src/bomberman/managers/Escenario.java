package bomberman.managers;

import java.awt.Canvas;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Sprite;

/**
 * Interfaz que definirá los métodos comunes
 * para la gestión del juego.
 * Este interfaz tiene como objetivo dar la información de
 * lo que está pasando en la ventana VentanaJuego, aunque
 * parezca que no tiene mucho sentido ya que se podría
 * utilizar la clase VentanaJuego directamente la verdad
 * es que de este modo conseguimos separar las funcionalidades
 * en clases para que así el código sea más reutilizable.
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
	 * Añade un sprite a la lista
	 * @param spr - Sprite
	 */
	public void añadirSprite(Sprite spr);

	/**
	 * Devuelve la lista con todos los Sprites
	 * @return ArrayList<Sprite>
	 */
	public ArrayList<Sprite> getLista();

	/**
	 * Actualiza la puntuación del juego
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
	 * Busca a un personaje que este en la misma posición
	 * que el personaje por parámetro. Sirve para que la
	 * puerta sepa si tiene un muro encima o no.
	 * Devuelve los personajes que esten colisionando con
	 * el personaje enviado por parámetro.
	 * @return tempArray - ArrayList<Sprite>
	 */
	public ArrayList<Sprite> buscarPersonajePos(Class clase, Sprite spr);

	/**
	 * Devuelve el número de rivales que quedan en este nivel
	 * @return int - Nñumero de rivales
	 */
	public int rivalesQuedan();
}
