package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.enumeraciones.MirarLado;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

/**
 * Clase para el personaje BombermanNegro que hereda de Bomberman.
 * 
 * @author David
 * @version 1.0
 */
public class BombermanNegro extends Bomberman {

	/**
	 * Constructor de la clase BombermanNegro.
	 * 
	 * @param esce
	 *            - Escenario
	 * @param x
	 *            - float
	 * @param y
	 *            - float
	 * @param jug
	 *            - Jugador
	 */
	public BombermanNegro(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		/*
		 * Definimos el nombre de las imágenes de cada lado.
		 */
		spritesImagUp = new String[] { "bomber2_up_down.gif_2",
				"bomber2_up_down.gif_1", "bomber2_up_down.gif_3",
				"bomber2_up_down.gif_1" };
		spritesImagDown = new String[] { "bomber2_up_down.gif_5",
				"bomber2_up_down.gif_4", "bomber2_up_down.gif_6",
				"bomber2_up_down.gif_4" };
		spritesImagRight = new String[] { "bomber2_der.gif_2",
				"bomber2_der.gif_1", "bomber2_der.gif_3", "bomber2_der.gif_1" };
		spritesImagLeft = new String[] { "bomber2_izq.gif_2",
				"bomber2_izq.gif_1", "bomber2_izq.gif_3", "bomber2_izq.gif_1" };
		spritesDestUp = new String[] { "bomber2_dest.gif_3" };
		spritesDestDown = new String[] { "bomber2_dest.gif_1" };
		spritesDestRight = new String[] { "bomber2_dest.gif_4" };
		spritesDestLeft = new String[] { "bomber2_dest.gif_2" };
		setSpritesImag(spritesImagDown);
		setSpriteDestruccion(spritesDestDown);
		mirando = MirarLado.ABAJO;
		this.altura = ManagerImagen.getImagen(spritesImagUp[0]).getWidth();
		this.anchura = ManagerImagen.getImagen(spritesImagUp[0]).getHeight();
	}

	/**
	 * Recibe un objeto de la clase KeyEvent cuando una tecla ha sido pulsada.
	 */
	public void teclaPulsada(KeyEvent e) {
		/*
		 * En caso de que nos movamos a algún lado el personaje dejará de estar
		 * parado.
		 */
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getDerecha()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getIzquierda()) {
			parado = false;
		}

		/*
		 * Cogemos el código ASCII de la tecla que acciona cada movimiento para
		 * el BombermanBlanco.
		 */
		// Tecla arriba
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()) {
			// Tenemos que restar posición en el eje Y.
			deltaY = -Math.abs(velocidad);
			setSpritesImag(spritesImagUp);
			setSpriteDestruccion(spritesDestUp);
			mirando = MirarLado.ARRIBA;
			// Tecla abajo
		} else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getAbajo()) {
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpritesImag(spritesImagDown);
			setSpriteDestruccion(spritesDestDown);
			mirando = MirarLado.ABAJO;
			// Tecla izquierda
		} else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getIzquierda()) {
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpritesImag(spritesImagLeft);
			setSpriteDestruccion(spritesDestLeft);
			mirando = MirarLado.IZQUIERDA;
			// Tecla derecha
		} else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getDerecha()) {
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpritesImag(spritesImagRight);
			setSpriteDestruccion(spritesDestRight);
			mirando = MirarLado.DERECHA;
			// Tecla bomba
		} else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getBomba()) {
			if (this.getNumBomba() < this.getMaxBomba()) {
				int tempX = ((((int) (this.getPosX() + (this.getAnchura() / 2))) / CASILLA) * CASILLA);
				int tempY = ((((int) (this.getPosY() + (this.getAltura() / 2))) / CASILLA) * CASILLA);
				if (tempY == 0)
					tempY = 33;
				// try {
				// ManagerSonido.playClip("dejar.wav", false);
				// } catch (Exception ex) {
				// ex.printStackTrace();
				// }
				escenario.añadirSprite(new Bomba(escenario, tempX, tempY, this,
						jugador, alcanceMax));
				this.setNumBomba(this.getNumBomba() + 1);
			}
		}
	}

	/**
	 * Recibe un objeto de la clase KeyEvent cuando una tecla ha sido dejada de
	 * pulsar.
	 */
	public void teclaSoltada(KeyEvent e) {
		/*
		 * Si ha sido arriba, abajo, derecha o izquierda entonces el personaje
		 * se queda parado.
		 */
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getDerecha()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos()
						.getIzquierda()) {
			parado = true;
		}

		/*
		 * Como es lógico en caso de que se suelte una tecla se deja de sumar o
		 * restar posiciones.
		 */
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba())
			deltaY = 0;
		else if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getAbajo())
			deltaY = 0;
		else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getIzquierda())
			deltaX = 0;
		else if (e.getKeyCode() == ControlPrincipal.getJugadorDos()
				.getDerecha())
			deltaX = 0;
	}
}
