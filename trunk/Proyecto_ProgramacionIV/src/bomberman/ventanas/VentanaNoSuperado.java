package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bomberman.database.AccesoNivel;
import bomberman.managers.ControlPrincipal;

public class VentanaNoSuperado extends JDialog {

	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pPrincipal;
	private JLabel jlMensaje;
	private JLabel jlNick;
	private JLabel jlNivel;
	private JLabel jlPuntuacion;

	public VentanaNoSuperado() {
		jbVolverMenu = new JButton("Volver al menú");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>¡Que pena no has podido superar el nivel! Anímate a intentarlo de nuevo<P>");
		jlNick = new JLabel();
		jlNivel = new JLabel();
		jlPuntuacion = new JLabel();

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: ", jlNick));
		pSuperior.add(posicionaComp("Nivel: ", jlNivel));
		pSuperior.add(posicionaComp("Puntuación: ", jlPuntuacion));
		pInferior.add(jbVolverMenu);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pPrincipal);

		// Carcterísticas de la ventana
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(200, 100));
		this.setSize(350, 200);
		this.setTitle("Nivel superado");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		Insets insets = this.getInsets();
		pPrincipal.setBorder(BorderFactory.createEmptyBorder(insets.top,
				((insets.left >= 2) ? insets.left - 2 : insets.left),
				insets.bottom, insets.right));
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
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			jlNick.setText(ControlPrincipal.getJugadorUno().getNick());
			jlNivel.setText(String.valueOf(ControlPrincipal.getJugadorUno().getNivel()));
			jlPuntuacion.setText(String.valueOf(ControlPrincipal.getJugadorUno().getPuntuacion()));
		}
	}

	public JLabel getJlNick() {
		return jlNick;
	}

	public void setJlNick(JLabel jlNick) {
		this.jlNick = jlNick;
	}

	public JLabel getJlNivel() {
		return jlNivel;
	}

	public void setJlNivel(JLabel jlNivel) {
		this.jlNivel = jlNivel;
	}

	public JLabel getJlPuntuacion() {
		return jlPuntuacion;
	}

	public void setJlPuntuacion(JLabel jlPuntuacion) {
		this.jlPuntuacion = jlPuntuacion;
	}

	public static void main(String[] args) {
		VentanaNoSuperado prueba = new VentanaNoSuperado();
		prueba.setVisible(true);
	}
}
