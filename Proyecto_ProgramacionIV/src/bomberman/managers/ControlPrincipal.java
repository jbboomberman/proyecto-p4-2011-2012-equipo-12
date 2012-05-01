package bomberman.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

import javax.swing.*;
import bomberman.database.AccesoControles;
import bomberman.database.AccesoExtras;
import bomberman.database.AccesoJugador;
import bomberman.database.AccesoNivel;
import bomberman.database.AccesoPunEspe;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuEspe;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.outin.CuentaAtras;
import bomberman.outin.EnvioEmail;
import bomberman.outin.LeerMapa;
import bomberman.outin.ManipuladorFecha;
import bomberman.protagonistas.Sprite;
import bomberman.ventanas.GestorVentana;
import bomberman.ventanas.VentanaCargar;
import bomberman.ventanas.VentanaInicial;
import bomberman.ventanas.VentanaJuego;
import bomberman.ventanas.VentanaNoSuperado;
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
		boolean primeraVez = true;
		while (!pararJuego) {
			if (ventJuego.isVisible()) {
				/*
				 * Como indica este post:
				 * http://www.gamedev.net/topic/261754-javalangillegalstateexception-component-must-have-a-valid-peer/
				 * Sólo hay que crear el BufferStrategy una vez
				 * y cuando la ventanaJuego este visible.
				 */
				if(primeraVez){
					ventJuego.getPanel().createBufferStrategy(2);
					image = ventJuego.getPanel().getBufferStrategy();
					primeraVez = false;
				}
				beforeTime = System.currentTimeMillis();
				if (!ventJuego.getReloj().isTimeFinished()
						&& !(ventJuego.getAcabarPartida())
						&& !(ventJuego.getSuperadoNivel()))
					paintWorld();
				// En caso de que hayamos superado el nivel
				else if (ventJuego.getSuperadoNivel()) {
					// Paramos el reloj
					((CuentaAtras) ventJuego.getReloj()).setParado(true);
					/*
					 * Ponemos superado a falso para que en el siguiente nivel
					 * podamos jugar.
					 */
					ventJuego.setSuperadoNivel(false);
					/*
					 * En caso de que estemos jugando en el modo historia.
					 */
					if (jugadorUno.getModo() == ModoJuego.Historia
							|| jugadorUno.getModo() == ModoJuego.Master) {
						// Hacemos que en la VentanaSuperado aparezca el nick.
						((VentanaSuperado) GestorVentana
								.getVentana(VentanaSuperado.class))
								.setJlNick(ControlPrincipal.getJugadorUno()
										.getNick());
						// Hacemos que en la VentanaSuperado aparezca el nivel.
						((VentanaSuperado) GestorVentana
								.getVentana(VentanaSuperado.class))
								.setJlNivel(String.valueOf(ControlPrincipal
										.getJugadorUno().getNivel()));
						// Hacemos que en la VentanaSuperado aparezca la
						// puntuación.
						((VentanaSuperado) GestorVentana
								.getVentana(VentanaSuperado.class))
								.setJlPuntuacion(String
										.valueOf(ControlPrincipal
												.getJugadorUno()
												.getPuntuacion()));
						//Si hemos llegado al nivel máximo se acaba el juego
						if(jugadorUno.getNivel() == 10)
							((VentanaSuperado)GestorVentana.
									getVentana(VentanaSuperado.class)).setEnabled(false);
						int codPunt;
						if(jugadorUno.getModo() == ModoJuego.Historia)
							codPunt = jugadorUno.getCodPart();
						else
							codPunt = -jugadorUno.getCodJugador();
						// Insertamos la puntuación específica.
						AccesoPunEspe.insertarPunt(new PuntuEspe(AccesoPunEspe
								.getNumPunt(), codPunt,
								jugadorUno.getPuntuNivel(), ManipuladorFecha
										.getFecha(), jugadorUno.getNivel()));
						
						if(jugadorUno.getModo() == ModoJuego.Historia){
						// Hacemos que aparezca la ventana VentanaSuperado
						GestorVentana
								.hacerVisible(VentanaSuperado.class, false);
						}else{
							((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class)).setBotonPasarEnabled(false);
							((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class)).setBotonGuardarEnabled(false);
							((VentanaSuperado)GestorVentana.getVentana(VentanaSuperado.class)).
							setJlPassword(AccesoNivel.getPass(jugadorUno.getNivel()));
							GestorVentana.hacerVisible(VentanaSuperado.class, false);
						}
						
						
					}
					/*
					 * En caso de que se haya acabado el tiempo o nos hayan
					 * matado.
					 */
				} else {
					// Paramos el reloj
					((CuentaAtras) ventJuego.getReloj()).setParado(true);
					// Restamos una vida al jugador.
					jugadorUno.setVidas(jugadorUno.getVidas() - 1);
					/*
					 * En caso de que tengamos alguna vida y estemos jugando en
					 * modo Historia.
					 */
					if (jugadorUno.getVidas() > 0
							&& (jugadorUno.getModo() == ModoJuego.Historia || jugadorUno
									.getModo() == ModoJuego.Master)) {
						// Actualizamos la puntuación
						((VentanaJuego) GestorVentana
								.getVentana(VentanaJuego.class))
								.setPuntuacion();
						// Hacemos visible la ventana VentanaVidaMenos
						GestorVentana.hacerVisible(VentanaVidaMenos.class,
								false);
						/*
						 * Si jugamos modo Multijugador sólo tiene una vida.
						 */
					} else
						// Acabamos partida.
						terminarPartida();
				}

				timeDiff = System.currentTimeMillis() - beforeTime;
				sleepTime = PERIODO - timeDiff; // time left in this loop
				System.out.println(sleepTime);
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
//		 if (timeDiff > 0)
//		 System.out.println(String.valueOf(1000 / timeDiff) + " fps");

		image.show();
	}

	/**
	 * Método que se encarga de introducir las puntuaciones en la BD y todo lo
	 * que tenga que hacer cuando hayamos acabado la partida.
	 */
	public void terminarPartida() {
		// Si estabamos jugando en modo Historia
		if (jugadorUno.getModo() == ModoJuego.Historia
				|| jugadorUno.getModo() == ModoJuego.Master) {
			// Solo guardamos la puntuación general en caso de Historia
			if (jugadorUno.getModo() == ModoJuego.Historia) {
				// Creamos la puntuación general
				PuntuGeneral tempGene = new PuntuGeneral(
						AccesoPuntuGen.getNumPunt(),
						((bomberman.database.Jugador) AccesoJugador.getJugador(
								jugadorUno.getNombre(),
								jugadorUno.getApellidos(),
								jugadorUno.getNick(), jugadorUno.getEmail()))
								.getCod_jugador(), false,
						jugadorUno.getPuntuacion(),
						ManipuladorFecha.getFecha(), 0, -1);
				// Introducimos la puntuación general
				AccesoPuntuGen.insertarPunt(tempGene);
			}
			/*
			 * Esto lo hacemos porque cuando se juega un
			 * nivel específico en modo Master no tienes
			 * partida general.
			 */
			int codPunt;
			if(jugadorUno.getModo() == ModoJuego.Historia)
				codPunt = jugadorUno.getCodPart();
			else
				codPunt = -jugadorUno.getCodJugador();
			// Insertamos la puntuación específica.
			AccesoPunEspe.insertarPunt(new PuntuEspe(AccesoPunEspe
					.getNumPunt(), codPunt,
					jugadorUno.getPuntuNivel(), ManipuladorFecha
							.getFecha(), jugadorUno.getNivel()));
			// Preparamos los datos de la ventana VentanaNoSuperado
			((VentanaNoSuperado) GestorVentana
					.getVentana(VentanaNoSuperado.class)).setJlNick(String
					.valueOf(ControlPrincipal.getJugadorUno().getNick()));
			((VentanaNoSuperado) GestorVentana
					.getVentana(VentanaNoSuperado.class)).setJlNivel(String
					.valueOf(ControlPrincipal.getJugadorUno().getNivel()));
			((VentanaNoSuperado) GestorVentana
					.getVentana(VentanaNoSuperado.class))
					.setJlPuntuacion(String.valueOf(ControlPrincipal
							.getJugadorUno().getPuntuacion()));
			// Ocultamos la ventana VentanaJuego
			GestorVentana.ocultarVentana(VentanaJuego.class);
			// Hacemos visible la ventana VentanaInicial
			GestorVentana.hacerVisible(VentanaInicial.class, false);
			// Hacemos visible la ventana VentanaNoSuperado
			GestorVentana.hacerVisible(VentanaNoSuperado.class, false);
			// Si el jugador quería email se lo enviamos y si estaba jugando modo Historia
			if (AccesoExtras.getExtra("email") && jugadorUno.getModo() == ModoJuego.Historia)
				EnvioEmail.enviarMensaje();
			// Ya la partida ha acabado
			ventJuego.setAcabarPartida(false);
			// En caso de que estuvieramos jugando en modo Multijugador
		} else if (jugadorUno.getModo() == ModoJuego.Multijugador) {
			// Si se murio el Bomberman1 entonces ganó el 2
			if (ventJuego.getBomberman().isSeDestruir()) {
				JOptionPane.showMessageDialog(new JDialog(),
						"¡Ha ganado el jugador 2!", "Ganador",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(new JDialog(),
						"¡Ha ganado el jugador 1!", "Ganador",
						JOptionPane.INFORMATION_MESSAGE);
			}
			// Ocultamos la ventana VentanaJuego
			GestorVentana.ocultarVentana(VentanaJuego.class);
			// Hacemos visible la ventana VentanaInicial
			GestorVentana.hacerVisible(VentanaInicial.class, true);
			// Ya la partida ha acabado
			ventJuego.setAcabarPartida(false);
		} 
	}

	/**
	 * Para cargar una partida que ha sido guardada.
	 * 
	 * @param punt
	 *            - PuntGeneral, la partida que queremos cargar
	 */
	public static void cargarPartida(PuntuGeneral punt) {
		// Cogemos al jugador que guardo la partida.
		bomberman.database.Jugador partJug = AccesoJugador.getJugador(punt
				.getCod_jug());
		/*
		 * Definimos el nuevo jugador que será el que guardó la partida
		 * guardada.
		 */
		jugadorUno.setNombre(partJug.getNomJugador());
		jugadorUno.setApellidos(partJug.getApellJugador());
		jugadorUno.setNick(partJug.getNickJugador());
		jugadorUno.setEmail(partJug.getEmail());
		jugadorUno.setPuntuacion(punt.getPuntu());
		jugadorUno.setPuntuNivel(0);
		jugadorUno.setVidas(punt.getVidas());
		jugadorUno
				.setNivel(punt.getNiv_guar() + 1);
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

		// Creamos el escenario.
		ControlPrincipal.crearEscenario(jugadorUno.getNivel());
		//Ocultamos la ventana cargar
		GestorVentana.ocultarVentana(VentanaCargar.class);
		//Hacemos visible la ventana VentanaJuego
		GestorVentana.hacerVisible(VentanaJuego.class, true);
	}

	/**
	 * Se encarga de crear el escenario del juego cargando
	 * todos los Sprites.
	 * @param numEsce
	 */
	public static void crearEscenario(int numEsce) {
		//Limpiamos el escenario
		((VentanaJuego) GestorVentana.getVentana(VentanaJuego.class))
				.borrarSprites();
		//Leemos el mapa
		Character arrayMapa[][] = LeerMapa.LeerMapaJuego(numEsce);
		//Construimos el escenario
		PrepararEscenario.ColocarMapa(
				(VentanaJuego) GestorVentana.getVentana(VentanaJuego.class),
				arrayMapa, jugadorUno);
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
