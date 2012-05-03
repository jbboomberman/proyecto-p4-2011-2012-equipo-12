package bomberman.ventanas;

import javax.swing.*;
import bomberman.database.AccesoJugador;
import bomberman.database.AccesoNivel;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.outin.CuentaAtras;
import bomberman.outin.ManipuladorFecha;
import bomberman.outin.RelojException;
import bomberman.protagonistas.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * La clase VentanaJuego es donde se desarrolla lo que es el juego en sí.
 * Implementa el inteface KeyListener para poder escuchar los eventos generados
 * por el teclado. Hereda de JFrame.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaJuego extends JFrame implements KeyListener, Escenario {

	private static final long serialVersionUID = -7461719037402108362L;
	// Canvas donde se pintarán los personajes
	private Canvas canPintar;
	private Bomberman bomber1;
	private Bomberman bomber2;
	// ArrayList donde se guardarán los Sprites
	private static ArrayList<Sprite> arLista;
	private JPanel panelMarcador;
	private Jugador jugador;
	// El reloj cuenta atrás
	private CuentaAtras tiempo;
	private JLabel jlText;
	private boolean parado;
	private boolean finalizar;
	private boolean superadoNivel;

	/**
	 * Constructor principal de la ventana.
	 */
	public VentanaJuego() {

		finalizar = false;
		superadoNivel = false;
		arLista = new ArrayList<Sprite>();
		canPintar = new Canvas();
		canPintar.setSize(640, 640);
		panelMarcador = new JPanel();
		jugador = ControlPrincipal.getJugadorUno();
		try {
			parado = true;
			tiempo = new CuentaAtras(0, 5);
		} catch (RelojException e) {
			e.printStackTrace();
		}
		jlText = new JLabel();
		jlText.setFont(new Font("SansSerif", 0, 16));

		// Layout
		panelMarcador.setLayout(new BorderLayout());
		getContentPane().setLayout(new BorderLayout());

		// Alineamientos y añadir componentes
		panelMarcador.setSize(640, 50);
		panelMarcador.add(jlText, BorderLayout.NORTH);
		panelMarcador.add(tiempo.getReloj(), BorderLayout.SOUTH);
		tiempo.getReloj().setFont(new Font("SansSerif", Font.PLAIN, 16));
		jlText.setHorizontalAlignment(SwingConstants.CENTER);
		tiempo.getReloj().setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(canPintar, BorderLayout.SOUTH);
		getContentPane().add(panelMarcador, BorderLayout.NORTH);

		// La ventana tiene que escuchar el teclado.
		this.addKeyListener(this);

		// Determinamos los parámetros de la ventana.
		this.setResizable(false);
		this.setTitle("BombermanAddict");
		this.setSize(645, 727);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		/*
		 * En caso de que se quiera cerrar la ventana
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * Si es modo Historia guardamos la puntuación general
				 */
				if (ControlPrincipal.getJugadorUno().getModo() == ModoJuego.Historia) {
					PuntuGeneral tempGene = new PuntuGeneral(AccesoPuntuGen
							.getNumPunt(),
							((bomberman.database.Jugador) AccesoJugador
									.getJugador(ControlPrincipal
											.getJugadorUno().getNombre(),
											ControlPrincipal.getJugadorUno()
													.getApellidos(),
											ControlPrincipal.getJugadorUno()
													.getNick(),
											ControlPrincipal.getJugadorUno()
													.getEmail()))
									.getCod_jugador(), false, ControlPrincipal
									.getJugadorUno().getPuntuacion(),
							ManipuladorFecha.getFecha(), 0, -1);
					// Introducimos la puntuación general
					AccesoPuntuGen.insertarPunt(tempGene);
				}
				GestorVentana.hacerVisible(VentanaInicial.class, true);
				GestorVentana.ocultarVentana(VentanaJuego.class);
			}
		});
	}

	/**
	 * Método que se ejecuta en caso de que se pulse una tecla. El qué hacer se
	 * lo deja al objeto de la clase Bomberman.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		if (bomber1 != null)
			bomber1.teclaPulsada(e);
		if (bomber2 != null)
			bomber2.teclaPulsada(e);
	}

	/**
	 * Método que se ejecuta en caso de que se deje de pulsar una tecla. El qué
	 * hacer se lo deja al objeto de la clase Bomberman.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		if (bomber1 != null)
			bomber1.teclaSoltada(e);
		if (bomber2 != null)
			bomber2.teclaSoltada(e);
	}

	/**
	 * Este método es obligatorio crearlo cuando implementamos la interfaz
	 * KeyListener pero realmente no hacemos nada con el.
	 */
	public void keyTyped(KeyEvent e) {
		/*
		 * Lo que hace es escuchar ambos eventos cuando la tecla es pulsada y
		 * también cuando es soltada.
		 */
	}

	/**
	 * Devuelve el primer Bomberman, el blanco
	 * 
	 * @return bomber1
	 */
	public Bomberman getBomberman() {
		return bomber1;
	}

	/**
	 * Devuelve el segundo Bomberman, el segundo
	 * 
	 * @return bomber2
	 */
	public Bomberman getBomberman2() {
		return bomber2;
	}

	/**
	 * Modifica el primer Bomberman y lo añade al ArrayList de Sprites.
	 * 
	 * @param b
	 *            - Bomberman
	 */
	public void setBomberman(Bomberman b) {
		this.bomber1 = b;
		añadirSprite(bomber1);
	}

	/**
	 * Modifica el segundo Bomberman y lo añade al ArrayList de Sprites
	 * 
	 * @param b
	 *            - Bomberman
	 */
	public void setBomberman2(Bomberman b) {
		this.bomber2 = b;
		añadirSprite(bomber2);
	}

	/**
	 * Modifica el jugador
	 * 
	 * @param jugador
	 *            - Jugador
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * Devuelve el Canvas donde estamos pintando todos los personajes.
	 */
	public Canvas getPanel() {
		return canPintar;
	}

	/**
	 * Añade un Sprite al ArrayList
	 * 
	 * @param spr
	 *            - Sprite
	 */
	public void añadirSprite(Sprite spr) {
		/*
		 * Hay que tener ojo en el orden que se meten ya que eso determinará
		 * cuando se pintan y quien se pintará encima del otro.
		 */
		/*
		 * Si es Llama o Pildora siempre se guardan los primeros porque tienen
		 * que pintarse siempre lo más abajo posible. Nunca van pintados encima
		 * de otros Sprites.
		 */
		if (spr instanceof Llama || spr instanceof Pildora) {
			arLista.add(0, spr);
			// Bomberman los últimos
		} else if (spr instanceof Bomberman) {
			arLista.add(arLista.size(), spr);
			/*
			 * Puerta tiene que irse siempre antes de cualquier Muro porque
			 * estarán escondidas debajo de los muros.
			 */
		} else if (spr instanceof Puerta) {
			if (arLista.size() == 0)
				arLista.add(spr);
			else {
				int cont = 0;
				boolean encon = false;
				// Buscamos el primer muro o Bomberman
				while (!encon) {
					if (arLista.get(cont) instanceof Muro
							|| arLista.get(cont) instanceof Bomberman) {
						encon = true;
					} else {
						cont++;
					}
				}
				arLista.add(cont, spr);
			}
		} else if (spr instanceof BombermanNegro) {
			arLista.add(spr);
			// El resto Sprites
		} else {
			if (arLista.size() != 0) {
				//Si las lista es mayor que 1
				if (arLista.size() > 1) {
					/*
					 * Si el segundo por la cola es de tipo
					 * Bomberman significa que hay 2 Bomberman.
					 */
					if (arLista.get(arLista.size() - 2) instanceof Bomberman)
						arLista.add(arLista.size() - 2, spr);
					else
						arLista.add(arLista.size() - 1, spr);
				} else {
					arLista.add(arLista.size() - 1, spr);
				}
			} else
				arLista.add(spr);
		}
	}

	/**
	 * Devuelve un ArrayList con todos los
	 * Sprites.
	 * @return srLista - ArrayList<Sprite>
	 */
	public ArrayList<Sprite> getLista() {
		return arLista;
	}

	/**
	 * Actualiza la puntuación de la pantalla.
	 */
	public void setPuntuacion() {
		jugador = ControlPrincipal.getJugadorUno();
		jlText.setText("<html><b>Vidas</b>: " + jugador.getVidas()
				+ "&emsp;<b>Puntuación nivel:</b> " + jugador.getPuntuNivel()
				+ "&emsp;<b>Puntuación total:</b> " + jugador.getPuntuacion()
				+ "&emsp;<b>Enemigos restantes:</b>" + this.rivalesQuedan()
				+ "</html>");
		ControlPrincipal.getJugadorUno().setPuntuacion(jugador.getPuntuacion());
		ControlPrincipal.getJugadorUno().setPuntuNivel(jugador.getPuntuNivel());
	}

	/**
	 * Empieza la cuenta atrás
	 */
	public void empezarReloj() {
		if (parado)
			tiempo.setParado(false);
	}

	/**
	 * Sobrescribimos el método setVisible para
	 * arrancar la puntuación cuando se haga visible.
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (this.isVisible()) {
			this.setPuntuacion();
			setTiempoReloj(
					AccesoNivel.getNivel(
							ControlPrincipal.getJugadorUno().getNivel())
							.getTiempo() / 60,
					AccesoNivel.getNivel(
							ControlPrincipal.getJugadorUno().getNivel())
							.getTiempo() % 60);
			this.empezarReloj();
		}
	}

	/**
	 * Devuelve el reloj de la ventana.
	 * @return tiempo - CuentaAtras
	 */
	public CuentaAtras getReloj() {
		return tiempo;
	}

	/**
	 * Modifica el tiempo del reloj de la ventana.
	 * @param min - int
	 * @param seg - int
	 */
	public void setTiempoReloj(int min, int seg) {
		try {
			tiempo.setTiempo(min, seg);
		} catch (RelojException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hace que la partida se acabe.
	 */
	public void setAcabarPartida(boolean acabar) {
		finalizar = acabar;
	}

	/**
	 * Recibe si la partida está acabada o no.
	 * @return finalizar - boolean
	 */
	public boolean getAcabarPartida() {
		return finalizar;
	}

	/**
	 * Hace que el nivel actual haya sido superado.
	 */
	public void setSuperadoNivel(boolean estado) {
		superadoNivel = estado;
	}

	/**
	 * Devuelve si el nivel actual ha sido superado.
	 * @return superadoNivel - boolean
	 */
	public boolean getSuperadoNivel() {
		return superadoNivel;
	}

	/**
	 * Borra todos los Sprites del ArrayList
	 */
	public void borrarSprites() {
		arLista.clear();
	}

	/**
	 * Devuelve el número de rivales que quedan en este nivel
	 * @return int - Nñumero de rivales
	 */
	public int rivalesQuedan() {
		int cont = 0;
		for (Sprite spr : arLista) {
			if (spr instanceof Enemigo)
				cont++;
		}
		return cont;
	}

	/**
	 * Busca a un personaje que este en la misma posición
	 * que el personaje por parámetro. Sirve para que la
	 * puerta sepa si tiene un muro encima o no.
	 * Devuelve los personajes que esten colisionando con
	 * el personaje enviado por parámetro.
	 * @return tempArray - ArrayList<Sprite>
	 */
	public ArrayList<Sprite> buscarPersonajePos(Class clase, Sprite spr) {
		ArrayList<Sprite> tempArray = new ArrayList<Sprite>();
		for (Sprite sprtTemp : arLista) {
			if (sprtTemp.colision(spr) && (sprtTemp != spr)
					&& sprtTemp.getClass().isAssignableFrom(clase)) {
				tempArray.add(sprtTemp);
			}
		}
		return tempArray;
	}
}
