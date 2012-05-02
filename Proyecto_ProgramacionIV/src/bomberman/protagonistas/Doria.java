package bomberman.protagonistas;

import java.util.Collections;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaJuego;

public class Doria extends Enemigo {

	public Doria(Escenario esce, float x, float y, Jugador jug) {
		super(esce, x, y, jug);
		this.puntos = 2000;
		this.posX = x;
		this.posY = y;
		deltaX = 100;
		deltaY = 100;
		velocidad = 100;
		spritesImag = new String[] { "doria.gif_1", "doria.gif_2",
				"doria.gif_3" };
		spritesImagDest = new String[] { "doria_dest.gif_1",
				"destruccion.png_1", "destruccion.png_2", "destruccion.png_3",
				"destruccion.png_4", "destruccion.png_5" };
		this.anchura = CASILLA;
		this.altura = CASILLA;
		aleatorizacion.add(velocidad);
		aleatorizacion.add(-velocidad);
	}

	public void mover() {
		super.mover();

		if (!seChoca(posX + (deltaX * tiempoTranscurrido), posY
				+ (deltaY * tiempoTranscurrido))) {
			posX += deltaX * tiempoTranscurrido;
			posY += deltaY * tiempoTranscurrido;
		}

		/*
		 * En caso de que este en una intersección podemos cambiar el rumbo.
		 */
		if (estaInterseccion()) {
			// Miramos que lados tenemos libres.
			lados = this.alLado();
			
			float posRelaX = ((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).
			getBomberman().getPosX() - this.posX;
			float posRelaY = ((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).
			getBomberman().getPosY() - this.posY;
			
			
			//Izquierda
			if(posRelaX <= 0 && lados[1]){
				deltaX = - velocidad;
			//Izquierda no poder
			}else if(posRelaX <= 0 && !lados[1]){
				if(!lados[2] && !lados[3]){
					if(lados[0])
						deltaX = velocidad;
					else
						deltaX = 0;
				}
			//Derecha
			}else if(posRelaX > 0 && lados[0]){
				deltaX = velocidad;
			}else if(posRelaX > 0 && !lados[0]){
				if(!lados[2] && !lados[3]){
					if(lados[1])
						deltaX = - velocidad;
					else
						deltaX = 0;
				}
			}
			
			
			//Abajo
			if(posRelaY >= 0 && lados[3]){
				deltaY = velocidad;
			//Izquierda no poder
			}else if(posRelaY >= 0 && !lados[3]){
				if(!lados[0] && !lados[1]){
					if(lados[2])
						deltaY = - velocidad;
					else
						deltaY = 0;
				}
			//Arriba
			}else if(posRelaY < 0 && lados[2]){
				deltaY = - velocidad;
			}else if(posRelaY < 0 && !lados[2]){
				if(!lados[0] && !lados[1]){
					if(lados[3])
						deltaX = velocidad;
					else
						deltaX = 0;
				}
			}
			
			if(deltaX != 0 && deltaY != 0){
				if(Math.abs(posRelaX) > Math.abs(posRelaY)){
					deltaY = 0;
				}else{
					deltaX = 0;
				}
			}
		}
	}

	public boolean determinarChoque(Sprite spr) {
		if (spr instanceof Llama)
			this.procDestruccion();
		else if (spr instanceof Bomberman)
			spr.procDestruccion();
		else if (spr instanceof Muro) {
			if (((Muro) spr).isDestructible())
				return false;
		}
		return true;
	}
}
