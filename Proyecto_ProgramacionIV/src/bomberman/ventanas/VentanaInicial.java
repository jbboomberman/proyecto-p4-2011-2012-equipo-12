package bomberman.ventanas;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;

/**
 * Ventana inicial del juego que nos ofrece varias opciones.
 * Extiende de JFrame.
 * Implementa el interfaz ActionListener.
 * @author David
 * @version 1.0
 */
public class VentanaInicial extends JFrame implements ActionListener{

	private static final long serialVersionUID = -7461719037402108362L;
	private JLabel jlTitulo;
	private JButton jbBotonJugar;
	private JButton jbBotonPuntu;
	private JButton jbBotonInstruc;
	private JButton jbBotonSobre;
	private String strPath;
	private PanelFondo pfFondo;
	
	/**
	 * Constructor principal, crea una ventana Inicial.
	 */
	public VentanaInicial()
	{
		strPath = "bomberman.jpg";
		jlTitulo = new JLabel("Bomberman");
		jbBotonJugar = new JButton("JUGAR");
		jbBotonPuntu = new JButton("SCORES");
		jbBotonInstruc = new JButton("INSTRUCCIONES");
		jbBotonSobre = new JButton("SOBRE...");
		
		/*
		 * Creamos un objeto de tipo PanelpfFondo que har� que se vea una imagen en el pfFondo de la pantalla.
		 */
		pfFondo = new PanelFondo(strPath, this);
		
		/*
		 * Para que todos los componentes esten bien centrados determinamos su alineamiento en el eje X.
		 */
		jlTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonPuntu.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonInstruc.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBotonSobre.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/*
		 * Modificamos las caracter�sticas del t�tulo para que tenga un buen aspecto.
		 */
		jlTitulo.setFont(new Font("sansserif", Font.BOLD, 50));
		jlTitulo.setForeground(Color.red);
		
		//Layout
		pfFondo.setLayout(new BoxLayout(pfFondo, BoxLayout.Y_AXIS));
		
		//Metemos los componentes en el contenedor.
		meterComponente(jlTitulo);
		meterComponente(jbBotonJugar);
		meterComponente(jbBotonPuntu);
		meterComponente(jbBotonInstruc);
		meterComponente(jbBotonSobre);
		
		/*
		 * A cada bot�n le a�adimos un escuchador, al ser 'addActionListener' le a�adir� el
		 * escuchador m�s t�pico, la propia ventana.
		 */
		jbBotonJugar.addActionListener(this);
		jbBotonPuntu.addActionListener(this);
		jbBotonInstruc.addActionListener(this);
		jbBotonSobre.addActionListener(this);
		
		//A�adimos el JPanel pfFondo al contenedor principal.
		getContentPane().add(pfFondo);
		
		/*
		 * Determinamos los valores principales de la ventana.
		 */
		this.setSize(440, 598);
		this.setTitle("Men� Principal");
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que
		 * tenga un tama�o m�ximo. De esta manera puede seguir funcionando
		 * a la perfecci�n en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension (350, 300));
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
		/*
		 * Queremos que cuando la ventana se cierre el programa entero termine su ejecuci�n.
		 */
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				/*
				 * En caso de que escuche un evento para cerrar la ventana
				 * le diremos que acabe la ejecuci�n del programa entero.
				 */
				System.exit(0);
			}
		});
		
	}
	
	/**
	 * Implementamos el m�todo 'actionPerformed' del interface ActionListener.
	 * @param e - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e)
	{
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la direcci�n del
		 * generador del evento.
		 */
		Object botonpulsado = e.getSource();
		
		//En caso de que el bot�n pulsado sea 'jbBotonJugar'.
//		if(botonpulsado == jbBotonJugar)
//		{
//			//Hacemos visible la ventana 'VentanaDatos'.
//			GestorVentana.hacerVisible(VentanaDatos.class, false);
//		}
//		
//		//En caso de que el bot�n pulsado sea 'jbBotonPuntu'.
//		if(botonpulsado == jbBotonPuntu)
//		{
//			//Hacemos visible la ventana 'VentanaScore'.
//			GestorVentana.hacerVisible(VentanaScore.class, false);
//		}
//		
//		//En caso de que el bot�n pulsado sea 'jbBotonInstruc'.
//		if(botonpulsado == jbBotonInstruc)
//		{
//			//Hacemos visible la ventana 'VentanaInstrucciones'.
//			GestorVentana.hacerVisible(VentanaInstrucciones.class, false);
//		}
		
		//En caso de que el bot�n pulsado sea 'jbBotonSobre'.
//		if(botonpulsado == jbBotonSobre)
//		{
//			//Hacemos visible la ventana 'VentanaInformacion'.
//			GestorVentana.hacerVisible(VentanaInformacion.class, false);
//		}
	}
	
	/**
	 * Con este m�todo logramos meter los componentes de una forma ordenada y centrada.
	 */
	public void meterComponente(Component comp)
	{
		JPanel tempPanel = new JPanel();
		//Queremos que este alineado hacia el centro.
		tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tempPanel.add(comp);
		//En caso de no poner este atributo no dibujar�a la imagen de pfFondo.
		tempPanel.setOpaque(false);
		pfFondo.add(tempPanel);
	}
	
	/**
	 * Este es el m�todo main que nos sirve para comprobar que
	 * la clase funciona correctamente.
	 * @param args No utilizados
	 */
	public static void main (String [] args)
	{
		try {
			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
		} catch (Exception e) {
		}
		VentanaInicial ventana = new VentanaInicial();
		ventana.setVisible(true);
	}
}


