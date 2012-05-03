package bomberman.ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * La clase VentanaInformación está diseñada para mostrar información.
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
		pfHistoria = new PanelFondo("fondoBomber.gif", this);
		pfDesarrolladores = new PanelFondo("fondoBomber2.jpg", this);
		pfProposito = new PanelFondo("fondoBomber.gif", this);

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
		explipfHistoria = "<html><font color = red>Bomberman es un juego de estilo estratégico-laberínticos que corresponde a una franquicia de videojuegos desarrollados por Hudson Soft y que viene dando juego desde 1983 hasta hoy en día donde sigue dando juegos para las nuevas consolas que van saliendo al mercado. Esta franquicia tuvo su primera aparición en el año 1983 con el nombre de Eric and the Floaters en Europa y Estados Unidos, con el famoso nombre de Bomberman en Japón para los ZX Spectrum y los MSX.<P>"
				+ "<html>En 1985 salió la versión para la NES con el nombre de Bomberman, juego que se considera como el origen del Bomberman tal como lo conocemos hoy día y que en un principio en Europa se dio a conocer con el nombre de Dynablaster.<P>"
				+ "<html>En 1990 Hudson portó y mejoro el juego para la TurboGrafx-16 con una nueva imagen. La imagen actual de los Bomberman proviene de esta versión.<P>"
				+ "<html>Posteriormente han salido versiones para la mayoría de consolas de videojuegos, ordenadores, recreativas y teléfonos móviles. También, debido a la simplicidad del juego, han salido decenas de copias no oficiales del juego.<P>";
		meterTexto(explipfHistoria, pfHistoria);

		explipfProp = "<html><font color = red>Este juego nace como consecuencia de un proyecto de Programación IV con el objetivo de desarrollar habilidades en la programación con Java<P>";
		meterTexto(explipfProp, pfProposito);
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
		this.setSize(389, 408);
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
}
