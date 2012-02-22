package bomberman.ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * La clase VentanaInformación está diseñada para mostrar información varia
 * sobre el pfJuego. Hereda de JDialog.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaInformacion extends JDialog {

	private static final long serialVersionUID = -7461719037402108362L;
	private JTabbedPane jtPestaña;
	private PanelFondo pfHistoria;
	private PanelFondo pfDesarrolladores;
	private PanelFondo pfProposito;
	private String explipfHistoria;
	private String explipfDesa;
	private String explipfProp;

	/**
	 * Constructor principal de la clase VentanaInformación.
	 */
	public VentanaInformacion() {
		// Hay que crear un PanelFondo con cada imagen.
		pfHistoria = new PanelFondo("bomberman.jpg", this);
		pfDesarrolladores = new PanelFondo("bomberman.jpg", this);
		pfProposito = new PanelFondo("bomberman.jpg", this);

		// Las petañas que tendrá la ventana.
		jtPestaña = new JTabbedPane();

		/*
		 * El texto que tendrá cada Pestaña. LLamamos a el método meterTexto que
		 * se encargará de introducir el texto en el fondo correspondiente. La
		 * etiqueta '<font color = xxxx>' nos sirve para darle color al texto.
		 */

		explipfDesa = "<html><font color = red>Autores:<P>"
				+ "<html>Jefe del proyecto: David Orive Miguel<P>"
				+ "<html>Desarrollador de bases de datos: Urtzi Pérez Aspiazu<P>"
				+ "<html>Desarrollador de interfaz de usuario: Álvaro Carmona Basanez<P>"
				+ "<html>Desarrollador de lógica de negocio: Beñat Bravo Quintas<P>";
		meterTexto(explipfDesa, pfDesarrolladores);

		// Añadimos cada fondo a la jtPestaña correspondiente.
		jtPestaña.add("Historia", pfHistoria);
		jtPestaña.add("Desarrolladores", pfDesarrolladores);
		jtPestaña.add("Propósito", pfProposito);

		// Añadimos el objeto JTabbedPane al contenedor principal.
		getContentPane().add(jtPestaña);
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(350, 285));
		this.setSize(389, 308);
		this.setModal(true);
		this.setTitle("Información");
		/*
		 * Como lo único que quiero es que está ventana se cierre cuando damos a
		 * la X, entonces con esta linea consigo que la ejecución de la ventana
		 * se pare. Muy útil si lo único que se quiere es cerrar la ventana.
		 */
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Sirve para meter cada texto en su correspondiente sitio de forma
	 * ordenada.
	 * 
	 * @param texto
	 *            - String. El texto a introducir en el fondo.
	 * @param fondo
	 *            - PanelFondo. El panel que contiene la imagen del fondo.
	 */
	public void meterTexto(String texto, PanelFondo fondo) {
		JLabel label = new JLabel(texto, SwingConstants.CENTER);
		fondo.setLayout(new BorderLayout());
		fondo.add(label, BorderLayout.CENTER);
		// Esto hará espacio entre el borde de la ventana y el texto.
		fondo.add(Box.createRigidArea(new Dimension(20, 5)), BorderLayout.WEST);
		fondo.add(Box.createRigidArea(new Dimension(20, 5)), BorderLayout.EAST);
	}

	public static void main(String[] args) {
		// Creamos la ventana VentanaInformacion.
		VentanaInformacion ventana = new VentanaInformacion();
		// La hacemos visible.
		ventana.setVisible(true);
	}
}
