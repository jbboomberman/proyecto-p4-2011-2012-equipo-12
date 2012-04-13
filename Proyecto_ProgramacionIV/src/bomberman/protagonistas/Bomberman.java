package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Bomberman extends SpriteDinamico {

	private String[] spritesImagUp;
	private String[] spritesImagDown;
	private String[] spritesImagRight;
	private String[] spritesImagLeft;
	private String[] spritesDestUp;
	private String[] spritesDestDown;
	private String[] spritesDestRight;
	private String[] spritesDestLeft;
	private boolean parado;
	private final int ANCH_ALT_MURO = 33;
	private int numBomba;
	private int maxBomba;
	private int alcanceMax;

	public Bomberman(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 0;
		deltaY = 0;
		velocidad = 150;
		velocidadPic = 25;
		parado = false;
		numBomba = 0;
		maxBomba = 1;
		alcanceMax = 4;
		spritesImagUp = new String[] { "bomber.gif_7", "bomber.gif_8",
				"bomber.gif_9" };
		spritesImagDown = new String[] { "bomber.gif_10", "bomber.gif_11",
				"bomber.gif_12" };
		spritesImagRight = new String[] { "bomber.gif_4", "bomber.gif_5",
				"bomber.gif_6" };
		spritesImagLeft = new String[] { "bomber.gif_1", "bomber.gif_2",
				"bomber.gif_3" };
		spritesDestUp = new String[]{"bomber_dest.gif_3"};
		spritesDestDown = new String[]{"bomber_dest.gif_1"};
		spritesDestRight = new String[]{"bomber_dest.gif_4"};
		spritesDestLeft = new String[]{"bomber_dest.gif_2"};
		setSpriteNombres(spritesImagDown);
		setSpriteDestruccion(spritesDestDown);
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

	public int getNumBomba() {
		return numBomba;
	}

	public void setNumBomba(int numBomba) {
		this.numBomba = numBomba;
	}

	public int getMaxBomba() {
		return maxBomba;
	}

	public void setMaxBomba(int maxBomba) {
		this.maxBomba = maxBomba;
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
			setSpriteDestruccion(spritesDestUp);
			break;
		case KeyEvent.VK_DOWN:
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpriteNombres(spritesImagDown);
			setSpriteDestruccion(spritesDestDown);
			break;
		case KeyEvent.VK_LEFT:
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpriteNombres(spritesImagLeft);
			setSpriteDestruccion(spritesDestLeft);
			break;
		case KeyEvent.VK_RIGHT:
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpriteNombres(spritesImagRight);
			setSpriteDestruccion(spritesDestRight);
			break;
		case KeyEvent.VK_SPACE:
			if(this.getNumBomba() < this.getMaxBomba()){
				int tempX = (((int) (this.getPosX() + (this.getAnchura() / 2)) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
				int tempY = (((int) (this.getPosY() + (this.getAltura() / 2)) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
				if(tempY == 0)
					tempY = 33;
				escenario.añadirSprite(new Bomba(escenario, tempX, tempY, this, jugador, alcanceMax));
				this.setNumBomba(this.getNumBomba() + 1);
			}
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
