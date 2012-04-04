package bomberman.protagonistas;

import java.awt.Rectangle;

import bomberman.managers.Escenario;

public class SpriteDinamico extends Sprite {

	protected int deltaX;
	protected int deltaY;
	protected int velocidad;

	public SpriteDinamico(Escenario esce) {
		super(esce);
	}

	protected boolean seChoca(float x, float y) {
		Rectangle tempRect = new Rectangle((int)x, (int)(y + (this.altura/2)), (int)this.anchura, (int)this.altura/2);
		for(Sprite sprTemp : escenario.getLista()){
			if(sprTemp != this){
				Rectangle tempRect2 = new Rectangle((int)sprTemp.posX, (int)sprTemp.posY, 
						(int)sprTemp.anchura, (int)sprTemp.altura);
				if(tempRect.intersects(tempRect2)){
					return true;
				}
			}
		}
		return false;
	}

//	protected void rebota(Sprite chocaCon) {
//		if (chocaCon instanceof Muro) {
//			this.setPosX(getPosX());
//		}else if(chocaCon instanceof Pildora){
//			
//		}
////		else if(chocaCon instanceof Enemigo){
////			
////		}
//		
//	}

	public void mover() {
		super.mover();
	}
}
