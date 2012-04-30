package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bomberman.database.AccesoNivel;
import bomberman.managers.ControlPrincipal;

public class VentanaNoSuperado extends JDialog implements ActionListener {

	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pPrincipal;
	private JLabel jlMensaje;
	private JLabel jlNick;
	private JLabel jlNivel;
	private JLabel jlPuntuacion;
	private JLabel jlPuntuacionNivel;

	public VentanaNoSuperado() {
		jbVolverMenu = new JButton("Volver al men�");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>�Que pena no has podido superar el nivel! An�mate a intentarlo de nuevo<P>");
		jlNick = new JLabel();
		jlNivel = new JLabel();
		jlPuntuacion = new JLabel();
		jlPuntuacionNivel = new JLabel();

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

		// A�adir componentes
		pSuperior.add(posicionaComp("Nick: ", jlNick));
		pSuperior.add(posicionaComp("Nivel: ", jlNivel));
		pSuperior.add(posicionaComp("Puntuaci�n: ", jlPuntuacion));
		pSuperior.add(posicionaComp("Puntuaci�n global: ", jlPuntuacionNivel));
		pInferior.add(jbVolverMenu);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 200)),
				BorderLayout.WEST);
		getContentPane().add(pPrincipal);
		this.pack();

		// A�adir escuchadores
		jbVolverMenu.addActionListener(this);

		// Carcter�sticas de la ventana
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que tenga
		 * un tama�o m�ximo. De esta manera puede seguir funcionando a la
		 * perfecci�n en distintas resoluciones.
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
	 * Nos a�ade un componente al contenedor principal de forma centrada.
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
	 * Implementamos el m�todo 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		if (botonPulsado == jbVolverMenu) {
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			jlNick.setText(ControlPrincipal.getJugadorUno().getNick());
			jlNivel.setText(String.valueOf(ControlPrincipal.getJugadorUno()
					.getNivel()));
			jlPuntuacion.setText(String.valueOf(ControlPrincipal
					.getJugadorUno().getPuntuacion()));
		}
	}

	public JLabel getJlNick() {
		return jlNick;
	}

	public void setJlNick(String jlNick) {
		this.jlNick.setText(jlNick);
	}

	public JLabel getJlNivel() {
		return jlNivel;
	}

	public void setJlNivel(String jlNivel) {
		this.jlNivel.setText(jlNivel);
	}

	public JLabel getJlPuntuacion() {
		return jlPuntuacion;
	}

	public void setJlPuntuacion(String jlPuntuacion) {
		this.jlPuntuacion.setText(jlPuntuacion);
	}

	public static void main(String[] args) {
		VentanaNoSuperado prueba = new VentanaNoSuperado();
		prueba.setVisible(true);
	}
}
