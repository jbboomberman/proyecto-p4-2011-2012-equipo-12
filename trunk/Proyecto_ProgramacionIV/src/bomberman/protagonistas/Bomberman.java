package bomberman.protagonistas;

import java.awt.event.KeyEvent;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaJuego;

/**
 * Esta clase representa al personaje principal del juego.
 * Es abstracta ya que según la tecla que pulsemos
 * se moverá o no. Además los constructores serán distintos
 * dependiendo del tipo de Bomberman.
 * @author David
 * @version 1.0
 */
public abstract class Bomberman extends SpriteDinamico {

	//Array con el nombre de las imagenes hacia arriba.
	protected String[] spritesImagUp;
	//Array con el nombre de las imagenes hacia abajo.
	protected String[] spritesImagDown;
	//Array con el nombre de las imagenes hacia la derecha.
	protected String[] spritesImagRight;
	//Array con el nombre de las imagenes hacia la izquierda.
	protected String[] spritesImagLeft;
	//Array con el nombre de las imagenes muriendose hacia arriba.
	protected String[] spritesDestUp;
	//Array con el nombre de las imagenes muriendose hacia abajo.
	protected String[] spritesDestDown;
	//Array con el nombre de las imagenes muriendose hacia la derecha.
	protected String[] spritesDestRight;
	//Array con el nombre de las imagenes muriendose hacia la izquierda.
	protected String[] spritesDestLeft;
	//Si está parado o no.
	protected boolean parado;
	//Número de bombas que ha tirado sin explotar.
	protected int numBomba;
	//Número máximo de bombas que puede tirar.
	protected int maxBomba;
	//Alcance máximo de las bombas.
	protected int alcanceMax;

	/**
	 * Constructor principal de Bomberman.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug -- Jugador
	 */
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

	/**
	 * Sobreescribe el método mover() de la clase Sprite.
	 */
	public void mover() {
		if (isParado()) {
			imagActual = 0;
		} else {
			super.mover();
		}

		/*
		 * Antes de mover el personaje hay que comprobar
		 * si donde se va a mover hay sitio o no.
		 */
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
		}
		return true;
	}
	
	public void procDestruccion(){
		seDestruir = true;
		((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).setAcabarPartida(true);
	}
}
