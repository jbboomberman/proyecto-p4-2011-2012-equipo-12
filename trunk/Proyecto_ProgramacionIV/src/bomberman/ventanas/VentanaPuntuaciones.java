package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bomberman.database.AccesoMapa;
import bomberman.database.AccesoPunEspe;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.managers.ControlPrincipal;
import bomberman.outin.VerificadorFecha;

//Fig 12 Página 17
public class VentanaPuntuaciones extends JFrame implements ActionListener,
		ItemListener {

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
		// Inicializamos variables
		jpSuperior = new JPanel();
		jpInferior = new JPanel();
		jpCentro = new JPanel();
		jtJuga = new JTextField(12) {
			public String getText() {
				String tempString = super.getText();
				if (tempString.equals(""))
					return null;
				else
					return tempString;
			}
		};
		jtFecha = new JTextField(10) {
			public String getText() {
				String tempString = super.getText();
				if (tempString.equals(""))
					return null;
				else
					return tempString;
			}
		};
		String[] arrayCombo = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10" };
		jcNivel = new JComboBox(arrayCombo);
		jcNivel.setEnabled(false);
		jchTotal = new JCheckBox();
		jchTotal.setSelected(true);
		jcNivel.setEnabled(false);
		jbTen = new JButton("Top-ten");
		jbBuscar = new JButton("Buscar");
		jbCancelar = new JButton("Cancelar");
		tmPuntu = new TableModelPuntuaciones(0, 6);
		tmPuntu.setColumnIdentifiers(new String[] { "Nombre", "Apellidos",
				"Nick", "Puntuación", "Nivel", "Fecha" });
		jtPuntuacion = new JTable(tmPuntu);
		jsScroll = new JScrollPane(jtPuntuacion);
		jpCentro.add(jsScroll);
		jpCentro.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		jpSuperior.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

		// Layouts
		jpInferior.setLayout(new FlowLayout());
		jpSuperior.setLayout(new GridLayout(3, 2));
		getContentPane().setLayout(new BorderLayout());

		// Meter componentes
		jpSuperior.add(meterComponente("Buscar jugador: ", jtJuga, true));
		jpSuperior.add(meterComponente("Fecha: ", jtFecha, true));
		jpSuperior.add(jbTen);
		jpSuperior.add(meterComponente("Nivel: ", jcNivel, true));
		jpSuperior.add(meterComponente("Completado total", jchTotal, false));
		jpInferior.add(jbBuscar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jpSuperior, BorderLayout.NORTH);
		getContentPane().add(jpCentro, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);

		// Escuchadores
		jbBuscar.addActionListener(this);
		jbCancelar.addActionListener(this);
		jbTen.addActionListener(this);
		jchTotal.addItemListener(this);

		// Características de la ventana
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

	private JPanel meterComponente(String nom, JComponent comp, boolean orden) {
		JLabel tempLabel = new JLabel(nom);
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout());
		if (orden) {
			tempPanel.add(tempLabel);
			tempPanel.add(comp);
		} else {
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

		if (botonPulsado == jbBuscar) {
			new Thread(new Runnable() {
				public void run() {
					ArrayList<PuntuGeneral> tempArray = null;

					/*
					 * Si alguno de los JTextField es distinto de null.
					 */
					if (jtJuga.getText() != null || jtFecha.getText() != null) {
						// Le pedimos las puntuaciones con esos datos.
						if (jchTotal.isSelected()) {
							tempArray = AccesoPuntuGen.getPuntDatos(
									jtJuga.getText(), jtFecha.getText());
						} else {
							tempArray = AccesoPunEspe.getPuntDatos(jtJuga
									.getText(), jtFecha.getText(), Integer
									.parseInt((String) jcNivel
											.getSelectedItem()));
						}
						/*
						 * En caso de que haya partidas con esas características
						 * borramos datos anteriores y ponemos los nuevos.
						 */
						if (tempArray != null) {
							jtJuga.setBackground(Color.WHITE);
							jtFecha.setBackground(Color.WHITE);
							// Los ordenamos de mayor a menor.
							Collections.sort(tempArray,
									Collections.reverseOrder());
							tmPuntu.deleteAllRows();
							for (PuntuGeneral punGen : tempArray)
								tmPuntu.añadirFila(punGen);
							// En caso de que no haya resultados.
						} else {
							tmPuntu.deleteAllRows();
							// Si había datos en Jugador lo ponemos en rojo.
							if (jtJuga.getText() != null)
								jtJuga.setBackground(Color.RED);
							// Si había datos en fecha lo ponemos en rojo.
							if (jtFecha.getText() != null)
								jtFecha.setBackground(Color.RED);
						}
					} else {
						tmPuntu.deleteAllRows();
						jtJuga.setBackground(Color.RED);
						jtFecha.setBackground(Color.RED);
					}
				}
			}).start();

		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaPuntuaciones.class);
		} else if (botonPulsado == jbTen) {
			new Thread(new Runnable() {
				public void run() {
					ArrayList<PuntuGeneral> arrayTopTen = AccesoPuntuGen
							.getTopTen();
					if (arrayTopTen != null) {
						Collections.sort(arrayTopTen,
								Collections.reverseOrder());
						tmPuntu.deleteAllRows();
						for (PuntuGeneral punGen : arrayTopTen)
							tmPuntu.añadirFila(punGen);
					} else
						tmPuntu.deleteAllRows();
				}
			}).start();

		}
	}

	public void itemStateChanged(ItemEvent e) {
		Object itemChanged = e.getSource();

		if (itemChanged == jchTotal) {
			if (e.getStateChange() == ItemEvent.SELECTED)
				jcNivel.setEnabled(false);
			else
				jcNivel.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		VentanaPuntuaciones v = new VentanaPuntuaciones();
		v.setVisible(true);
	}
}
