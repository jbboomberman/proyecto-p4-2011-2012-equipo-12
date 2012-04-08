package bomberman.ventanas;

import javax.swing.*;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;
import bomberman.protagonistas.Bomberman;
import bomberman.protagonistas.Sprite;
import bomberman.protagonistas.SpriteDinamico;
import bomberman.protagonistas.Valcom;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
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
	private Canvas canPintar;
	private BufferedImage biImagen;
	private Bomberman bomber;
	private static ArrayList<Sprite> arLista;
	private JPanel panelMarcador;
	private Jugador jugador;
	
	private JLabel jlText;
	private Font textFont;

	/**
	 * Constructor principal de la ventana.
	 */
	public VentanaJuego() {

		arLista = new ArrayList<Sprite>();
		canPintar = new Canvas();
		canPintar.setSize(660, 660);
		panelMarcador = new JPanel();
		
		jlText = new JLabel();
//		textFont = new Font("TIMESROMAN", Font.PLAIN, 20);
//		jlText.setFont(textFont);
		
		panelMarcador.setSize(660, 50);
		panelMarcador.add(jlText);
		//Layout
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(canPintar, BorderLayout.SOUTH);
		getContentPane().add(panelMarcador, BorderLayout.NORTH);

		// La ventana tiene que escuchar el teclado.
		this.addKeyListener(this);

		// Determinamos los par�metros de la ventana.
		this.setResizable(true);
		this.setTitle("BombermanAddict");
		// this.setUndecorated(true);
		this.setSize(677, 747);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * M�todo que se ejecuta en caso de que se pulse una tecla. El qu� hacer se
	 * lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		bomber.teclaPulsada(e);
	}

	/**
	 * M�todo que se ejecuta en caso de que se deje de pulsar una tecla. El qu�
	 * hacer se lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		bomber.teclaSoltada(e);
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

	public Bomberman getBomberman() {
		return bomber;
	}

	// PRUEBA, HAY QUE MEJORAR
	public void setBomberman(Bomberman b) {
		this.bomber = b;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
		this.setPuntuacion();
	}

	public Canvas getPanel() {
		return canPintar;
	}

	public void a�adirSprite(Sprite spr) {
		arLista.add(spr);
	}

	public ArrayList<Sprite> getLista() {
		return arLista;
	}
	
	public void setPuntuacion(){
		jlText.setText("Vidas: " + jugador.getVidas()
				+ " " + jugador.getPuntuNivel()
				+ " " + jugador.getPuntuacion()
				+ " " + "Tiempo"
				+ " " + "Enemigos");
	}
	
	public static void main(String[] args) {
		VentanaJuego juego = new VentanaJuego();
		juego.setVisible(true);
	}
}
