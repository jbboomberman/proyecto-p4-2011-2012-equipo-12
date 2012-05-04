package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bomberman.database.AccesoControles;
import bomberman.database.AccesoMapa;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.ManagerSonido;

/**
 * Ventana que nos ofrecer� elegir el modo de juego.
 * @author David
 * @version 1.0
 */
public class VentanaSeleccion extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JLabel texto;
	private JButton jbHistoria;
	private JButton jbMultijugador;
	private JButton jbMaster;
	private JPanel panelInferior;
	private JPanel panelSuperior;

	/**
	 * Constructor principal de la clase VentanaSeleccion
	 */
	public VentanaSeleccion() {

		// Inicializamos las variables.
		texto = new JLabel("Seleccione el modo de juego: ");
		jbHistoria = new JButton("Historia");
		jbMultijugador = new JButton("Multijugador");
		jbMaster = new JButton("M�ster");
		panelInferior = new JPanel();
		panelSuperior = new JPanel();

		// Layout de los paneles
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());
		
		// A�adir elementos a los paneles
		panelSuperior.add(texto);
		panelInferior.add(jbHistoria);
		panelInferior.add(jbMultijugador);
		panelInferior.add(jbMaster);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// A�adir escuchadores
		jbHistoria.addActionListener(this);
		jbMultijugador.addActionListener(this);
		jbMaster.addActionListener(this);

		// Par�metros de la ventana.
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que tenga
		 * un tama�o m�ximo. De esta manera puede seguir funcionando a la
		 * perfecci�n en distintas resoluciones.
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

	/**
	 * Implementamos el m�todo 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. El evento que se ha producido.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbHistoria || botonPulsado == jbMaster
				|| botonPulsado == jbMultijugador){
			ControlPrincipal.setJugadorUno(new Jugador());
			ControlPrincipal.getJugadorUno().setArriba(
					AccesoControles.getControl("ARRIBA", 1));
			ControlPrincipal.getJugadorUno().setAbajo(
					AccesoControles.getControl("ABAJO", 1));
			ControlPrincipal.getJugadorUno().setDerecha(
					AccesoControles.getControl("DERECHA", 1));
			ControlPrincipal.getJugadorUno().setIzquierda(
					AccesoControles.getControl("IZQUIERDA", 1));
			ControlPrincipal.getJugadorUno().setBomba(
					AccesoControles.getControl("BOMBA", 1));
		}
		
		/*
		 * Hacemos sonar la m�sica
		 */
		if ((botonPulsado == jbHistoria
				|| botonPulsado == jbMaster)
				&& ((VentanaControles) GestorVentana
						.getVentana(VentanaControles.class)).getSonido()) {
			try {
				ManagerSonido.playClip("levelintrosong.wav", false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// Si el bot�n pulsado era jbHistoria
		if (botonPulsado == jbHistoria) {
			new Thread(new Runnable() {
				public void run() {
					//Actualizamos los par�metros del jugador
					ControlPrincipal.getJugadorUno().setNivel(1);
					ControlPrincipal.getJugadorUno()
							.setModo(ModoJuego.Historia);
					GestorVentana.ocultarVentana(VentanaSeleccion.class);
					GestorVentana.hacerVisible(VentanaDatos.class, false);
					ControlPrincipal.crearEscenario(AccesoMapa.getCodMapa(1));
				}
			}).start();

		} else if (botonPulsado == jbMultijugador) {
			new Thread(new Runnable() {
				public void run() {
					/*
					 * Si es multijugador actualizamos al
					 * jugador 2.
					 */
					ControlPrincipal.setJugadorDos(new Jugador());
					ControlPrincipal.getJugadorDos().setArriba(
							AccesoControles.getControl("ARRIBA", 2));
					ControlPrincipal.getJugadorDos().setAbajo(
							AccesoControles.getControl("ABAJO", 2));
					ControlPrincipal.getJugadorDos().setDerecha(
							AccesoControles.getControl("DERECHA", 2));
					ControlPrincipal.getJugadorDos().setIzquierda(
							AccesoControles.getControl("IZQUIERDA", 2));
					ControlPrincipal.getJugadorDos().setBomba(
							AccesoControles.getControl("BOMBA", 2));
					ControlPrincipal.getJugadorUno().setModo(
							ModoJuego.Multijugador);
					ControlPrincipal.crearEscenario(AccesoMapa.getCodMapa(11));
					GestorVentana.ocultarVentana(VentanaSeleccion.class);
					GestorVentana.hacerVisible(VentanaJuego.class, true);
				}
			}).start();

		} else if (botonPulsado == jbMaster) {
			new Thread(new Runnable() {
				public void run() {
					GestorVentana.ocultarVentana(VentanaSeleccion.class);
					GestorVentana.hacerVisible(VentanaDirecto.class, false);
				}
			}).start();
		}
	}
}
