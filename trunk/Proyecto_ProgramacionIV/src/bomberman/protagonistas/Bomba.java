package bomberman.protagonistas;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.managers.Escenario;
import bomberman.ventanas.VentanaJuego;

public class Bomba extends SpriteEstatico {

	private Timer temporizador;

	public Bomba(Escenario esce, float x, float y) {
		super(esce);
		// spritesImag = new String[]{"bomba_1.png", "bomba_2.png",
		// "bomba_3.png"};
		spritesImag = new String[] { "bombs.gif_1", "bombs.gif_2",
				"bombs.gif_3" };
		this.posX = x;
		this.posY = y;
		//¿Se puede automatizar?
		this.altura = 33;
		this.anchura = 33;
		//////
		temporizador = new Timer();
		temporizador.schedule(new LoadExplode(), 3000);
	}

	public void explotar() {
		temporizador.cancel();
		int[] maxLlama = calcularDistancias();
		escenario.añadirSprite(new Llama(escenario, maxLlama, this.getPosX(), this.getPosY()));
	}
	
	class LoadExplode extends TimerTask {

        public void run() {
            explotar();
        }
    }
	
	private int[] calcularDistancias(){
		
		int[]tempArray = new int[4];
		
		/*
		 * Creamos un objeto de la clase Rectangle que nos servirá
		 * para comprobar si hay algún muro o enemigo en el
		 * camino que tendrá que tomar la llama.
		 */
		Rectangle tempRect = new Rectangle(new Dimension(33, 33));

		for (int i = 0; i < tempArray.length; i++) {
			tempRect.x = (int) this.posX;
			tempRect.y = (int) this.posY;

			// Comprobamos la distancia que hay arriba
			if (i == 0) {
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					
					/*
					 * Vamos añadiendo posiciones hasta que
					 * lo encontremos.
					 */
					//AL REVÉS!!!!!!!!!!!11
					tempRect.y += this.getAltura();
					/*
					 * Comprobamos con los Sprites que hay en
					 * la ventana a ver si alguno se interpone
					 * en nuestro camino.
					 */
					for (Sprite sprTemp : escenario.getLista()) {
						Rectangle tempRect2 = new Rectangle((int)sprTemp.getPosX(), (int)sprTemp.getPosY(), (int)sprTemp.getAnchura(), (int)sprTemp.getAltura());
						//Si alguno se interpone
						if (tempRect.intersects(tempRect2)) {
							encon = true;
							//Ya sabemos el tope.
							tempArray[i] = tempRect.y/33;
							break;
						}
					}
				}
			// Comprobamos la distancia que hay abajo
			}else if(i == 1){
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.x -= this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						if (tempRect.intersects(sprTemp.getBounds())) {
							encon = true;
							tempArray[i] = tempRect.x/33;
							break;
						}
					}
				}
			// Comprobamos la distancia que hay a la derecha
			}else if(i == 2){
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.y += this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						if (tempRect.intersects(sprTemp.getBounds())) {
							encon = true;
							tempArray[i] = tempRect.y/33;
							break;
						}
					}
				}
			// Comprobamos la distancia que hay a la  izquierda
			}else{
				boolean encon = false;
				// CUIDADO CON MÁXIMO
				while (!encon) {
					tempRect.y -= this.getAltura();
					for (Sprite sprTemp : escenario.getLista()) {
						if (tempRect.intersects(sprTemp.getBounds())) {
							encon = true;
							tempArray[i] = tempRect.y/33;
							break;
						}
					}
				}
			}
		}
		return tempArray;
	}
}
