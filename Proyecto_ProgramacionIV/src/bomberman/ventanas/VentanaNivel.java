package bomberman.ventanas;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

public class VentanaNivel extends JDialog {

	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JLabel jlMensaje;
	private JPanel pPrincipal;

	public VentanaNivel() {
		jbVolverMenu = new JButton("Volver al menú");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>¡Enhorabuena, has conseguido superar el nivel!<P>");

		// Layouts
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new FlowLayout());
		jlMensaje.setHorizontalTextPosition(SwingConstants.CENTER);
		pPrincipal.setLayout(new BorderLayout());

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: "));
		pSuperior.add(posicionaComp("Nivel: "));
		pSuperior.add(posicionaComp("Puntuación: "));
		pInferior.add(jbVolverMenu);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		getContentPane().add(pPrincipal);

		// Carcterísticas de la ventana
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(200, 200));
		this.setSize(250, 250);
		this.setTitle("Nivel superado");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		Insets insets = this.getInsets();
		pPrincipal.setBorder(new EmptyBorder(new Insets(5, 15, 5, 5)));
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
		VentanaNivel prueba = new VentanaNivel();
		prueba.setVisible(true);
	}
}

