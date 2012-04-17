package bomberman.protagonistas;

import java.awt.Graphics2D;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class Llama extends SpriteDinamico {

	private int tipo;

	public Llama(Escenario esce, float x, float y, Jugador jug, int tip) {
		super(esce, x, y, jug);
		this.escenario = esce;
		this.tipo = tip;
		this.velocidadPic = 10;
		
		switch(tipo){
		
		//Centro
		case 1:
			spritesImag = new String []{"llama.gif_1", "llama.gif_2", "llama.gif_3", "llama.gif_4"};
			break;
		//Intermedio - vertical
		case 2:
			spritesImag = new String []{"llama.gif_21", "llama.gif_22", "llama.gif_23", "llama.gif_24"};
			break;
		//Intermedio - horizontal
		case 3:
			spritesImag = new String []{"llama.gif_9", "llama.gif_10", "llama.gif_11", "llama.gif_12"};
			break;
		//Punta - derecha
		case 4:
			spritesImag = new String []{"llama.gif_17", "llama.gif_18", "llama.gif_19", "llama.gif_20"};
			break;
		//Punta - izquierda
		case 5:
			spritesImag = new String []{"llama.gif_13", "llama.gif_14", "llama.gif_15", "llama.gif_16"};
			break;
		//Punta - arriba
		case 6:
			spritesImag = new String []{"llama.gif_25", "llama.gif_26", "llama.gif_27", "llama.gif_28"};
			break;
		//Punta - abajo
		case 7:
			spritesImag = new String []{"llama.gif_29", "llama.gif_30", "llama.gif_31", "llama.gif_32"};
			break;
		}
		this.anchura = ManagerImagen.getImagen(spritesImag[0]).getWidth();
		this.altura = ManagerImagen.getImagen(spritesImag[0]).getHeight();
	}
	
	public void mover(){
		super.mover();
		seChoca(this.getPosX(), this.getPosY());
		if(imagActual == (spritesImag.length - 1))
			this.destruir();
	}
	
	public boolean determinarChoque(Sprite spr){
		if(spr instanceof Muro){
			if (((Muro) spr).isDestructible())
					spr.procDestruccion();
		}
		else if(spr instanceof Enemigo || spr instanceof Bomberman)
			spr.procDestruccion();
		return true;
	}
}
