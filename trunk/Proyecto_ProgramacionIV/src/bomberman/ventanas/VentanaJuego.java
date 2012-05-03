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
 * La clase VentanaJuego es donde se desarrolla lo que es el juego en s�.
 * Implementa el inteface KeyListener para poder escuchar los eventos generados
 * por el teclado. Hereda de JFrame.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaJuego extends JFrame implements KeyListener, Escenario {

	private static final long serialVersionUID = -7461719037402108362L;
	// Canvas donde se pintar�n los personajes
	private Canvas canPintar;
	private Bomberman bomber1;
	private Bomberman bomber2;
	// ArrayList donde se guardar�n los Sprites
	private static ArrayList<Sprite> arLista;
	private JPanel panelMarcador;
	private Jugador jugador;
	// El reloj cuenta atr�s
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

		// Alineamientos y a�adir componentes
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

		// Determinamos los par�metros de la ventana.
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
				 * Si es modo Historia guardamos la puntuaci�n general
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
					// Introducimos la puntuaci�n general
					AccesoPuntuGen.insertarPunt(tempGene);
				}
				GestorVentana.hacerVisible(VentanaInicial.class, true);
				GestorVentana.ocultarVentana(VentanaJuego.class);
			}
		});
	}

	/**
	 * M�todo que se ejecuta en caso de que se pulse una tecla. El qu� hacer se
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
	 * M�todo que se ejecuta en caso de que se deje de pulsar una tecla. El qu�
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
	 * Este m�todo es obligatorio crearlo cuando implementamos la interfaz
	 * KeyListener pero realmente no hacemos nada con el.
	 */
	public void keyTyped(KeyEvent e) {
		/*
		 * Lo que hace es escuchar ambos eventos cuando la tecla es pulsada y
		 * tambi�n cuando es soltada.
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
	 * Modifica el primer Bomberman y lo a�ade al ArrayList de Sprites.
	 * 
	 * @param b
	 *            - Bomberman
	 */
	public void setBomberman(Bomberman b) {
		this.bomber1 = b;
		a�adirSprite(bomber1);
	}

	/**
	 * Modifica el segundo Bomberman y lo a�ade al ArrayList de Sprites
	 * 
	 * @param b
	 *            - Bomberman
	 */
	public void setBomberman2(Bomberman b) {
		this.bomber2 = b;
		a�adirSprite(bomber2);
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
	 * A�ade un Sprite al ArrayList
	 * 
	 * @param spr
	 *            - Sprite
	 */
	public void a�adirSprite(Sprite spr) {
		/*
		 * Hay que tener ojo en el orden que se meten ya que eso determinar�
		 * cuando se pintan y quien se pintar� encima del otro.
		 */
		/*
		 * Si es Llama o Pildora siempre se guardan los primeros porque tienen
		 * que pintarse siempre lo m�s abajo posible. Nunca van pintados encima
		 * de otros Sprites.
		 */
		if (spr instanceof Llama || spr instanceof Pildora) {
			arLista.add(0, spr);
			// Bomberman los �ltimos
		} else if (spr instanceof Bomberman) {
			arLista.add(arLista.size(), spr);
			/*
			 * Puerta tiene que irse siempre antes de cualquier Muro porque
			 * estar�n escondidas debajo de los muros.
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
	 * Actualiza la puntuaci�n de la pantalla.
	 */
	public void setPuntuacion() {
		jugador = ControlPrincipal.getJugadorUno();
		jlText.setText("<html><b>Vidas</b>: " + jugador.getVidas()
				+ "&emsp;<b>Puntuaci�n nivel:</b> " + jugador.getPuntuNivel()
				+ "&emsp;<b>Puntuaci�n total:</b> " + jugador.getPuntuacion()
				+ "&emsp;<b>Enemigos restantes:</b>" + this.rivalesQuedan()
				+ "</html>");
		ControlPrincipal.getJugadorUno().setPuntuacion(jugador.getPuntuacion());
		ControlPrincipal.getJugadorUno().setPuntuNivel(jugador.getPuntuNivel());
	}

	/**
	 * Empieza la cuenta atr�s
	 */
	public void empezarReloj() {
		if (parado)
			tiempo.setParado(false);
	}

	/**
	 * Sobrescribimos el m�todo setVisible para
	 * arrancar la puntuaci�n cuando se haga visible.
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
	 * Recibe si la partida est� acabada o no.
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
	 * Devuelve el n�mero de rivales que quedan en este nivel
	 * @return int - N�umero de rivales
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
	 * Busca a un personaje que este en la misma posici�n
	 * que el personaje por par�metro. Sirve para que la
	 * puerta sepa si tiene un muro encima o no.
	 * Devuelve los personajes que esten colisionando con
	 * el personaje enviado por par�metro.
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
