package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class BombermanNegro extends Bomberman{
	
	public BombermanNegro(Escenario esce, float x, float y, Jugador jug){
		super(esce, x, y, jug);
		spritesImagUp = new String[] { "bomber2_up_down.gif_1",
				"bomber2_up_down.gif_2", "bomber2_up_down.gif_3" };
		spritesImagDown = new String[] { "bomber2_up_down.gif_4",
				"bomber2_up_down.gif_5", "bomber2_up_down.gif_6" };
		spritesImagRight = new String[] { "bomber2_der.gif_1",
				"bomber2_der.gif_2", "bomber2_der.gif_3" };
		spritesImagLeft = new String[] { "bomber2_izq.gif_1",
				"bomber2_izq.gif_2", "bomber2_izq.gif_3" };
		spritesDestUp = new String[] { "bomber2_dest.gif_3" };
		spritesDestDown = new String[] { "bomber2_dest.gif_1" };
		spritesDestRight = new String[] { "bomber2_dest.gif_4" };
		spritesDestLeft = new String[] { "bomber2_dest.gif_2" };
		setSpritesImag(spritesImagDown);
		setSpriteDestruccion(spritesDestDown);
		this.altura = ManagerImagen.getImagen(spritesImagUp[0]).getWidth();
		this.anchura = ManagerImagen.getImagen(spritesImagUp[0]).getHeight();
	}

	public void teclaPulsada(KeyEvent e){
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getDerecha()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getIzquierda()) {
			parado = false;
		}
		
		if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()){
			// Tenemos que restar posición en el eje Y.
			deltaY = -Math.abs(velocidad);
			setSpritesImag(spritesImagUp);
			setSpriteDestruccion(spritesDestUp);
		}else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getAbajo()){
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpritesImag(spritesImagDown);
			setSpriteDestruccion(spritesDestDown);
		}else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getIzquierda()){
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpritesImag(spritesImagLeft);
			setSpriteDestruccion(spritesDestLeft);
		}else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getDerecha()){
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpritesImag(spritesImagRight);
			setSpriteDestruccion(spritesDestRight);
		}else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getBomba()){
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
	
	public void teclaSoltada(KeyEvent e){
		if (e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getAbajo()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getDerecha()
				|| e.getKeyCode() == ControlPrincipal.getJugadorDos().getIzquierda()) {
			parado = true;
		}
		
		/*
		 * Como es lógico en caso de que se suelte una tecla se deja de sumar o
		 * restar posiciones.
		 */
		if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getArriba())
			deltaY = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getAbajo())
			deltaY = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getIzquierda()) 
			deltaX = 0;
		else if(e.getKeyCode() == ControlPrincipal.getJugadorDos().getDerecha())
			deltaX = 0;
	}
}
