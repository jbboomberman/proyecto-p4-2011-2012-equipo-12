package bomberman.managers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;

import javax.swing.*;

import bomberman.database.AccesoJugador;
import bomberman.database.AccesoMapa;
import bomberman.database.AccesoPunEspe;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.Mapa;
import bomberman.database.PuntuEspe;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.outin.ConversorFecha;
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
import bomberman.ventanas.VentanaSeleccion;
import bomberman.ventanas.VentanaSuperado;
import bomberman.ventanas.VentanaVidaMenos;

public class ControlPrincipal {

	private BufferStrategy image;
	private VentanaJuego ventJuego;
	private static Jugador jugadorUno;
	private static Jugador jugadorDos;
	private boolean pararJuego;
	private final int PERIODO = 18;
	long beforeTime, timeDiff, sleepTime;

	public ControlPrincipal() {

		pararJuego = false;
		ventJuego = (VentanaJuego) GestorVentana.getVentana(VentanaJuego.class);
		// Pruebaaaaaaaaaaaaaaaaa
		jugadorUno = new Jugador("David", "O", "nose", "no", 1, 0, 1,
				ModoJuego.Historia, AccesoJugador.getNumJug(),
				AccesoPuntuGen.getNumPunt());
		AccesoJugador.insertarJugador(new bomberman.database.Jugador(
				AccesoJugador.getNumJug(), jugadorUno.getNombre(), jugadorUno
						.getApellidos(), jugadorUno.getNick(), jugadorUno
						.getEmail()));

		ventJuego.setJugador(jugadorUno);
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
		while (!pararJuego) {
			if (ventJuego.isVisible()) {
				ventJuego.getPanel().createBufferStrategy(2);
				image = ventJuego.getPanel().getBufferStrategy();
				beforeTime = System.currentTimeMillis();
				if (!ventJuego.getReloj().isTimeFinished()
						&& !(ventJuego.getAcabarPartida())
						&& (!ventJuego.getSuperadoNivel()))
					paintWorld();
				else if (ventJuego.getSuperadoNivel()) {
					GestorVentana.ocultarVentana(VentanaJuego.class);
					if (jugadorUno.getModo() == ModoJuego.Historia) {
						Calendar tempCalendar = Calendar.getInstance();
						String mes, dia;
						if (tempCalendar.get(Calendar.MONTH) + 1 < 10)
							mes = "0" + (tempCalendar.get(Calendar.MONTH) + 1);
						else
							mes = "" + (tempCalendar.get(Calendar.MONTH) + 1);
						if (tempCalendar.get(Calendar.DAY_OF_MONTH) < 10)
							dia = "0" + tempCalendar.get(Calendar.DAY_OF_MONTH);
						else
							dia = "" + tempCalendar.get(Calendar.DAY_OF_MONTH);
						GestorVentana
								.hacerVisible(VentanaSuperado.class, false);
						AccesoPunEspe.insertarPunt(new PuntuEspe(AccesoPunEspe
								.getNumPunt(), jugadorUno.getCodPart(),
								jugadorUno.getPuntuNivel(), ConversorFecha.parsearFecha(new String(
										tempCalendar.get(Calendar.YEAR) + mes
												+ dia)), jugadorUno.getNivel()));
					} else if (jugadorUno.getModo() == ModoJuego.Master) {

					} else {

					}
				} else {
					jugadorUno.setVidas(jugadorUno.getVidas() - 1);
					if (jugadorUno.getVidas() > 0) {
						((VentanaJuego) GestorVentana
								.getVentana(VentanaJuego.class))
								.setPuntuacion();
						GestorVentana.hacerVisible(VentanaVidaMenos.class,
								false);
					} else
						terminarPartida(image.getDrawGraphics(), image);
				}

				timeDiff = System.currentTimeMillis() - beforeTime;
				sleepTime = PERIODO - timeDiff; // time left in this loop
				// System.out.println(sleepTime);
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

		// if (usedTime > 0)
		// System.out.println(String.valueOf(1000 / usedTime) + " fps");
		// if (timeDiff > 0)
		// System.out.println(String.valueOf(1000 / timeDiff) + " fps");

		image.show();
	}

	public void terminarPartida(Graphics g, BufferStrategy buffer) {
		Calendar tempCalendar = Calendar.getInstance();
		String mes, dia;
		if (tempCalendar.get(Calendar.MONTH) + 1 < 10)
			mes = "0" + (tempCalendar.get(Calendar.MONTH) + 1);
		else
			mes = "" + tempCalendar.get(Calendar.MONTH) + 1;
		if (tempCalendar.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + (tempCalendar.get(Calendar.DAY_OF_MONTH));
		else
			dia = "" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		PuntuGeneral tempGene = new PuntuGeneral(AccesoPuntuGen.getNumPunt(),
				((bomberman.database.Jugador) AccesoJugador.getJugador(
						jugadorUno.getNombre(), jugadorUno.getApellidos(),
						jugadorUno.getNick(), jugadorUno.getEmail()))
						.getCod_jugador(), false, jugadorUno.getPuntuacion(),
				ConversorFecha.parsearFecha(new String(tempCalendar.get(Calendar.YEAR) + mes + dia)));
		AccesoPuntuGen.insertarPunt(tempGene);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sansserif", Font.BOLD, 20));
		g.drawString("Has perdido", ventJuego.getWidth() / 3,
				ventJuego.getHeight() / 3);
		buffer.show();
		pararJuego = true;
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		ControlPrincipal prueba = new ControlPrincipal();
	}

	public static void crearEscenario(int numEsce) {
		Character array[][] = LeerMapa.LeerMapaJuego(numEsce);
		PrepararEscenario.ColocarMapa(
				(VentanaJuego) GestorVentana.getVentana(VentanaJuego.class),
				array, jugadorUno);
		// AQUÍ SE CARGA RELOJ
	}

	public static Jugador getJugadorUno() {
		return jugadorUno;
	}

	public static void setJugadorUno(Jugador jug) {
		jugadorUno = jug;
	}
}
