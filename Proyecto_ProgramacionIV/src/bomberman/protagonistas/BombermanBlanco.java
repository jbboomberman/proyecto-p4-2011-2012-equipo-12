package bomberman.protagonistas;

import java.awt.event.KeyEvent;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

/**
 * Clase para el personaje BombermanBlanco que
 * hereda de Bomberman.
 * @author David
 * @version 1.0
 */
public class BombermanBlanco extends Bomberman {

	/**
	 * Constructor de la clase BombermanBlanco.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug -  Jugador
	 */
	public BombermanBlanco(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		/*
		 * Definimos el nombre de las imágenes de
		 * cada lado.
		 */
		spritesImagUp = new String[] { "bomber_up_down.gif_1",
				"bomber_up_down.gif_2", "bomber_up_down.gif_3" };
		spritesImagDown = new String[] { "bomber_up_down.gif_4",
				"bomber_up_down.gif_5", "bomber_up_down.gif_6" };
		spritesImagRight = new String[] { "bomber_der.gif_1",
				"bomber_der.gif_2", "bomber_der.gif_3" };
		spritesImagLeft = new String[] { "bomber_izq.gif_1",
				"bomber_izq.gif_2", "bomber_izq.gif_3" };
		spritesDestUp = new String[] { "bomber_dest.gif_3" };
		spritesDestDown = new String[] { "bomber_dest.gif_1" };
		spritesDestRight = new String[] { "bomber_dest.gif_4" };
		spritesDestLeft = new String[] { "bomber_dest.gif_2" };
		setSpritesImag(spritesImagDown);
		setSpriteDestruccion(spritesDestDown);
		this.altura = ManagerImagen.getImagen(spritesImagUp[0]).getWidth();
		this.anchura = ManagerImagen.getImagen(spritesImagUp[0]).getHeight();
	}

	/**
	 * Recibe un objeto de la clase KeyEvent cuando
	 * una tecla ha sido pulsada.
	 */
	public void teclaPulsada(KeyEvent e) {
		/*
		 * En caso de que nos movamos a algún lado
		 * el personaje dejará de estar parado.
		 */
		if (e.getKeyCode() == ControlPrincipal.getJugadorUno().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getIzquierda()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getDerecha()) {
			parado = false;
		}

		/*
		 * Cogemos el código ASCII de la tecla que acciona cada
		 * movimiento para el BombermanBlanco.
		 */
		//Tecla arriba
		if (ControlPrincipal.getJugadorUno().getArriba() == e.getKeyCode()){
			// Tenemos que restar posición en el eje Y.
			deltaY = -Math.abs(velocidad);
			setSpritesImag(spritesImagUp);
			setSpriteDestruccion(spritesDestUp);
		//Tecla abajo
		}else if(ControlPrincipal.getJugadorUno().getAbajo() == e.getKeyCode()){
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpritesImag(spritesImagDown);
			setSpriteDestruccion(spritesDestDown);
		//Tecla izquierda
		}else if(ControlPrincipal.getJugadorUno().getIzquierda() == e.getKeyCode()){
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpritesImag(spritesImagLeft);
			setSpriteDestruccion(spritesDestLeft);
		//Tecla derecha
		}else if(ControlPrincipal.getJugadorUno().getDerecha() == e.getKeyCode()){
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpritesImag(spritesImagRight);
			setSpriteDestruccion(spritesDestRight);
		//Tecla bomba
		}else if(ControlPrincipal.getJugadorUno().getBomba() == e.getKeyCode()){
			if (this.getNumBomba() < this.getMaxBomba()) {
				int tempX = ((((int) (this.getPosX() + (this.getAnchura() / 2))) / CASILLA) * CASILLA);
				int tempY = ((((int) (this.getPosY() + (this.getAltura() / 2))) / CASILLA) * CASILLA);
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
	 * Recibe un objeto de la clase KeyEvent cuando
	 * una tecla ha sido dejada de pulsar.
	 */
	public void teclaSoltada(KeyEvent e){
		/*
		 * Si ha sido arriba, abajo, derecha o izquierda
		 * entonces el personaje se queda parado.
		 */
		if (e.getKeyCode() == ControlPrincipal.getJugadorUno().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getIzquierda()
				|| e.getKeyCode() == ControlPrincipal.getJugadorUno().getDerecha()) {
			parado = true;
		}
		/*
		 * Como es lógico en caso de que se suelte una tecla se deja de sumar o
		 * restar posiciones.
		 */
		if(e.getKeyCode() == ControlPrincipal.getJugadorUno().getArriba())
			deltaY = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorUno().getAbajo())
			deltaY = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorUno().getIzquierda())
			deltaX = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorUno().getDerecha())
			deltaX = 0;
	}
}
