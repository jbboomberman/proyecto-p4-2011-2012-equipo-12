package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import bomberman.database.AccesoMapa;
import bomberman.managers.ControlPrincipal;

/**
 * Clase que representa a la ventana que aparecerá cuando perdamos una vida.
 * @author David
 * @version 1.0
 */
public class VentanaVidaMenos extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JButton jbContinuar;
	private JButton jbCancelar;
	private JLabel jlTexto;
	private JPanel jpInferior;

	/**
	 * Constructor principal de la clase VentanaVidaMenos
	 */
	public VentanaVidaMenos() {
		jbContinuar = new JButton("Continuar");
		jbCancelar = new JButton("Cancelar");
		jlTexto = new JLabel("Has perdido una vida, ¿quieres continuar?");
		jlTexto.setHorizontalAlignment(SwingConstants.CENTER);
		jpInferior = new JPanel();

		// Layout
		jpInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setLayout(new BorderLayout());

		// Añadir componentes
		jpInferior.add(jbContinuar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jlTexto, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);

		// Listeners
		jbContinuar.addActionListener(this);
		jbCancelar.addActionListener(this);

		// Parámetros de la ventana
		this.setSize(270, 100);
		this.setResizable(false);
		this.setTitle("¿Seguir jugando?");
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
	 * @param e - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		//Seguimos jugando
		if (botonPulsado == jbContinuar) {
			new Thread(new Runnable() {
				public void run() {
					GestorVentana.ocultarVentana(VentanaJuego.class);
					GestorVentana.ocultarVentana(VentanaVidaMenos.class);
					ControlPrincipal.crearEscenario(AccesoMapa
							.getCodMapa(ControlPrincipal.getJugadorUno()
									.getNivel()));
					((VentanaJuego) GestorVentana
							.getVentana(VentanaJuego.class))
							.setAcabarPartida(false);
					GestorVentana.hacerVisible(VentanaJuego.class, true);
				}
			}).start();
		//Volvemos al menú
		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaVidaMenos.class);
			GestorVentana.ocultarVentana(VentanaJuego.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}
}
