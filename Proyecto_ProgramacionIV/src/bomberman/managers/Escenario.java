package bomberman.managers;

import java.awt.Canvas;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JPanel;

import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Sprite;

public interface Escenario extends ImageObserver {
	public static final int ANCHURA = 660;
	public static final int ALTURA = 660;
	public static final int VELOCIDAD = 10;

	public Canvas getPanel();

	public Bomberman getBomberman();

	public void aņadirSprite(Sprite spr);

	public ArrayList<Sprite> getLista();

	public void setPuntuacion();
	
	public void acabarPartida();
}
