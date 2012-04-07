package bomberman.protagonistas;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Dahl extends Enemigo {

	public Dahl(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		deltaX = 10;
		deltaY = 10;
		velocidad = 10;
		this.puntos = 200;
		spritesImag = new String []{"dahl.gif_1", "dahl.gif_2", "dahl.gif_3"};
		spritesImagDest = new String []{"dahl.gif_4", "dahl.gif_5", "destruccion.png_1", "destruccion.png_2", 
				"destruccion.png_3", "destruccion.png_4", "destruccion.png_5"};
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
	
	public void mover(){
		super.mover();
//		boolean[]tempbool = this.alLado();
//		if(this.estaInterseccion()){
//			if(!seChoca(posX + (deltaX * tiempoTranscurrido), posY)){
//				posX += deltaX * tiempoTranscurrido;
//			}
//		}else{
//			if(deltaX > 0){
//				if(!seChoca(posX + (deltaX * tiempoTranscurrido), posY)){
//					posX += deltaX * tiempoTranscurrido;
//				}else{
//					deltaX = -(velocidad);
//					if(!seChoca(posX + (deltaX * tiempoTranscurrido), posY))
//						posX += deltaX * tiempoTranscurrido;
//				}
//			}
//		}
			
	}
}
