package bomberman.protagonistas;

import java.awt.event.KeyEvent;

import bomberman.jugador.Jugador;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;

public class BombermanNegro extends Bomberman{
	
	public BombermanNegro(Escenario esce, float x, float y, Jugador jug){
		super(esce, x, y, jug);
		spritesImagUp = new String[] { "bomber2_up_down.gif_1",
				"bomber2_up_down.gif_2", "bomber2_up_down.gif_3" };
		spritesImagDown = new String[] { "bomber2_up_down.gif_4",
				"bomber2_up_down.gif_5", "bomber2_up_down.gif_6" };
		spritesImagRight = new String[] { "bomber2_der.gif_1",
				"bomber2_der.gif_2", "bomber2_der.gif_3" };
		spritesImagLeft = new String[] { "bomber2_izq.gif_1",
				"bomber2_izq.gif_2", "bomber2_izq.gif_3" };
		spritesDestUp = new String[] { "bomber2_dest.gif_3" };
		spritesDestDown = new String[] { "bomber2_dest.gif_1" };
		spritesDestRight = new String[] { "bomber2_dest.gif_4" };
		spritesDestLeft = new String[] { "bomber2_dest.gif_2" };
		setSpritesImag(spritesImagDown);
		setSpriteDestruccion(spritesDestDown);
		this.altura = ManagerImagen.getImagen(spritesImagUp[0]).getWidth();
		this.anchura = ManagerImagen.getImagen(spritesImagUp[0]).getHeight();
	}

	public void teclaPulsada(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_W
				|| e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_A
				|| e.getKeyCode() == KeyEvent.VK_D) {
			parado = false;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			// Tenemos que restar posición en el eje Y.
			deltaY = -Math.abs(velocidad);
			setSpritesImag(spritesImagUp);
			setSpriteDestruccion(spritesDestUp);
			break;
		case KeyEvent.VK_S:
			// Tenemos que aumentar posición en el eje Y.
			deltaY = Math.abs(velocidad);
			setSpritesImag(spritesImagDown);
			setSpriteDestruccion(spritesDestDown);
			break;
		case KeyEvent.VK_A:
			// Tenemos que restar posición en el eje X.
			deltaX = -Math.abs(velocidad);
			setSpritesImag(spritesImagLeft);
			setSpriteDestruccion(spritesDestLeft);
			break;
		case KeyEvent.VK_D:
			// Tenemos que aumentar posición en el eje X.
			deltaX = Math.abs(velocidad);
			setSpritesImag(spritesImagRight);
			setSpriteDestruccion(spritesDestRight);
			break;
		case KeyEvent.VK_V:
			if (this.getNumBomba() < this.getMaxBomba()) {
				int tempX = ((((int) (this.getPosX() + (this.getAnchura() / 2))) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
				int tempY = ((((int) (this.getPosY() + (this.getAltura() / 2))) / ANCH_ALT_MURO) * ANCH_ALT_MURO);
				if (tempY == 0)
					tempY = 33;
				// try {
				// ManagerSonido.playClip("dejar.wav", false);
				// } catch (Exception ex) {
				// ex.printStackTrace();
				// }
				escenario.añadirSprite(new Bomba(escenario, tempX, tempY, this,
						jugador, alcanceMax));
				this.setNumBomba(this.getNumBomba() + 1);
			}
			break;
		default:
			break;
		}
	}
	
	public void teclaSoltada(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_W
				|| e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_A
				|| e.getKeyCode() == KeyEvent.VK_D) {
			parado = true;
		}
		switch (e.getKeyCode()) {
		/*
		 * Como es lógico en caso de que se suelte una tecla se deja de sumar o
		 * restar posiciones.
		 */
		case KeyEvent.VK_W:
			deltaY = 0;
			break;
		case KeyEvent.VK_S:
			deltaY = 0;
			break;
		case KeyEvent.VK_A:
			deltaX = 0;
			break;
		case KeyEvent.VK_D:
			deltaX = 0;
			break;
		default:
			break;
		}
	}
}
