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

	private static Bomberman prueba;
	private long usedTime;
	private BufferStrategy image;
	private VentanaJuego ventJuego;
	private Jugador jugador;

	public ControlPrincipal() {
		
		ventJuego = (VentanaJuego) GestorVentana.getVentana(VentanaJuego.class);
		jugador = new Jugador("David", "O", "nose", "no");
		prueba = new Bomberman(ventJuego, 33, 33, jugador);
		ventJuego.setBomberman(prueba);
		ventJuego.setJugador(jugador);
		// Hacer despu�s de que la ventana este activa para que funcione.
		// http://www.gamedev.net/topic/261754-javalangillegalstateexception-component-must-have-a-valid-peer/
		GestorVentana.hacerVisible(VentanaInicial.class, true);
		this.game();
	}

	public void game() {
		usedTime = 1000;
		this.crearEscenario();
		while (true) {
			while (ventJuego.isVisible()) {
				ventJuego.getPanel().createBufferStrategy(2);
				image = ventJuego.getPanel().getBufferStrategy();
				long startTime = System.currentTimeMillis();
				if(!ventJuego.getReloj().isTimeFinished())
					paintWorld();
				else
					terminarPartida(image.getDrawGraphics(), image);
				usedTime = System.currentTimeMillis() - startTime;
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
		}
	}

	public void paintWorld() {
		Graphics g = image.getDrawGraphics();
		g.setColor(ventJuego.getBackground());
		g.fillRect(0, 0, ventJuego.getWidth(), ventJuego.getHeight());

		ArrayList<Sprite> tempLista = ventJuego.getLista();
		for(int i = 0; i < tempLista.size(); i++){
			Sprite tempSpr = tempLista.get(i);
			tempSpr.mover();
			tempSpr.paint((Graphics2D) g);
		}

		ventJuego.getBomberman().mover();
		ventJuego.getBomberman().paint((Graphics2D) g);

//		 if (usedTime > 0)
//		System.out.println(String.valueOf(1000 / usedTime) + " fps");
		
		
		image.show();
	}

	public void terminarPartida(Graphics g, BufferStrategy buffer){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sansserif", Font.BOLD, 20));
		g.drawString("Ha terminado la partida por el tiempo", ventJuego.getWidth()/3, ventJuego.getHeight()/3);
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

	// ESTE M�TODO ES DE PRUEBA
	private void crearEscenario() {
		Character array[][] = LeerMapa.LeerMapaJuego("mapa1.txt");
		PrepararEscenario.ColocarMapa(ventJuego, array, jugador);
	}
}