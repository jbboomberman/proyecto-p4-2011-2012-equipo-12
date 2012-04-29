package bomberman.protagonistas;

import java.awt.Rectangle;
import java.util.ArrayList;
import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

/**
 * Esta clase representa a los objetos que son capaces
 * de moverse a lo largo de la ventana. Es abastracta
 * ya que el método determinarChoque es abstracto.
 * @author David
 * @version 1.0
 */
public abstract class SpriteDinamico extends Sprite {

	//La velocidad horizontal
	protected int deltaX;
	//La velocidad vertical
	protected int deltaY;
	//La velocidad en general
	protected int velocidad;
	//Para saber si es la primera vez que se mueve
	protected boolean primeraVezMover;
	/*
	 * Contendrá en todo momento el estado
	 * de los alrededores del SpriteDinamico
	 * si estan libre o no: arriba, abajo,
	 * derecha e izquierda.
	 */
	protected boolean[] lados;

	/**
	 * Contructor principal que recibe como parámetros
	 * el escenario, la posición X, la posición Y y los
	 * datos del jugador.
	 * @param esce - Escenario
	 * @param x - float
	 * @param y - float
	 * @param jug - Jugador
	 */
	public SpriteDinamico(Escenario esce, float x, float y, Jugador jug) {
		//Llamamos al constructor padre.
		super(esce, jug);
		this.posX = x;
		this.posY = y;
		this.primeraVezMover = true;
	}

	/**
	 * Este método sirve según los datos que posición que recibimos
	 * si se va a chocar con algún otro elemento de la pantalla.
	 * @param x - float
	 * @param y - float
	 * @return boolean - TRUE, Si se choca. FALSE, si no
	 */
	protected boolean seChoca(float x, float y) {
		/*
		 * Obtenemos un objeto de la clase Rectangulo donde está ahora mismo el
		 * objeto Sprite.
		 */
		Rectangle tempRect = getRectangle(this, x, y);
		Rectangle tempRect2;

		/*
		 * Notese que hacemos el 'for' de la forma clásica porque con el
		 * Iterator nos da error si mientras estamos en el 'for' modificamos la
		 * lista.
		 */
		// for (Sprite sprTemp : escenario.getLista()) {
		for (int i = 0; i < escenario.getLista().size(); i++) {
			Sprite sprTemp = escenario.getLista().get(i);

			/*
			 * Si el otro objeto no es él mismo y no está en proceso de
			 * destrucción.
			 */
			if (sprTemp != this && !sprTemp.seDestruir) {
				tempRect2 = getRectangle(sprTemp);

				// En caso de que los dos se choquen
				if (tempRect.intersects(tempRect2)) {
					
					//ESTO HABRÍA QUE MEJORARLO
					//cuidadoooooooooooooooo
					if (sprTemp instanceof Puerta) {
						ArrayList<Sprite> tempArray = escenario
								.buscarPersonajePos(Muro.class, sprTemp);
						if (tempArray.size() > 0 && (this instanceof Llama)) {
							tempArray.get(0).procDestruccion();
							return true;
						}else if (tempArray.size() > 0){
							return true;
						}else{
							if(this instanceof Bomberman){
								if(escenario.rivalesQuedan() > 0)
									return false;
								else
									escenario.setSuperadoNivel(true);
							}
						}
						return false;
					} else
						return this.determinarChoque(sprTemp);
				}
			}
		}
		return false;
	}

	/**
	 * Para saber si el SpriteDinamico está en una
	 * intersección. Por 'intersección' entendemos
	 * que a posición de X y de Y sean múltiplos
	 * de 32 que son el tamaño y anchura de los muros.
	 * @return boolean - Si esté en una intersección
	 */
	public boolean estaInterseccion() {
		if (((int) this.getPosX()) % CASILLA == 0
				&& ((int) this.getPosY()) % CASILLA == 0)
			return true;
		else
			return false;
	}

	/**
	 * Se supone que está en una intersección.
	 * Nos devuelve si están ocupado los lados derecho, izquierdo, arriba
	 * y abajo.
	 * @return boolean[] - Derecha, izquierda, arriba, abajo.
	 */
	public boolean[] alLado() {
		boolean[] detectLados = new boolean[] { true, true, true, true };
		Rectangle tempRect = getRectangle(this);
		Rectangle tempRect2;

		/*
		 * Tenemos que comprobar todos los sprites del juego
		 * para saber si están al lado del personaje.
		 */
		for(int i = 0; i < escenario.getLista().size(); i++){
			//Si el personaje no es él mismo.
			if (escenario.getLista().get(i) != this) {
				tempRect2 = getRectangle(escenario.getLista().get(i));

				// Parte derecha
				tempRect.setLocation((int) tempRect.getX() + CASILLA,
						(int) tempRect.getY());
				if (tempRect.intersects(tempRect2)) {
					detectLados[0] = false;
				}

				// Parte izquierda
				tempRect.setLocation((int) tempRect.getX() - 2 * CASILLA,
						(int) tempRect.getY());
				if (tempRect.intersects(tempRect2))
					detectLados[1] = false;

				// Parte arriba
				tempRect.setLocation((int) tempRect.getX() + CASILLA,
						(int) tempRect.getY() - CASILLA);
				if (tempRect.intersects(tempRect2)) {
					detectLados[2] = false;
				}

				// Parte abajo
				tempRect.setLocation((int) tempRect.getX(),
						(int) tempRect.getY() + 2 * CASILLA);
				if (tempRect.intersects(tempRect2))
					detectLados[3] = false;
				tempRect.setLocation((int) tempRect.getX(),
						(int) tempRect.getY() - CASILLA);
			}
		}
		return detectLados;
	}

	/**
	 * Dependiendo del tipo de SpriteDinamico hará una
	 * cosa u otra dependienedo de con que se haya
	 * chocado.
	 * @param spr - Sprite
	 * @return boolean - Si se ha chocado o no
	 */
	public abstract boolean determinarChoque(Sprite spr);
}
