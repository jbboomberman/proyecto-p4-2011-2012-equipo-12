package bomberman.ventanas;

import javax.swing.*;
import java.awt.*;

/**
 * La clase VentanaInformaci�n est� dise�ada para mostrar informaci�n.
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
		pfHistoria = new PanelFondo("fondoBomber.gif", this);
		pfDesarrolladores = new PanelFondo("fondoBomber2.jpg", this);
		pfProposito = new PanelFondo("fondoBomber.gif", this);

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
		explipfHistoria = "<html><font color = red>Bomberman es un juego de estilo estrat�gico-laber�nticos que corresponde a una franquicia de videojuegos desarrollados por Hudson Soft y que viene dando juego desde 1983 hasta hoy en d�a donde sigue dando juegos para las nuevas consolas que van saliendo al mercado. Esta franquicia tuvo su primera aparici�n en el a�o 1983 con el nombre de Eric and the Floaters en Europa y Estados Unidos, con el famoso nombre de Bomberman en Jap�n para los ZX Spectrum y los MSX.<P>"
				+ "<html>En 1985 sali� la versi�n para la NES con el nombre de Bomberman, juego que se considera como el origen del Bomberman tal como lo conocemos hoy d�a y que en un principio en Europa se dio a conocer con el nombre de Dynablaster.<P>"
				+ "<html>En 1990 Hudson port� y mejoro el juego para la TurboGrafx-16 con una nueva imagen. La imagen actual de los Bomberman proviene de esta versi�n.<P>"
				+ "<html>Posteriormente han salido versiones para la mayor�a de consolas de videojuegos, ordenadores, recreativas y tel�fonos m�viles. Tambi�n, debido a la simplicidad del juego, han salido decenas de copias no oficiales del juego.<P>";
		meterTexto(explipfHistoria, pfHistoria);

		explipfProp = "<html><font color = red>Este juego nace como consecuencia de un proyecto de Programaci�n IV con el objetivo de desarrollar habilidades en la programaci�n con Java<P>";
		meterTexto(explipfProp, pfProposito);
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
		this.setSize(389, 408);
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
}
