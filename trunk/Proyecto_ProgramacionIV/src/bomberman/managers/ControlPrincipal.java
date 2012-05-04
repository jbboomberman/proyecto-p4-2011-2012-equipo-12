package bomberman.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
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

/**
 * Clase que se encargará de controlor todo el juego. Desde esta clase se inicia
 * el juego y es aquí donde está el GameLoop.
 * @author David
 * @version 1.0
 */
public class ControlPrincipal {
	//BufferStrategy para hace doble buffer
	private BufferStrategy image;
	//La ventana donde se pintará
	private VentanaJuego ventJuego;
	//El primer jugadir
	private static Jugador jugadorUno;
	//El segundo jugador
	private static Jugador jugadorDos;
	//Variable para parar el juego
	private static boolean pararJuego;
	//Periodo que queremos que dure cada bucle
	private final int PERIODO = 18;
	//Varias mediciones de tiempo
	private long beforeTime, timeDiff, sleepTime;

	/**
	 * Constructor principal de la clase ControlPrincipal
	 */
	public ControlPrincipal() {
		pararJuego = false;
		//Recibimos la ventana
		ventJuego = (VentanaJuego) GestorVentana.getVentana(VentanaJuego.class);
		//Creamos al jugador uno
		jugadorUno = new Jugador();
		GestorVentana.hacerVisible(VentanaInicial.class, true);
		this.game();
	}

