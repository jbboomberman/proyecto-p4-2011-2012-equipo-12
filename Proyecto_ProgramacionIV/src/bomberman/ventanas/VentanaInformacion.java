package bomberman.ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * La clase VentanaInformaci�n est� dise�ada para mostrar informaci�n varia
 * sobre el pfJuego. Hereda de JDialog.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaInformacion extends JDialog {

	private static final long serialVersionUID = -7461719037402108362L;
	private JTabbedPane jtPesta�a;
	private PanelFondo pfHistoria;
	private PanelFondo pfDesarrolladores;
	private PanelFondo pfProposito;
	private String explipfHistoria;
	private String explipfDesa;
	private String explipfProp;

	/**
	 * Constructor principal de la clase VentanaInformaci�n.
	 */
	public VentanaInformacion() {
		// Hay que crear un PanelFondo con cada imagen.
		pfHistoria = new PanelFondo("bomberman.jpg", this);
		pfDesarrolladores = new PanelFondo("bomberman.jpg", this);
		pfProposito = new PanelFondo("bomberman.jpg", this);

		// Las peta�as que tendr� la ventana.
		jtPesta�a = new JTabbedPane();

		/*
		 * El texto que tendr� cada Pesta�a. LLamamos a el m�todo meterTexto que
		 * se encargar� de introducir el texto en el fondo correspondiente. La
		 * etiqueta '<font color = xxxx>' nos sirve para darle color al texto.
		 */

		explipfDesa = "<html><font color = red>Autores:<P>"
				+ "<html>Jefe del proyecto: David Orive Miguel<P>"
				+ "<html>Desarrollador de bases de datos: Urtzi P�rez Aspiazu<P>"
				+ "<html>Desarrollador de interfaz de usuario: �lvaro Carmona Basanez<P>"
				+ "<html>Desarrollador de l�gica de negocio: Be�at Bravo Quintas<P>";
		meterTexto(explipfDesa, pfDesarrolladores);

		// A�adimos cada fondo a la jtPesta�a correspondiente.
		jtPesta�a.add("Historia", pfHistoria);
		jtPesta�a.add("Desarrolladores", pfDesarrolladores);
		jtPesta�a.add("Prop�sito", pfProposito);

		// A�adimos el objeto JTabbedPane al contenedor principal.
		getContentPane().add(jtPesta�a);
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que tenga
		 * un tama�o m�ximo. De esta manera puede seguir funcionando a la
		 * perfecci�n en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(350, 285));
		this.setSize(389, 308);
		this.setModal(true);
		this.setTitle("Informaci�n");
		/*
		 * Como lo �nico que quiero es que est� ventana se cierre cuando damos a
		 * la X, entonces con esta linea consigo que la ejecuci�n de la ventana
		 * se pare. Muy �til si lo �nico que se quiere es cerrar la ventana.
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
		// Esto har� espacio entre el borde de la ventana y el texto.
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
