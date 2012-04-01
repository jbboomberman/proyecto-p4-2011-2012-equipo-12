package bomberman.managers;

import java.awt.Canvas;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Sprite;

public interface Escenario extends ImageObserver {
	public static final int ANCHURA = 550;
	public static final int ALTURA = 550;
	public static final int VELOCIDAD = 10;

	public Canvas getPanel();
	public Bomberman getBomberman();
	public void añadirSprite(Sprite spr);
	public ArrayList<Sprite> getLista();
}
