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

import bomberman.managers.ControlPrincipal;
import bomberman.managers.PrepararEscenario;
import bomberman.outin.LeerMapa;

public class VentanaSeleccion extends JDialog implements ActionListener {

	private JLabel texto;
	private JButton jbHistoria;
	private JButton jbMultijugador;
	private JButton jbMaster;
	private JPanel panelInferior;
	private JPanel panelSuperior;

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

		// Si el boton pulsado er� botonAceptar
		if (botonPulsado == jbHistoria) {
			GestorVentana.ocultarVentana(VentanaSeleccion.class);
			GestorVentana.hacerVisible(VentanaDatos.class, false);
		} else if (botonPulsado == jbMultijugador) {
			ControlPrincipal.crearEscenario("mapaMultijugador.txt");
			GestorVentana.hacerVisible(VentanaJuego.class, true);
		} else if (botonPulsado == jbMaster) {
			
		}
	}

	public static void main(String[] args) {
		VentanaSeleccion prueba = new VentanaSeleccion();
		prueba.setVisible(true);
	}
}
