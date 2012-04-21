package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//Fig 12 Página 17
public class VentanaPuntuaciones extends JFrame implements ActionListener{

	private JTable jtPuntuacion;
	private JPanel jpSuperior;
	private JPanel jpInferior;
	private JPanel jpCentro;
	private JTextField jtJuga;
	private JTextField jtFecha;
	private JComboBox jcNivel;
	private JCheckBox jchTotal;
	private JButton jbTen;
	private JButton jbBuscar;
	private JButton jbCancelar;
	private TableModelPuntuaciones tmPuntu;
	private JScrollPane jsScroll;
	
	public VentanaPuntuaciones() {
		//Inicializamos variables
		jpSuperior = new JPanel();
		jpInferior = new JPanel();
		jpCentro = new JPanel();
		jtJuga = new JTextField(12);
		jtFecha = new JTextField(10);
		String[] arrayCombo = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"10" };
		jcNivel = new JComboBox(arrayCombo);
		jchTotal = new JCheckBox();
		jbTen = new JButton("Top-ten");
		jbBuscar = new JButton("Buscar");
		jbCancelar = new JButton("Cancelar");
		tmPuntu = new TableModelPuntuaciones(0, 6);
		tmPuntu.setColumnIdentifiers(new String[]{"Nombre", "Apellidos", "Nick", "Puntuación", "Nivel", "Fecha"});
		jtPuntuacion = new JTable(tmPuntu);
		jsScroll = new JScrollPane(jtPuntuacion);
		jpCentro.add(jsScroll);
		jpCentro.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		jpSuperior.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		
		//Layouts
		jpInferior.setLayout(new FlowLayout());
		jpSuperior.setLayout(new GridLayout(3, 2));
		getContentPane().setLayout(new BorderLayout());
		
		//Meter componentes
		jpSuperior.add(meterComponente("Buscar jugador: ", jtJuga, true));
		jpSuperior.add(meterComponente("Fecha: ", jtFecha, true));
		jpSuperior.add(jbTen);
		jpSuperior.add(meterComponente("Nivel: ", jcNivel, true));
		jpSuperior.add(meterComponente("Completado total", jchTotal, false));
		jpInferior.add(jbBuscar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jpSuperior, BorderLayout.NORTH);
		getContentPane().add(jsScroll, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);
		
		//Escuchadores
		jbBuscar.addActionListener(this);
		jbCancelar.addActionListener(this);
		jbTen.addActionListener(this);
		
		//Características de la ventana
		this.setSize(550, 600);
		this.setTitle("Mostrar puntuaciones");
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

	private JPanel meterComponente(String nom, JComponent comp, boolean orden){
		JLabel tempLabel = new JLabel(nom);
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout());
		if(orden){
			tempPanel.add(tempLabel);
			tempPanel.add(comp);
		}else{
			tempPanel.add(comp);
			tempPanel.add(tempLabel);
		}
		return tempPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbBuscar){
			
		}else if(botonPulsado == jbCancelar){
			
		}else if(botonPulsado == jbTen){
			
		}
	}
	
	public static void main(String[] args) {
		VentanaPuntuaciones v = new VentanaPuntuaciones();
		v.setVisible(true);
	}
}
