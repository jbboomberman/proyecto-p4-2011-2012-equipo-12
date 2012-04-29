package bomberman.protagonistas;

import java.awt.event.KeyEvent;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaJuego;

/**
 * Esta clase representa al personaje principal del juego.
 * Es abstracta ya que seg�n la tecla que pulsemos
 * se mover� o no. Adem�s los constructores ser�n distintos
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
	//Si est� parado o no.
	protected boolean parado;
	//N�mero de bombas que ha tirado sin explotar.
	protected int numBomba;
	//N�mero m�ximo de bombas que puede tirar.
	protected int maxBomba;
	//Alcance m�ximo de las bombas.
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
		parado = true;
		numBomba = 0;
		maxBomba = 1;
		alcanceMax = 4;;
	}

	/**
	 * Sobreescribe el m�todo mover() de la clase Sprite.
	 */
	public void mover() {
		//Si est� parado la imagen no se tiene que mover.
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

	/**
	 * Nos devueleve si est� parado o no
	 * @return boolean - parado
	 */
	public boolean isParado() {
		return parado;
	}

	/**
	 * Pone la variable parado seg�n
	 * el atributo que reciba.
	 * @param parado - boolean
	 */
	public void setParado(boolean parado) {
		this.parado = parado;
	}

	/**
	 * Devueleve el par�metro numBomba.
	 * N�mero de bombas puestas sin explotar.
	 * @return numBomba - int
	 */
	public int getNumBomba() {
		return numBomba;
	}

	/**
	 * Modificamos el par�metro numBomba.
	 * @param numBomba - int
	 */
	public void setNumBomba(int numBomba) {
		this.numBomba = numBomba;
	}

	/**
	 * Devuelve el n�mero m�ximo
	 *  de bombas que puede poner.
	 * @return maxBomba - int
	 */
	public int getMaxBomba() {
		return maxBomba;
	}

	/**
	 * Modifica el n�mero m�ximo de bombas que
	 * puede poner.
	 * @param maxBomba - int
	 */
	public void setMaxBomba(int maxBomba) {
		this.maxBomba = maxBomba;
	}

	/**
	 * Recibe un KeyEvent y hace algo
	 * en caso de que esa tecla active algo o no.
	 * Es abstracto porque seg�n el Bomberman
	 * hace una cosa u otra.
	 * @param e - KeyEvent
	 */
	public abstract void teclaPulsada(KeyEvent e);

	/**
	 * Este m�todo recibe el KeyEvent que ha recogido la ventana 'VentanaJuego'
	 * y decide que hacer seg�n la tecla soltada.
	 * @param e - KeyEvent
	 */
	public abstract void teclaSoltada(KeyEvent e);

	/**
	 * Determina que hacer en caso de que se choque
	 * con alg�n objeto.
	 * @return boolean - Si se ha chocado o no.
	 */
	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Pildora)
			spr.procDestruccion();
		else if (spr instanceof Enemigo)
			this.procDestruccion();
		/*
		 * En caso de que vaya a pisar una bomba
		 * que no ha sido pisada puede pasar.
		 */
		else if (spr instanceof Bomba) {
			if (((Bomba) spr).isPisada())
				return false;
		}
		return true;
	}
	
	/**
	 * Acaba la partida en caso de que tenga que morir.
	 */
	public void procDestruccion(){
		seDestruir = true;
		((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).setAcabarPartida(true);
	}
}
