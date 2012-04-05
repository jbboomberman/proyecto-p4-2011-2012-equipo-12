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
					}else
						sprTemp.procDestruccion();
					return true;
				}
			}
		}
		return false;
	}

	public void mover() {
		super.mover();
	}
}
