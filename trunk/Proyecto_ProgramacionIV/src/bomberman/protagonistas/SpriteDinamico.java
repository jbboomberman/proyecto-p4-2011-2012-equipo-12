package bomberman.protagonistas;

import java.awt.Rectangle;
import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

//MUCHA GUARRERIA INTENTAR LIMPIAR
public abstract class SpriteDinamico extends Sprite {

	protected int deltaX;
	protected int deltaY;
	protected int velocidad;
	protected final int CASILLA = 32;
	protected boolean primeraVezMover;
	protected boolean[] sitioLado;

	public SpriteDinamico(Escenario esce, float x, float y, Jugador jug) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
		this.primeraVezMover = true;
	}

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

	public boolean estaInterseccion() {
		if (((int) this.getPosX()) % CASILLA == 0
				&& ((int) this.getPosY()) % CASILLA == 0)
			return true;
		else
			return false;
	}

	/**
	 * Se supone que está en una intersección.
	 * 
	 * @return
	 */
	public boolean[] alLado() {
		boolean[] detectLados = new boolean[] { true, true, true, true };
		Rectangle tempRect = getRectangle(this);
		Rectangle tempRect2;

		for(int i = 0; i < escenario.getLista().size(); i++){
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

	public abstract boolean determinarChoque(Sprite spr);
}
