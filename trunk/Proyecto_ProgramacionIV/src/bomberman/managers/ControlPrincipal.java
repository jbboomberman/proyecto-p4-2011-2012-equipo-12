package bomberman.managers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.*;

import bomberman.jugador.Jugador;
import bomberman.outin.LeerMapa;
import bomberman.protagonistas.Bomba;
import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Dahl;
import bomberman.protagonistas.Llama;
import bomberman.protagonistas.Minvo;
import bomberman.protagonistas.Muro;
import bomberman.protagonistas.Pildora;
import bomberman.protagonistas.Sprite;
import bomberman.protagonistas.SpriteDinamico;
import bomberman.protagonistas.Valcom;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaInicial;
import bomberman.ventanas.VentanaJuego;

public class ControlPrincipal {

	private static Bomberman bomber;
	private BufferStrategy image;
	private VentanaJuego ventJuego;
	private Jugador jugador;
	private boolean pararJuego;
	private final int PERIODO = 18;
	long beforeTime, timeDiff, sleepTime;

	public ControlPrincipal() {

		pararJuego = false;
		ventJuego = (VentanaJuego) GestorVentana.getVentana(VentanaJuego.class);
		jugador = new Jugador("David", "O", "nose", "no");
		bomber = new Bomberman(ventJuego, 33, 33, jugador);
		ventJuego.setBomberman(bomber);
		ventJuego.setJugador(jugador);
		// Hacer después de que la ventana este activa para que funcione.
		// http://www.gamedev.net/topic/261754-javalangillegalstateexception-component-must-have-a-valid-peer/
		// try {
		// ManagerSonido.playClip("levelintrosong.wav", false);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		GestorVentana.hacerVisible(VentanaInicial.class, true);
		this.game();
	}

	public void game() {
		this.crearEscenario();
		
		while (!pararJuego) {
			if (ventJuego.isVisible()) {
				ventJuego.getPanel().createBufferStrategy(2);
				image = ventJuego.getPanel().getBufferStrategy();
				beforeTime = System.currentTimeMillis();
				if (!ventJuego.getReloj().isTimeFinished()
						&& !(bomber.isSeDestruir()))
					paintWorld();
				else
					terminarPartida(image.getDrawGraphics(), image);

				timeDiff = System.currentTimeMillis() - beforeTime;
				sleepTime = PERIODO - timeDiff; // time left in this loop
				if (sleepTime <= 0) // update/render took longer than period
					sleepTime = 5; // sleep a bit anyway
				try {
					Thread.sleep(sleepTime); // in ms
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void paintWorld() {
		Graphics g = image.getDrawGraphics();
		g.setColor(ventJuego.getBackground());
		g.fillRect(0, 0, ventJuego.getWidth(), ventJuego.getHeight());

		ArrayList<Sprite> tempLista = ventJuego.getLista();
		for (int i = 0; i < tempLista.size(); i++) {
			Sprite tempSpr = tempLista.get(i);
			tempSpr.mover();
			tempSpr.paint((Graphics2D) g);
		}

		ventJuego.getBomberman().mover();
		ventJuego.getBomberman().paint((Graphics2D) g);

//		if (usedTime > 0)
//			System.out.println(String.valueOf(1000 / usedTime) + " fps");
		if (timeDiff > 0)
			System.out.println(String.valueOf(1000 / timeDiff) + " fps");


		image.show();
	}

	public void terminarPartida(Graphics g, BufferStrategy buffer) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sansserif", Font.BOLD, 20));
		g.drawString("Has perdido", ventJuego.getWidth() / 3,
				ventJuego.getHeight() / 3);
		buffer.show();
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		ControlPrincipal prueba = new ControlPrincipal();
	}

	// ESTE MÉTODO ES DE PRUEBA
	private void crearEscenario() {
		Character array[][] = LeerMapa.LeerMapaJuego("mapaMultijugador.txt");
		PrepararEscenario.ColocarMapa(ventJuego, array, jugador);
	}
}
