package bomberman.ventanas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;

import bomberman.protagonistas.*;

/**
 * La clase VentanaJuego es donde se desarrolla lo que es el juego en sí.
 * Implementa el inteface KeyListener para poder escuchar los eventos generados por el teclado.
 * Hereda de JFrame.
 * @author David
 * @version 1.0
 */
public class VentanaJuego extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = -7461719037402108362L;
	private JPanel jpPrincipal;
	private Canvas canPintar;
	private URL miUrl;
	private BufferedImage imagen;
	
	/**
	 * Constructor principal de la ventana.
	 */
	public VentanaJuego()
	{
		jpPrincipal = (JPanel)this.getContentPane();
		setBounds(0, 0, 550, 550);
		canPintar = new Canvas();
		jpPrincipal.setPreferredSize(new Dimension(550,550));
		jpPrincipal.setLayout(null);
		jpPrincipal.add(canPintar);
		miUrl = getClass().getResource("Bomber.jpg");
		try{
			imagen = ImageIO.read(miUrl);
		}catch(IOException e){}
		
		//La ventana tiene que escuchar el teclado.
		this.addKeyListener(this);
		
		//Determinamos los parámetros de la ventana.
		this.setResizable(false);
		this.setTitle("BombermanAdict");
		this.setBounds(0, 0, 550, 550);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	/**
	 * Método que se ejecuta en caso de que se pulse una tecla.
	 * El qué hacer se lo deja al objeto de la clase Heroe.
	 * @param e - KeyEvent
	 */
	public void keyPressed(KeyEvent e)
	{
	}
	
	/**
	 * Método que se ejecuta en caso de que se deje de pulsar una tecla.
	 * El qué hacer se lo deja al objeto de la clase Heroe.
	 * @param e - KeyEvent
	 */
	public void keyReleased(KeyEvent e)
	{
	}
	
	/**
	 * Este método es obligatorio crearlo cuando implementamos la interfaz
	 * KeyListener pero realmente no hacemos nada con el.
	 */
	public void keyTyped(KeyEvent e)
	{
		/*
		 * Lo que hace es escuchar ambos eventos cuando la tecla es pulsada y
		 * también cuando es soltada.
		 */
	}
	
	public void paint(Graphics g){
		g.drawImage(imagen, 40, 40, canPintar);
	}
	
	public static void main (String [] args)
	{
		VentanaJuego juego = new VentanaJuego();
		juego.setVisible(true);
	}
}