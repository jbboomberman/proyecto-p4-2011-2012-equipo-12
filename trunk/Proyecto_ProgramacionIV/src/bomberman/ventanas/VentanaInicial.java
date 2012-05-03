package bomberman.ventanas;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.ManagerSonido;

/**
 * Ventana inicial del juego que nos ofrece varias opciones. Extiende de JFrame.
 * Implementa el interfaz ActionListener.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaInicial extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JLabel jlTitulo;
	private JButton jbBotonJugar;
	private JButton jbBotonCargar;
	private JButton jbBotonPuntu;
	private JButton jbBotonConfi;
	private JButton jbBotonInstruc;
	private JButton jbBotonInfo;
	private JButton jbBotonSalir;
	private String strPath;
	private PanelFondo pfFondo;

	/**
	 * Constructor principal, crea una ventana Inicial.
	 */
	public VentanaInicial() {
		strPath = "bomberman.jpg";
		jlTitulo = new JLabel(new ImageIcon(getClass().getClassLoader()
				.getResource("bomberman/resources/letras_bomber.png")));
		jbBotonJugar = new JButton("Jugar");
		jbBotonCargar = new JButton("Cargar partida");
		jbBotonPuntu = new JButton("Puntuaciones");
		jbBotonConfi = new JButton("Configuración");
		jbBotonInstruc = new JButton("Instrucciones");
		jbBotonInfo = new JButton("Información");
		jbBotonSalir = new JButton("Salir");

		/*
		 * Creamos un objeto de tipo PanelpfFondo que hará que se vea una imagen
		 * en el pfFondo de la pantalla.
		 */
		pfFondo = new PanelFondo(strPath, this);

		/*
		 * Para que todos los componentes esten bien centrados determinamos su
		 * alineamiento en el eje X.
		 */
		jlTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonPuntu.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonInstruc.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Layout
		pfFondo.setLayout(new BoxLayout(pfFondo, BoxLayout.Y_AXIS));

		// Metemos los componentes en el contenedor.
		meterComponente(jlTitulo);
		meterComponente(jbBotonJugar);
		meterComponente(jbBotonCargar);
		meterComponente(jbBotonPuntu);
		meterComponente(jbBotonConfi);
		meterComponente(jbBotonInstruc);
		meterComponente(jbBotonInfo);
		meterComponente(jbBotonSalir);

		/*
		 * Agrandamos todos los botones
		 */
		agrandarBonton(jbBotonJugar);
		agrandarBonton(jbBotonCargar);
		agrandarBonton(jbBotonPuntu);
		agrandarBonton(jbBotonConfi);
		agrandarBonton(jbBotonInstruc);
		agrandarBonton(jbBotonInfo);
		agrandarBonton(jbBotonSalir);

		/*
		 * A cada botón le añadimos un escuchador, al ser 'addActionListener' le
		 * añadirá el escuchador más típico, la propia ventana.
		 */
		jbBotonJugar.addActionListener(this);
		jbBotonCargar.addActionListener(this);
		jbBotonPuntu.addActionListener(this);
		jbBotonConfi.addActionListener(this);
		jbBotonInstruc.addActionListener(this);
		jbBotonInfo.addActionListener(this);
		jbBotonSalir.addActionListener(this);

		// Añadimos el JPanel pfFondo al contenedor principal.
		getContentPane().add(pfFondo);

		/*
		 * Determinamos los valores principales de la ventana.
		 */
		this.setSize(490, 598);
		this.setTitle("Menú Principal");
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(350, 300));
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		/*
		 * Queremos que cuando la ventana se cierre el programa entero termine
		 * su ejecución.
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * En caso de que escuche un evento para cerrar la ventana le
				 * diremos que acabe la ejecución del programa entero.
				 */
				System.exit(0);
			}
		});

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
		if ((botonPulsado == jbBotonJugar || botonPulsado == jbBotonCargar
				|| botonPulsado == jbBotonPuntu || botonPulsado == jbBotonConfi
				|| botonPulsado == jbBotonInstruc
				|| botonPulsado == jbBotonInfo || botonPulsado == jbBotonSalir) &&
				((VentanaControles) GestorVentana
						.getVentana(VentanaControles.class)).getSonido()) {
			try {
				ManagerSonido.playClip("click.wav", false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if (botonPulsado == jbBotonJugar) {
			ControlPrincipal.setPararJuego(false);
			GestorVentana.hacerVisible(VentanaSeleccion.class, false);
		} else if (botonPulsado == jbBotonCargar) {
			GestorVentana.hacerVisible(VentanaCargar.class, false);
		} else if (botonPulsado == jbBotonPuntu) {
			GestorVentana.hacerVisible(VentanaPuntuaciones.class, false);
		} else if (botonPulsado == jbBotonConfi) {
			GestorVentana.hacerVisible(VentanaControles.class, false);
		} else if (botonPulsado == jbBotonInstruc) {
			GestorVentana.hacerVisible(VentanaInstrucciones.class, false);
		} else if (botonPulsado == jbBotonInfo) {
			GestorVentana.hacerVisible(VentanaInformacion.class, false);
		} else if (botonPulsado == jbBotonSalir) {
			System.exit(0);
		}
	}

	/**
	 * Con este método logramos meter los componentes de una forma ordenada y
	 * centrada.
	 */
	public void meterComponente(Component comp) {
		JPanel tempPanel = new JPanel();
		// Queremos que este alineado hacia el centro.
		tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tempPanel.add(comp);
		// En caso de no poner este atributo no dibujaría la imagen de pfFondo.
		tempPanel.setOpaque(false);
		pfFondo.add(tempPanel);
	}

	/**
	 * Método que recibe un objeto de la clase JButton y
	 * se encarga de angrandarlo.
	 * @param boton - JButton
	 */
	public void agrandarBonton(JButton boton) {
		boton.setPreferredSize(new Dimension(150, 40));
		boton.setFont(new Font("SansSerif", 0, 16));
	}
}
