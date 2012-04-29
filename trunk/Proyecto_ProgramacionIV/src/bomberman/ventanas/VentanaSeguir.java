package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.database.AccesoMapa;
import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;

public class VentanaSeguir extends JDialog implements ActionListener{
	private JLabel texto;
	private JButton jbSeguirNivel;
	private JButton jbVolver;
	private JPanel panelInferior;
	private JPanel panelSuperior;
	private ModoJuego modo;

	public VentanaSeguir() {

		// Inicializamos las variables.
		texto = new JLabel("Seleccione el modo de juego: ");
		jbSeguirNivel = new JButton("Seguir nivel");
		jbVolver = new JButton("Volver");
		panelInferior = new JPanel();
		panelSuperior = new JPanel();

		// Layout de los paneles
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());
		// A�adir elementos a los paneles
		panelSuperior.add(texto);
		panelInferior.add(jbSeguirNivel);
		panelInferior.add(jbVolver);

		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// A�adir escuchadores
		jbSeguirNivel.addActionListener(this);
		jbVolver.addActionListener(this);
		
		// Par�metros de la ventana.
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que tenga
		 * un tama�o m�ximo. De esta manera puede seguir funcionando a la
		 * perfecci�n en distintas resoluciones.
		 */
		this.setSize(300, 100);
		this.setResizable(false);
		this.setTitle("Elija una opci�n");
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

		// Si el boton pulsado er� botonAceptar
		if (botonPulsado == jbSeguirNivel) {
			 new Thread(
	            		new Runnable() {
	            			public void run() {
	            				ControlPrincipal.crearEscenario(ControlPrincipal.getJugadorUno().getNivel() + 1);
	            				ControlPrincipal.getJugadorUno().setNivel(ControlPrincipal.getJugadorUno().getNivel());
	            				GestorVentana.hacerVisible(VentanaJuego.class, true);
	            			}
	            		}
	            ).start();

		} else if (botonPulsado == jbVolver) {
			new Thread(
            		new Runnable() {
            			public void run() {
            				GestorVentana.hacerVisible(VentanaInicial.class, true);
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
