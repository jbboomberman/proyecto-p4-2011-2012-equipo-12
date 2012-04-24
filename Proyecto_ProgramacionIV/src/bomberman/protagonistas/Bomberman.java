package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;
import bomberman.managers.ManagerSonido;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaJuego;
import bomberman.ventanas.VentanaVidaMenos;

public abstract class Bomberman extends SpriteDinamico {

	protected String[] spritesImagUp;
	protected String[] spritesImagDown;
	protected String[] spritesImagRight;
	protected String[] spritesImagLeft;
	protected String[] spritesDestUp;
	protected String[] spritesDestDown;
	protected String[] spritesDestRight;
	protected String[] spritesDestLeft;
	protected boolean parado;
	protected final int ANCH_ALT_MURO = 33;
	protected int numBomba;
	protected int maxBomba;
	protected int alcanceMax;

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
	}

	public void mover() {
		if (isParado()) {
			imagActual = 0;
		} else {
			super.mover();
		}

		if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY
				+ (deltaY * tiempoTranscurrido))) {
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

	public abstract void teclaPulsada(KeyEvent e);

	/**
	 * Este método recibe el KeyEvent que ha recogido la ventana 'VentanaJuego'
	 * y decide que hacer según la tecla soltada.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public abstract void teclaSoltada(KeyEvent e);

	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Pildora)
			spr.procDestruccion();
		else if (spr instanceof Enemigo)
			this.procDestruccion();
		else if (spr instanceof Bomba) {
			if (((Bomba) spr).isPisada())
				return false;
		}else if(spr instanceof Puerta){
			escenario.setAcabarPartida(true);
		}
		return true;
	}
	
	public void procDestruccion(){
		seDestruir = true;
		((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).setAcabarPartida(true);
	}
}