	public void game() {
		boolean primeraVez = true;
		while (!pararJuego) {
			if (ventJuego.isVisible()) {
				/*
				 * Como indica este post:
				 * http://www.gamedev.net/topic/261754-javalangillegalstateexception
				 * -component-must-have-a-valid-peer/ Sólo hay que crear el
				 * BufferStrategy una vez y cuando la ventanaJuego este visible.
				 */
				if (primeraVez) {
					ventJuego.getPanel().createBufferStrategy(2);
					image = ventJuego.getPanel().getBufferStrategy();
					primeraVez = false;
				}
				//Calculamos el tiempo desde que empieza el bucle
				beforeTime = System.currentTimeMillis();
				/*
				 * En caso de que el reloj no haya acabado, la partida
				 * no haya acabado y no se haya superado el nivel
				 * seguimos pintando la pantalla.
				 */
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
						//Hacemos visible el password
						((VentanaSuperado) GestorVentana
								.getVentana(VentanaSuperado.class))
								.setJlPassword(AccesoNivel
										.getPass(jugadorUno.getNivel()));
						// Si hemos llegado al nivel máximo se acaba el juego
						if (jugadorUno.getNivel() == 10){
							((VentanaSuperado) GestorVentana
									.getVentana(VentanaSuperado.class))
									.setBotonGuardarEnabled(false);
							((VentanaSuperado) GestorVentana
									.getVentana(VentanaSuperado.class))
									.setBotonPasarEnabled(false);
						}
						/*
						 * Como hemos comentado anteiormente si es puntación
						 * específica jugada en modo Master no tendrá Puntuación
						 * General por lo que apuntaremos el código del jugador
						 * y no el de la PuntuaciónGeneral.
						 */
						int codPunt;
						if (jugadorUno.getModo() == ModoJuego.Historia)
							codPunt = jugadorUno.getCodPart();
						else
							codPunt = -jugadorUno.getCodJugador();
						// Insertamos la puntuación específica.
						AccesoPunEspe.insertarPunt(new PuntuEspe(AccesoPunEspe
								.getNumPunt(), codPunt, jugadorUno
								.getPuntuNivel(), ManipuladorFecha.getFecha(),
								jugadorUno.getNivel()));

						if (jugadorUno.getModo() == ModoJuego.Historia) {
							// Hacemos que aparezca la ventana VentanaSuperado
							GestorVentana.hacerVisible(VentanaSuperado.class,
									false);
						} else {
							((VentanaSuperado) GestorVentana
									.getVentana(VentanaSuperado.class))
									.setBotonPasarEnabled(false);
							((VentanaSuperado) GestorVentana
									.getVentana(VentanaSuperado.class))
									.setBotonGuardarEnabled(false);
							
							GestorVentana.hacerVisible(VentanaSuperado.class,
									false);
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
					jugadorUno.setPuntuNivel(0);
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

				/*
				 * Calculamos el tiempo que ha durado el bucle.
				 */
				timeDiff = System.currentTimeMillis() - beforeTime;
				//Miramos si ha tardado más o menos que la diferencia
				sleepTime = PERIODO - timeDiff; 
				//Si ha tardado más entonces descansamos 5 ms
				if (sleepTime <= 0) 
					sleepTime = 5; 
				//Si no lo que nos haya sobrado
				try {
					Thread.sleep(sleepTime); // in ms
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			/*
			 * Si no entra en el GameLoop descansamos 1 ms para
			 * no machacar el procesador.
			 */
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Pinta toda la pantalla del videojuego con los
	 * gráficos.
	 */
	public void paintWorld() {
		//Obtenemos los gráficos
		Graphics g = image.getDrawGraphics();
		//El fondo lo pintamos del mismo color.
		g.setColor(ventJuego.getBackground());
		g.fillRect(0, 0, ventJuego.getWidth(), ventJuego.getHeight());

		/*
		 * Vamos pintando uno a uno todos los personajes.
		 * No lo hacemos con un for-each porque en caso de
		 * que borremos un personajes mientras estamos
		 * iterando nos puede dar error.
		 */
		ArrayList<Sprite> tempLista = ventJuego.getLista();
		for (int i = 0; i < tempLista.size(); i++) {
			Sprite tempSpr = tempLista.get(i);
			//Movemos
			tempSpr.mover();
			//Pintamos
			tempSpr.paint((Graphics2D) g);
		}
		//Pintamos todo
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
			 * Esto lo hacemos porque cuando se juega un nivel específico en
			 * modo Master no tienes partida general.
			 */
			int codPunt;
			if (jugadorUno.getModo() == ModoJuego.Historia)
				codPunt = jugadorUno.getCodPart();
			else
				codPunt = -jugadorUno.getCodJugador();
			// Insertamos la puntuación específica.
			AccesoPunEspe.insertarPunt(new PuntuEspe(
					AccesoPunEspe.getNumPunt(), codPunt, jugadorUno
							.getPuntuNivel(), ManipuladorFecha.getFecha(),
					jugadorUno.getNivel()));
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
			// Si el jugador quería email se lo enviamos y si estaba jugando
			// modo Historia
			if (AccesoExtras.getExtra("email")
					&& jugadorUno.getModo() == ModoJuego.Historia)
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
		jugadorUno.setNivel(punt.getNiv_guar() + 1);
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
		// Ocultamos la ventana cargar
		GestorVentana.ocultarVentana(VentanaCargar.class);
		// Hacemos visible la ventana VentanaJuego
		GestorVentana.hacerVisible(VentanaJuego.class, true);
	}

	/**
	 * Se encarga de crear el escenario del juego cargando todos los Sprites.
	 * 
	 * @param numEsce
	 */
	public static void crearEscenario(int numEsce) {
		// Limpiamos el escenario
		((VentanaJuego) GestorVentana.getVentana(VentanaJuego.class))
				.borrarSprites();
		// Leemos el mapa
		Character arrayMapa[][] = LeerMapa.LeerMapaJuego(numEsce);
		// Construimos el escenario
		PrepararEscenario.ColocarMapa(
				(VentanaJuego) GestorVentana.getVentana(VentanaJuego.class),
				arrayMapa, jugadorUno);
	}

	/**
	 * Devuelve el primer jugador
	 * @return jugadorUno - Jugador
	 */
	public static Jugador getJugadorUno() {
		return jugadorUno;
	}

	/**
	 * Modificia el primer jugador
	 * @param jug - Jugador
	 */
	public static void setJugadorUno(Jugador jug) {
		jugadorUno = jug;
	}

	/**
	 * Devuelve el segundo jugador
	 * @return jugadorDos - Jugador
	 */
	public static Jugador getJugadorDos() {
		return jugadorDos;
	}

	/**
	 * Modifica el segundo jugador.
	 * @param jug - Jugador
	 */
	public static void setJugadorDos(Jugador jug) {
		jugadorDos = jug;
	}

	/**
	 * Devuelve el valor de la variable pararJuego
	 * @return pararJuego
	 */
	public static boolean isPararJuego() {
		return pararJuego;
	}
	
	/**
	 * Modifica el valor de pararJuego
	 * @param pararJuego - boolean
	 */
	public static void setPararJuego(boolean pararJuego) {
		ControlPrincipal.pararJuego = pararJuego;
	}

	/**
	 * Main donde se  arranca el juego.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		ControlPrincipal prueba = new ControlPrincipal();
	}
}
