package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ventana que nos aparecerá cuando no hayamos conseguido superar el nivel.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaNoSuperado extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pPrincipal;
	private JLabel jlMensaje;
	private JLabel jlNick;
	private JLabel jlNivel;
	private JLabel jlPuntuacion;
	private JLabel jlPuntuacionNivel;

	/**
	 * Constructor principal de la clase VentanaNoSuperado
	 */
	public VentanaNoSuperado() {
		jbVolverMenu = new JButton("Volver al menú");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>¡Que pena no hayas podido superar el nivel! Anímate a intentarlo de nuevo<P>");
		jlNick = new JLabel();
		jlNivel = new JLabel();
		jlPuntuacion = new JLabel();
		jlPuntuacionNivel = new JLabel();

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: ", jlNick));
		pSuperior.add(posicionaComp("Nivel: ", jlNivel));
		pSuperior.add(posicionaComp("Puntuación: ", jlPuntuacion));
		pSuperior.add(posicionaComp("Puntuación global: ", jlPuntuacionNivel));
		pInferior.add(jbVolverMenu);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 200)),
				BorderLayout.WEST);
		getContentPane().add(pPrincipal);
		this.pack();

		// Añadir escuchadores
		jbVolverMenu.addActionListener(this);

		// Carcterísticas de la ventana
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(200, 100));
		this.setSize(350, 250);
		this.setTitle("Nivel superado");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);

	}

	/**
	 * Nos añade un componente al contenedor principal de forma centrada.
	 * 
	 * @param componente
	 *            - Component. Container donde hay que meter los componentes.
	 */
	private JPanel posicionaComp(String nombre, JLabel texto) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(new JLabel(nombre));
		panelCompl.add(texto);
		return panelCompl;
	}

	/**
	 * Implementamos el método 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		if (botonPulsado == jbVolverMenu) {
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}

	/**
	 * Sobreescribimos el método setVisible() porque queremos que cada vez que
	 * se actualice también se hagan visibles los cambios.
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
//		jlNick.setText(ControlPrincipal.getJugadorUno().getNick());
//		jlNivel.setText(String.valueOf(ControlPrincipal.getJugadorUno()
//				.getNivel()));
//		jlPuntuacion.setText(String.valueOf(ControlPrincipal.getJugadorUno()
//				.getPuntuacion()));
	}

	/**
	 * Nos devuelve el contenido del JTextField que contiene el nick.
	 * 
	 * @return jlNick - JLabel
	 */
	public JLabel getJlNick() {
		return jlNick;
	}

	/**
	 * Modificamos el contenido del JTextField nick, como parámetro un String.
	 * 
	 * @param jlNick
	 *            - String
	 */
	public void setJlNick(String jlNick) {
		this.jlNick.setText(jlNick);
	}

	/**
	 * Nos devuelve el contenido del JTextField que contiene el nivel.
	 * 
	 * @return jlNivel - JLabel
	 */
	public JLabel getJlNivel() {
		return jlNivel;
	}

	/**
	 * Modificamos el contenido del JTextField de nivel.
	 * 
	 * @param jlNivel
	 *            - String
	 */
	public void setJlNivel(String jlNivel) {
		this.jlNivel.setText(jlNivel);
	}

	/**
	 * Nos devuelve el contenido del JTextField puntuación.
	 * 
	 * @return jlPuntuacion
	 */
	public JLabel getJlPuntuacion() {
		return jlPuntuacion;
	}

	/**
	 * Modificamos el contenido del JTextField de puntuación.
	 * 
	 * @param jlPuntuacion
	 *            - String
	 */
	public void setJlPuntuacion(String jlPuntuacion) {
		this.jlPuntuacion.setText(jlPuntuacion);
	}
}
