package bomberman.ventanas;

import javax.swing.*;

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
 * La clase VentanaJuego es donde se desarrolla lo que es el juego en sí.
 * Implementa el inteface KeyListener para poder escuchar los eventos generados
 * por el teclado. Hereda de JFrame.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaJuego extends JFrame implements KeyListener, Escenario {

	private static final long serialVersionUID = -7461719037402108362L;
	private JPanel jpPrincipal;
	private Canvas canPintar;
	private BufferedImage biImagen;
	private Bomberman bomber;
	private Escenario escenario;
	private static ArrayList<Sprite> arLista;

	/**
	 * Constructor principal de la ventana.
	 */
	public VentanaJuego() {

		jpPrincipal = (JPanel) this.getContentPane();
		arLista = new ArrayList<Sprite>();
		setBounds(0, 0, 550, 550);
		canPintar = new Canvas();
		jpPrincipal.setPreferredSize(new Dimension(550, 550));
		jpPrincipal.setLayout(null);
		jpPrincipal.add(canPintar);
		
		// La ventana tiene que escuchar el teclado.
		this.addKeyListener(this);

		// Determinamos los parámetros de la ventana.
		this.setResizable(false);
		this.setTitle("BombermanAdict");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Método que se ejecuta en caso de que se pulse una tecla. El qué hacer se
	 * lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		bomber.teclaPulsada(e);
	}

	/**
	 * Método que se ejecuta en caso de que se deje de pulsar una tecla. El qué
	 * hacer se lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		bomber.teclaSoltada(e);
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

	public Bomberman getBomberman() {
		return bomber;
	}
	
	//PRUEBA, HAY QUE MEJORAR
	public void setBomberman(Bomberman b){
		this.bomber = b;
	}

	public Canvas getPanel() {
		return canPintar;
	}
	
	public void añadirSprite(Sprite spr){
		arLista.add(spr);
	}
	
	public ArrayList<Sprite> getLista(){
		return arLista;
	}
	public static void main(String[] args) {
		VentanaJuego juego = new VentanaJuego();
		juego.setVisible(true);
	}
}