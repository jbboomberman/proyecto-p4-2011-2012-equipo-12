package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bomberman.managers.ControlPrincipal;

/**
 * Ventana que nos preguntará si queremos seguir o no.
 * @author David
 * @version 1.0
 */
public class VentanaSeguir extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -7461719037402108362L;
	private JLabel texto;
	private JButton jbSeguirNivel;
	private JButton jbVolver;
	private JPanel panelInferior;
	private JPanel panelSuperior;

	/**
	 * Constructor principal de la clase VentanaSeguir.
	 */
	public VentanaSeguir() {

		// Inicializamos las variables.
		texto = new JLabel("¿Quiere seguir jugando?: ");
		jbSeguirNivel = new JButton("Seguir nivel");
		jbVolver = new JButton("Volver");
		panelInferior = new JPanel();
		panelSuperior = new JPanel();

		// Layout de los paneles
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());
		
		// Añadir elementos a los paneles
		panelSuperior.add(texto);
		panelInferior.add(jbSeguirNivel);
		panelInferior.add(jbVolver);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Añadir escuchadores
		jbSeguirNivel.addActionListener(this);
		jbVolver.addActionListener(this);

		// Parámetros de la ventana.
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setSize(300, 100);
		this.setResizable(false);
		this.setTitle("Elija una opción");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);
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

		// Si el boton pulsado erá jbSeguirNivel
		if (botonPulsado == jbSeguirNivel) {
			new Thread(new Runnable() {
				public void run() {
					//Aumentamos el nivel
					ControlPrincipal.crearEscenario(ControlPrincipal
							.getJugadorUno().getNivel() + 1);
					ControlPrincipal.getJugadorUno().setNivel(
							ControlPrincipal.getJugadorUno().getNivel() + 1);
					GestorVentana.hacerVisible(VentanaJuego.class, true);
				}
			}).start();

		//Volvemos al menú inicial
		} else if (botonPulsado == jbVolver) {
			new Thread(new Runnable() {
				public void run() {
					GestorVentana.hacerVisible(VentanaInicial.class, true);
				}
			}).start();
		}
	}
}
