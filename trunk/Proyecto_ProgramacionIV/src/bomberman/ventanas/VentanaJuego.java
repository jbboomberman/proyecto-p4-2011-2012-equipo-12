package bomberman.ventanas;

import javax.swing.*;

import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.Escenario;
import bomberman.managers.ManagerImagen;
import bomberman.outin.CuentaAtras;
import bomberman.outin.RelojException;
import bomberman.protagonistas.*;

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
	private Canvas canPintar;
	private Bomberman bomber1;
	private Bomberman bomber2;
	private static ArrayList<Sprite> arLista;
	private JPanel panelMarcador;
	private Jugador jugador;
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
		canPintar.setSize(660, 660);
		panelMarcador = new JPanel();
		jugador = ControlPrincipal.getJugadorUno();
		try {
			tiempo = new CuentaAtras(5, 0);
			parado = true;
		} catch (RelojException e) {
			e.printStackTrace();
		}
		jlText = new JLabel();
		jlText.setFont(new Font("SansSerif", 0, 16));

		// Layout
		panelMarcador.setLayout(new BorderLayout());

		// Alineamientos

		panelMarcador.setSize(660, 50);
		panelMarcador.add(jlText, BorderLayout.NORTH);
		panelMarcador.add(tiempo.getReloj(), BorderLayout.SOUTH);
		tiempo.getReloj().setFont(new Font("SansSerif", Font.PLAIN, 16));
		// Layout
		getContentPane().setLayout(new BorderLayout());

		jlText.setHorizontalAlignment(SwingConstants.CENTER);
		tiempo.getReloj().setHorizontalAlignment(SwingConstants.CENTER);

		getContentPane().add(canPintar, BorderLayout.SOUTH);
		getContentPane().add(panelMarcador, BorderLayout.NORTH);

		// La ventana tiene que escuchar el teclado.
		this.addKeyListener(this);

		// Determinamos los parámetros de la ventana.
		this.setResizable(true);
		this.setTitle("BombermanAddict");
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
	 * Método que se ejecuta en caso de que se pulse una tecla. El qué hacer se
	 * lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		if(bomber1 != null)
			bomber1.teclaPulsada(e);
		if(bomber2 != null)
			bomber2.teclaPulsada(e);
	}

	/**
	 * Método que se ejecuta en caso de que se deje de pulsar una tecla. El qué
	 * hacer se lo deja al objeto de la clase Heroe.
	 * 
	 * @param e
	 *            - KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		if(bomber1 != null)
			bomber1.teclaSoltada(e);
		if(bomber2 != null)
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

	public Bomberman getBomberman() {
		return bomber1;
	}
	
	public Bomberman getBomberman2(){
		return bomber2;
	}

	// PRUEBA, HAY QUE MEJORAR
	public void setBomberman(Bomberman b) {
		this.bomber1 = b;
		añadirSprite(bomber1);
	}
	
	public void setBomberman2(Bomberman b){
		this.bomber2 = b;
		añadirSprite(bomber2);
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

	public void añadirSprite(Sprite spr) {
		if (spr instanceof Llama || spr instanceof Pildora) {
			arLista.add(0, spr);
		} else if (spr instanceof Bomberman) {
			arLista.add(arLista.size(), spr);
		}else if(spr instanceof Puerta){
			if(arLista.size() == 0)
				arLista.add(spr);
			else{
				int cont = 0;
				boolean encon = false;
				while(!encon){
					if(arLista.get(cont) instanceof Muro || arLista.get(cont) instanceof Bomberman){
						encon = true;
					}else{
						cont++;
					}
				}
				arLista.add(cont, spr);
			}
				
		} else{
			if(arLista.size() != 0 && bomber2 != null)
				arLista.add(arLista.size() - 2, spr);
			else if(arLista.size() != 0 && bomber2 == null)
				arLista.add(arLista.size() - 1, spr);
			else
				arLista.add(spr);
		}
	}

	public ArrayList<Sprite> getLista() {
		return arLista;
	}

	public void setPuntuacion() {
		jlText.setText("<html><b>Vidas</b>: " + ControlPrincipal.getJugadorUno().getVidas()
				+ "&emsp;<b>Puntuación nivel:</b> " + jugador.getPuntuNivel()
				+ "&emsp;<b>Puntuación total:</b> " + jugador.getPuntuacion()
				+ "&emsp;<b>Enemigos restantes:</b> </html>");
		ControlPrincipal.getJugadorUno().setPuntuacion(jugador.getPuntuacion());
		ControlPrincipal.getJugadorUno().setPuntuNivel(jugador.getPuntuNivel());
	}

	public void empezarReloj() {
		if (parado)
			tiempo.start();
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			this.empezarReloj();
	}

	public CuentaAtras getReloj() {
		return tiempo;
	}
	
	public void setTiempoReloj(int min, int seg){
		try{
			tiempo = new CuentaAtras(min, seg);
		}catch(RelojException e){
			e.printStackTrace();
		}
	}
	
	public void setAcabarPartida(boolean acabar){
		finalizar = acabar;
	}
	
	public boolean getAcabarPartida(){
		return finalizar;
	}
	
	public void superadoNivel(){
		superadoNivel = true;
	}
	
	public boolean getSuperadoNivel(){
		return superadoNivel;
	}
		
	
	/**
	 * Borra todo menos los Bomberman
	 */
	public void borrarSprites(){
		arLista.clear();
	}
	
	public ArrayList<Sprite> buscarPersonajePos(Class clase, Sprite spr){
		ArrayList<Sprite>tempArray = new ArrayList<Sprite>();
		for(Sprite sprtTemp : arLista){
			if(sprtTemp.colision(spr) && (sprtTemp != spr) && sprtTemp.getClass().isAssignableFrom(clase)){
				tempArray.add(sprtTemp);
			}
		}
		return tempArray;
	}

	public static void main(String[] args) {
		VentanaJuego juego = new VentanaJuego();
		juego.setVisible(true);
	}
}
