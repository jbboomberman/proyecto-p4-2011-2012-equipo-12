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

import bomberman.database.AccesoControles;
import bomberman.database.AccesoExtras;
import bomberman.database.AccesoJugador;
import bomberman.database.AccesoMapa;
import bomberman.database.AccesoPunEspe;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.Mapa;
import bomberman.database.PuntuEspe;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.outin.EnvioEmail;
import bomberman.outin.LeerMapa;
import bomberman.outin.ManipuladorFecha;
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
import bomberman.ventanas.VentanaCargar;
import bomberman.ventanas.VentanaInicial;
import bomberman.ventanas.VentanaJuego;
import bomberman.ventanas.VentanaNoSuperado;
import bomberman.ventanas.VentanaSeleccion;
import bomberman.ventanas.VentanaSuperado;
import bomberman.ventanas.VentanaVidaMenos;

public class ControlPrincipal {
	private BufferStrategy image;
	private VentanaJuego ventJuego;
	private static Jugador jugadorUno;
	private static Jugador jugadorDos;
	private static boolean pararJuego;
	private final int PERIODO = 18;
	private long beforeTime, timeDiff, sleepTime;

	public ControlPrincipal() {
		pararJuego = false;
		ventJuego = (VentanaJuego) GestorVentana.getVentana(VentanaJuego.class);
		jugadorUno = new Jugador();

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
					ventJuego.setSuperadoNivel(false);
					ventJuego.borrarSprites();
					if (jugadorUno.getModo() == ModoJuego.Historia) {
						((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class))
						.setJlNick(ControlPrincipal.getJugadorUno().getNick());
						((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class))
						.setJlNivel(String.valueOf(ControlPrincipal.getJugadorUno().getNivel()));
						((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class))
						.setJlPuntuacion(String.valueOf(ControlPrincipal.getJugadorUno().getPuntuacion()));
						GestorVentana
								.hacerVisible(VentanaSuperado.class, false);
						AccesoPunEspe.insertarPunt(new PuntuEspe(AccesoPunEspe
								.getNumPunt(), jugadorUno.getCodPart(),
								jugadorUno.getPuntuNivel(), ManipuladorFecha.getFecha()
								, jugadorUno.getNivel()));
					} else if (jugadorUno.getModo() == ModoJuego.Master) {

					} else {

					}
				} else {
					jugadorUno.setVidas(jugadorUno.getVidas() - 1);
					if (jugadorUno.getVidas() > 0 && (!jugadorUno.getModo().equals(ModoJuego.Multijugador))) {
						((VentanaJuego) GestorVentana
								.getVentana(VentanaJuego.class))
								.setPuntuacion();
						GestorVentana.hacerVisible(VentanaVidaMenos.class,
								false);
					} else
						terminarPartida();
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

		// if (usedTime > 0)
		// System.out.println(String.valueOf(1000 / usedTime) + " fps");
		// if (timeDiff > 0)
		// System.out.println(String.valueOf(1000 / timeDiff) + " fps");

		image.show();
	}

	public void terminarPartida() {
		PuntuGeneral tempGene = new PuntuGeneral(AccesoPuntuGen.getNumPunt(),
				((bomberman.database.Jugador) AccesoJugador.getJugador(
						jugadorUno.getNombre(), jugadorUno.getApellidos(),
						jugadorUno.getNick(), jugadorUno.getEmail()))
						.getCod_jugador(), false, jugadorUno.getPuntuacion(),
				ManipuladorFecha.getFecha(), 0);
		AccesoPuntuGen.insertarPunt(tempGene);
		ventJuego.borrarSprites();
		((VentanaNoSuperado)GestorVentana.getVentana(VentanaNoSuperado.class))
		.setJlNick(String.valueOf(ControlPrincipal.getJugadorUno().getNick()));
		((VentanaNoSuperado)GestorVentana.getVentana(VentanaNoSuperado.class))
		.setJlNivel(String.valueOf(ControlPrincipal.getJugadorUno().getNivel()));
		((VentanaNoSuperado)GestorVentana.getVentana(VentanaNoSuperado.class))
		.setJlPuntuacion(String.valueOf(ControlPrincipal.getJugadorUno().getPuntuacion()));
		GestorVentana.ocultarVentana(VentanaJuego.class);
		GestorVentana.hacerVisible(VentanaInicial.class, false);
		GestorVentana.hacerVisible(VentanaNoSuperado.class, false);
		EnvioEmail.enviarMensaje();
		pararJuego = true;
	}

	/**
	 * Para cargar una partida que ha sido guardada.
	 * @param punt - PuntGeneral
	 */
	public static void cargarPartida(PuntuGeneral punt){
		//Cogemos al jugador que guardo la partida.
		bomberman.database.Jugador partJug = AccesoJugador.getJugador(punt.getCod_jug());
		
		/*
		 * Definimos el nuevo jugador que será el que guardó
		 * la partida guardada.
		 */
		jugadorUno.setNombre(partJug.getNomJugador());
		jugadorUno.setApellidos(partJug.getApellJugador());
		jugadorUno.setNick(partJug.getNickJugador());
		jugadorUno.setEmail(partJug.getEmail());
		jugadorUno.setPuntuacion(punt.getPuntu());
		jugadorUno.setPuntuNivel(0);
		jugadorUno.setVidas(punt.getVidas());
		jugadorUno.setNivel(AccesoPunEspe.getNivelMasAlto(punt.getCod_punt()) + 1);
		jugadorUno.setModo(ModoJuego.Historia);
		jugadorUno.setCodJugador(punt.getCod_jug());
		jugadorUno.setCodPart(punt.getCod_punt());
		jugadorUno.setArriba(AccesoControles.getControl("ARRIBA", 1));
		jugadorUno.setAbajo(AccesoControles.getControl("ABAJO", 1));
		jugadorUno.setDerecha(AccesoControles.getControl("DERECHA", 1));
		jugadorUno.setIzquierda(AccesoControles.getControl("IZQUIERDA", 1));
		jugadorUno.setBomba(AccesoControles.getControl("BOMBA", 1));
		jugadorUno.setSonido(AccesoExtras.getExtra("sonido"));
		jugadorUno.setQuiereEmail(AccesoExtras.getExtra("email"));
		
		//Creamos el escenario.
		ControlPrincipal.crearEscenario(AccesoPunEspe.getNivelMasAlto(punt.getCod_punt()) + 1);
		GestorVentana.ocultarVentana(VentanaCargar.class);
		GestorVentana.hacerVisible(VentanaJuego.class, true);
	}
	public static void crearEscenario(int numEsce) {
		((VentanaJuego)GestorVentana.getVentana(VentanaJuego.class)).borrarSprites();
		Character array[][] = LeerMapa.LeerMapaJuego(numEsce);
		PrepararEscenario.ColocarMapa(
				(VentanaJuego) GestorVentana.getVentana(VentanaJuego.class),
				array, jugadorUno);
	}

	public static Jugador getJugadorUno() {
		return jugadorUno;
	}

	public static void setJugadorUno(Jugador jug) {
		jugadorUno = jug;
	}
	
	public static Jugador getJugadorDos() {
		return jugadorDos;
	}

	public static void setJugadorDos(Jugador jug) {
		jugadorDos = jug;
	}

	public static boolean isPararJuego() {
		return pararJuego;
	}

	public static void setPararJuego(boolean pararJuego) {
		ControlPrincipal.pararJuego = pararJuego;
	}
	
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		ControlPrincipal prueba = new ControlPrincipal();
	}
}
