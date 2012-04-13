package bomberman.protagonistas;

import java.awt.Rectangle;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;

//MUCHA GUARRERIA INTENTAR LIMPIAR
public class SpriteDinamico extends Sprite {

	protected int deltaX;
	protected int deltaY;
	protected int velocidad;
	protected final int CASILLA = 33;
	protected boolean primeraVezMover;
	protected boolean[] sitioLado;

	public SpriteDinamico(Escenario esce, float x, float y, Jugador jug) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
		this.primeraVezMover = true;
	}

	protected boolean seChoca(float x, float y) {
		Rectangle tempRect = getRectangle(this, x, y);
		Rectangle tempRect2;
		
		for (Sprite sprTemp : escenario.getLista()) {
			if (sprTemp != this && !sprTemp.seDestruir) {
				tempRect2 = getRectangle(sprTemp);
				if (tempRect.intersects(tempRect2)) {
					if (this instanceof Llama) {
						if(sprTemp instanceof Muro){
							if (((Muro) sprTemp).isDestructible())
									sprTemp.procDestruccion();
						}
						else if(sprTemp instanceof Enemigo || sprTemp instanceof Bomberman)
							sprTemp.procDestruccion();
														
					} else if (sprTemp instanceof Bomba) {
						if (((Bomba) sprTemp).isPisada())
							return false;
					}else if(this instanceof Bomberman){
						if(sprTemp instanceof Pildora)
							sprTemp.procDestruccion();
						else if(sprTemp instanceof Enemigo)
							this.procDestruccion();
					}else if(this instanceof Enemigo){
						if(sprTemp instanceof Llama)
							this.procDestruccion();
					}
					return true;
				}
			}
		}
		return false;
	}

	public boolean estaInterseccion() {
		if (this.getPosX() % CASILLA == 0 && this.getPosY() % CASILLA == 0)
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

		for (Sprite sprTemp : escenario.getLista()) {
			if (sprTemp != this) {
				tempRect2 = getRectangle(sprTemp);

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
}
