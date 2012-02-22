package bomberman.ventanas;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;

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
		jlTitulo = new JLabel(new ImageIcon(getClass().getResource("letras_bomber.png")));
		jbBotonJugar = new JButton("Jugar");
		jbBotonCargar = new JButton("Cargar");
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
	 * @param e
	 *            - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonpulsado = e.getSource();
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
	 * Este es el método main que nos sirve para comprobar que la clase funciona
	 * correctamente.
	 * 
	 * @param args
	 *            No utilizados
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		VentanaInicial ventana = new VentanaInicial();
		ventana.setVisible(true);
	}
}
