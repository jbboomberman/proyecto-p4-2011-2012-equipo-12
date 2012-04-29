package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bomberman.database.AccesoControles;
import bomberman.database.AccesoMapa;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.PrepararEscenario;
import bomberman.outin.LeerMapa;
import bomberman.protagonistas.BombermanNegro;

public class VentanaSeleccion extends JDialog implements ActionListener {

	private JLabel texto;
	private JButton jbHistoria;
	private JButton jbMultijugador;
	private JButton jbMaster;
	private JPanel panelInferior;
	private JPanel panelSuperior;
	private ModoJuego modo;

	public VentanaSeleccion() {

		// Inicializamos las variables.
		texto = new JLabel("Seleccione el modo de juego: ");
		jbHistoria = new JButton("Historia");
		jbMultijugador = new JButton("Multijugador");
		jbMaster = new JButton("Máster");
		panelInferior = new JPanel();
		panelSuperior = new JPanel();

		// Layout de los paneles
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());
		// Añadir elementos a los paneles
		panelSuperior.add(texto);
		panelInferior.add(jbHistoria);
		panelInferior.add(jbMultijugador);
		panelInferior.add(jbMaster);

		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Añadir escuchadores
		jbHistoria.addActionListener(this);
		jbMultijugador.addActionListener(this);
		jbMaster.addActionListener(this);

		// Parámetros de la ventana.
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setSize(300, 100);
		this.setResizable(false);
		this.setTitle("Elija un modo");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	public ModoJuego getModo() {
		return modo;
	}

	public void setModo(ModoJuego modo) {
		this.modo = modo;
	}

	/**
	 * Implementamos el método 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. El evento que se ha producido.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		// Si el boton pulsado erá botonAceptar
		if (botonPulsado == jbHistoria) {
			 new Thread(
	            		new Runnable() {
	            			public void run() {
	            				GestorVentana.ocultarVentana(VentanaSeleccion.class);
	            				GestorVentana.hacerVisible(VentanaDatos.class, false);
	            				modo = ModoJuego.Historia;
	            				ControlPrincipal.crearEscenario(AccesoMapa.getCodMapa(1));
//	        					GestorVentana.hacerVisible(VentanaJuego.class, true);
	            			}
	            		}
	            ).start();

		} else if (botonPulsado == jbMultijugador) {
			new Thread(
            		new Runnable() {
            			public void run() {
            				ControlPrincipal.setJugadorDos(new Jugador());
            				ControlPrincipal.getJugadorDos().setArriba(AccesoControles.getControl("ARRIBA", 2));
            				ControlPrincipal.getJugadorDos().setAbajo(AccesoControles.getControl("ABAJO", 2));
            				ControlPrincipal.getJugadorDos().setDerecha(AccesoControles.getControl("DERECHA", 2));
            				ControlPrincipal.getJugadorDos().setIzquierda(AccesoControles.getControl("IZQUIERDA", 2));
            				ControlPrincipal.crearEscenario(AccesoMapa.getCodMapa(11));
            				modo = ModoJuego.Multijugador;
            				GestorVentana.ocultarVentana(VentanaSeleccion.class);
            				GestorVentana.hacerVisible(VentanaJuego.class, true);
            			}
            		}
            ).start();

		} else if (botonPulsado == jbMaster) {
			new Thread(
            		new Runnable() {
            			public void run() {
            				modo = ModoJuego.Master;
            				GestorVentana.ocultarVentana(VentanaSeleccion.class);
            				GestorVentana.hacerVisible(VentanaDirecto.class, false);
            			}
            		}
            ).start();
		}
	}

	public static void main(String[] args) {
		VentanaSeleccion prueba = new VentanaSeleccion();
		prueba.setVisible(true);
	}
}
