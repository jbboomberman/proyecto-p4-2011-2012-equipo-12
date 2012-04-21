package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

//Fig13a  Pag 18.
public class VentanaControles extends JDialog implements ActionListener{

	private JTabbedPane jtbPestañas;
	private JPanel jpSuperior;
	private JPanel jpCentro;
	private JPanel jpCenIzq;
	private JPanel jpCenDer;
	private JPanel jpInferior;
	private JButton jbGuardar;
	private JButton jbCancelar;
	private TitledBorder jug1;
	private TitledBorder jug2;
	private JPanel jpGeneral;
	private JPanel jpGeneral2;
	
	public VentanaControles(){
		//Inicializamos variables
		jpSuperior = new JPanel();
		jpCentro = new JPanel();
		jpCenIzq = new JPanel();
		jpCenDer = new JPanel();
		jpInferior = new JPanel();
		jpGeneral = new JPanel();
		jpGeneral2 = new JPanel();
		jtbPestañas = new JTabbedPane();
		jbGuardar = new JButton("Guardar cambios");
		jbCancelar = new JButton("Cancelar");
		jug1 = BorderFactory.createTitledBorder("Jugador 1");
		jug2 = BorderFactory.createTitledBorder("Jugador 2");
		
		//Layouts
		jpSuperior.setLayout(new FlowLayout());
		jpCentro.setLayout(new GridLayout(1, 2));
		jpCenIzq.setLayout(new BoxLayout(jpCenIzq, BoxLayout.Y_AXIS));
		jpCenDer.setLayout(new BoxLayout(jpCenDer, BoxLayout.Y_AXIS));
		jpCenIzq.setBorder(jug1);
		jpCenDer.setBorder(jug2);
		jpInferior.setLayout(new FlowLayout());
		jpGeneral.setLayout(new BorderLayout());
		jpGeneral2.setLayout(new FlowLayout());
		
		//Añadir componentes
		meterTecla("Arriba", jpCenIzq);
		meterTecla("Abajo", jpCenIzq);
		meterTecla("Derecha", jpCenIzq);
		meterTecla("Izquierda", jpCenIzq);
		meterTecla("Bomba", jpCenIzq);
		meterTecla("Arriba", jpCenDer);
		meterTecla("Abajo", jpCenDer);
		meterTecla("Derecha", jpCenDer);
		meterTecla("Izquierda", jpCenDer);
		meterTecla("Bomba", jpCenDer);
		meterCheckBox("Activar sonido", jpGeneral2);
		meterCheckBox("Activar envio emails", jpGeneral2);
		jpCentro.add(jpCenIzq);
		jpCentro.add(jpCenDer);
		jpSuperior.add(new JLabel("Modifica los controles del programa"));
		jpInferior.add(jbGuardar);
		jpInferior.add(jbCancelar);
		jpGeneral.add(jpInferior, BorderLayout.SOUTH);
		jpGeneral.add(jpSuperior, BorderLayout.NORTH);
		jpGeneral.add(jpCentro, BorderLayout.CENTER);
		jtbPestañas.add("Controles", jpGeneral);
		jtbPestañas.add("Extras", jpGeneral2);
		getContentPane().add(jtbPestañas);
		
		//Características de la ventana
		this.setSize(550, 400);
		this.setTitle("Modificar controles");
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	private void meterTecla(String nom, Container cont){
		JLabel tempLabel = new JLabel(nom);
		JTextField tempField = new JTextField(1);
		/*
		 * Para poder poner el tamaño que quiera de
		 * JTextField porque sino GridLayout no
		 * me deja.
		 */
		JPanel tempPanel = new JPanel();
		tempField.setColumns(2);
		tempPanel.add(tempLabel);
		tempPanel.add(tempField);
		cont.add(tempPanel);
	}
	
	private void meterCheckBox(String texto, Container cont){
		JCheckBox tempCheck = new JCheckBox();
		JLabel tempLabel = new JLabel(texto);
		JPanel tempPanel = new JPanel();
		tempPanel.add(tempCheck);
		tempPanel.add(tempLabel);
		cont.add(tempPanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbGuardar){
			
		}else if(botonPulsado == jbCancelar){
			GestorVentana.ocultarVentana(VentanaControles.class);
		}
	}
	
	public static void main (String [] args){
		VentanaControles prueba = new VentanaControles();
		prueba.setVisible(true);
	}
}
