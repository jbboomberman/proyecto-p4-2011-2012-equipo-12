package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Bomberman extends SpriteDinamico {

	private String[] spritesImagUp;
	private String[] spritesImagDown;
	private String[] spritesImagRight;
	private String[] spritesImagLeft;
	private boolean parado;
	private final int ANCH_ALT_MURO = 33;

	public Bomberman(Escenario esce, float x, float y) {
		super(esce, x, y);
		posX = 50;
		posY = 50;
		deltaX = 0;
		deltaY = 0;
		velocidad = 150;
		parado = false;
		spritesImagUp = new String[] { "bomber.gif_9", "bomber.gif_10",
				"bomber.gif_11" };
		spritesImagDown = new String[] { "bomber.gif_13", "bomber.gif_14",
				"bomber.gif_15" };
		spritesImagRight = new String[] { "bomber.gif_5", "bomber.gif_6",
				"bomber.gif_7" };
		spritesImagLeft = new String[] { "bomber.gif_1", "bomber.gif_2",
				"bomber.gif_3" };
		setSpriteNombres(spritesImagDown);
		this.altura = ManagerImagen.getImagen(spritesImagUp[0])
		.getWidth();
		this.anchura = ManagerImagen.getImagen(spritesImagUp[0])
		.getHeight();
	}

	public void mover() {
		if (isParado()) {
			imagActual = 0;
		} else {
			super.mover();
		}
		
		if(!seChoca(posX + (deltaX * tiempoTranscurrido), posY + (deltaY * tiempoTranscurrido))){
			posX += deltaX * tiempoTranscurrido;
			posY += deltaY * tiempoTranscurrido;
		}
	}

	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}

	public void teclaPulsada(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			parado = false;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			// Tenemos que restar posición en el eje Y.
			deltaY = -Math.abs(velocidad);
			setSpriteNombres(spritesImagUp);
			break;
		case KeyEvent.VK_DOWN:
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpriteNombres(spritesImagDown);
			break;
		case KeyEvent.VK_LEFT:
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpriteNombres(spritesImagLeft);
			break;
		case KeyEvent.VK_RIGHT:
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpriteNombres(spritesImagRight);
			break;
		case KeyEvent.VK_SPACE:
			int tempX = (((int) (this.getPosX() + (this.getAnchura() / 2)) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
			int tempY = (((int) (this.getPosY() + (this.getAltura() / 2)) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
			escenario.añadirSprite(new Bomba(escenario, tempX, tempY, this));
			break;
		default:
			break;
		}
	}

	/**
	 * Este método recibe el KeyEvent que ha recogido la ventana 'VentanaJuego'
	 * y decide que hacer según la tecla soltada.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void teclaSoltada(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			parado = true;
		}
		switch (e.getKeyCode()) {
		/*
		 * Como es lógico en caso de que se suelte una tecla se deja de sumar o
		 * restar posiciones.
		 */
		case KeyEvent.VK_UP:
			deltaY = 0;
			break;
		case KeyEvent.VK_DOWN:
			deltaY = 0;
			break;
		case KeyEvent.VK_LEFT:
			deltaX = 0;
			break;
		case KeyEvent.VK_RIGHT:
			deltaX = 0;
			break;
		default:
			break;
		}
	}

}
