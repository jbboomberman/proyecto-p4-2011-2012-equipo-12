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
	protected boolean [] sitioLado;

	public SpriteDinamico(Escenario esce, float x, float y, Jugador jug) {
		super(esce, jug);
		this.posX = x;
		this.posY = y;
		this.primeraVezMover = true;
	}

	protected boolean seChoca(float x, float y) {
		Rectangle tempRect;
		if(this instanceof Bomberman)
			tempRect = new Rectangle((int)x, (int)(y + (this.altura/2)), (int)this.anchura, (int)this.altura/2);
		else
			tempRect = new Rectangle((int)x, (int)y, (int)this.anchura, (int)this.altura);
		for(Sprite sprTemp : escenario.getLista()){
			if(sprTemp != this && !sprTemp.seDestruir){
				Rectangle tempRect2 = new Rectangle((int)sprTemp.posX, (int)sprTemp.posY, 
						(int)sprTemp.anchura, (int)sprTemp.altura);
				if(tempRect.intersects(tempRect2)){
					if(sprTemp instanceof Muro){
						Muro tempMuro = (Muro)sprTemp;
						if(tempMuro.isDestructible())
							sprTemp.procDestruccion();
					}else if(sprTemp instanceof Bomba){
						Bomba tempBomba = (Bomba)sprTemp;
						if(tempBomba.isPisada())
							return false;
						return true;
					}else
						sprTemp.procDestruccion();
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean estaInterseccion(){
		if(this.getPosX()% CASILLA == 0 && this.getPosY()% CASILLA == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Se supone que está en una intersección.
	 * @return
	 */
	public boolean[] alLado(){
		boolean [] arrayTemp = new boolean[]{true, true, true, true};
		Rectangle tempRect;
		if(this instanceof Bomberman)
			tempRect = new Rectangle((int)this.getPosX(), (int)(this.getPosY() + (this.altura/2)), (int)this.anchura, (int)this.altura/2);
		else
			tempRect = new Rectangle((int)this.getPosX(), (int)this.getPosY(), (int)this.anchura, (int)this.altura);
		
		for(Sprite sprTemp : escenario.getLista()){
			if(sprTemp != this){
			Rectangle tempRect2 = new Rectangle((int)sprTemp.posX, (int)sprTemp.posY, 
					(int)sprTemp.anchura, (int)sprTemp.altura);
			//Parte derecha
			tempRect.setLocation((int)tempRect.getX() + CASILLA, (int)tempRect.getY());
			if(tempRect.intersects(tempRect2)){
				arrayTemp[0] = false;
			}
				
			//Parte izquierda
			tempRect.setLocation((int)tempRect.getX() - 2*CASILLA, (int)tempRect.getY());
			if(tempRect.intersects(tempRect2))
				arrayTemp[1] = false;
			
			//Parte arriba
			tempRect.setLocation((int)tempRect.getX() + CASILLA, (int)tempRect.getY() - CASILLA);
			if(tempRect.intersects(tempRect2)){
				arrayTemp[2] = false;
				System.out.println("X: " + tempRect2.x);
				System.out.println("Y: " + tempRect2.y);
			}
			
			//Parte abajo
			tempRect.setLocation((int)tempRect.getX(), (int)tempRect.getY() + 2*CASILLA);
			if(tempRect.intersects(tempRect2))
				arrayTemp[3] = false;
			tempRect.setLocation((int)tempRect.getX(), (int)tempRect.getY() - CASILLA);
		}
		}
		return arrayTemp;
	}

	public void mover() {
		super.mover();
	}
}
