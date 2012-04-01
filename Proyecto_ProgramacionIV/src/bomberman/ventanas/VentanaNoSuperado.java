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

public class VentanaNoSuperado extends JDialog {

	private JButton jbOk;
	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pPrincipal;
	private JLabel jlMensaje;

	public VentanaNoSuperado() {
		jbOk = new JButton("Pasar al siguiente nivel");
		jbVolverMenu = new JButton("Volver al menú");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>¡Que pena no has podido superar el nivel! Anímate a intentarlo de nuevo<P>");

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: "));
		pSuperior.add(posicionaComp("Nivel: "));
		pSuperior.add(posicionaComp("Puntuación: "));
		pInferior.add(jbOk);
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
	private JPanel posicionaComp(String nombre) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(new JLabel(nombre));
		return panelCompl;
	}

	public static void main(String[] args) {
		VentanaNoSuperado prueba = new VentanaNoSuperado();
		prueba.setVisible(true);
	}
}
