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

import bomberman.database.AccesoControles;
import bomberman.database.AccesoExtras;
import bomberman.managers.ControlPrincipal;

//Fig13a  Pag 18.
public class VentanaControles extends JDialog implements ActionListener {

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
	private JTextField jtDerechaUno;
	private JTextField jtIzquierdaUno;
	private JTextField jtArribaUno;
	private JTextField jtAbajoUno;
	private JTextField jtBombaUno;
	private JTextField jtDerechaDos;
	private JTextField jtIzquierdaDos;
	private JTextField jtArribaDos;
	private JTextField jtAbajoDos;
	private JTextField jtBombaDos;
	private JCheckBox jcEmail;
	private JCheckBox jcSonido;

	public VentanaControles() {
		// Inicializamos variables
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
		jtArribaUno = new JTextField(1);
		jtAbajoUno = new JTextField(1);
		jtDerechaUno = new JTextField(1);
		jtIzquierdaUno = new JTextField(1);
		jtBombaUno = new JTextField(1);
		jtArribaDos = new JTextField(1);
		jtAbajoDos = new JTextField(1);
		jtDerechaDos = new JTextField(1);
		jtIzquierdaDos = new JTextField(1);
		jtBombaDos = new JTextField(1);
		jcEmail = new JCheckBox();
		jcSonido = new JCheckBox();

		// Metemos las letras que tiene
		jtArribaUno.setText(Character.toString((char) AccesoControles
				.getControl("ARRIBA", 1)));
		jtAbajoUno.setText(Character.toString((char) AccesoControles
				.getControl("ABAJO", 1)));
		jtDerechaUno.setText(Character.toString((char) AccesoControles
				.getControl("DERECHA", 1)));
		jtIzquierdaUno.setText(Character.toString((char) AccesoControles
				.getControl("IZQUIERDA", 1)));
		jtBombaUno.setText(Character.toString((char) AccesoControles
				.getControl("BOMBA", 1)));
		jtArribaDos.setText(Character.toString((char) AccesoControles
				.getControl("ARRIBA", 2)));
		jtAbajoDos.setText(Character.toString((char) AccesoControles
				.getControl("ABAJO", 2)));
		jtDerechaDos.setText(Character.toString((char) AccesoControles
				.getControl("DERECHA", 2)));
		jtIzquierdaDos.setText(Character.toString((char) AccesoControles
				.getControl("IZQUIERDA", 2)));
		jtBombaDos.setText(Character.toString((char) AccesoControles
				.getControl("BOMBA", 2)));
		/*
		 * Ponemos el sonido y el envio de emails a
		 * gusto del usuario.
		 */
		jcEmail.setSelected(AccesoExtras.getExtra("email"));
		jcSonido.setSelected(AccesoExtras.getExtra("sonido"));

		// Layouts
		jpSuperior.setLayout(new FlowLayout());
		jpCentro.setLayout(new GridLayout(1, 2));
		jpCenIzq.setLayout(new BoxLayout(jpCenIzq, BoxLayout.Y_AXIS));
		jpCenDer.setLayout(new BoxLayout(jpCenDer, BoxLayout.Y_AXIS));
		jpCenIzq.setBorder(jug1);
		jpCenDer.setBorder(jug2);
		jpInferior.setLayout(new FlowLayout());
		jpGeneral.setLayout(new BorderLayout());
		jpGeneral2.setLayout(new FlowLayout());

		// Añadir componentes
		meterTecla("Arriba", jpCenIzq, jtArribaUno);
		meterTecla("Abajo", jpCenIzq, jtAbajoUno);
		meterTecla("Derecha", jpCenIzq, jtDerechaUno);
		meterTecla("Izquierda", jpCenIzq, jtIzquierdaUno);
		meterTecla("Bomba", jpCenIzq, jtBombaUno);
		meterTecla("Arriba", jpCenDer, jtArribaDos);
		meterTecla("Abajo", jpCenDer, jtAbajoDos);
		meterTecla("Derecha", jpCenDer, jtDerechaDos);
		meterTecla("Izquierda", jpCenDer, jtIzquierdaDos);
		meterTecla("Bomba", jpCenDer, jtBombaDos);
		meterCheckBox("Activar sonido", jpGeneral2, jcSonido);
		meterCheckBox("Activar envio emails", jpGeneral2, jcEmail);
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

		// Escuchadores
		jbGuardar.addActionListener(this);
		jbCancelar.addActionListener(this);

		// Características de la ventana
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

	private void meterTecla(String nom, Container cont, JTextField jtText) {
		JLabel tempLabel = new JLabel(nom);
		/*
		 * Para poder poner el tamaño que quiera de JTextField porque sino
		 * GridLayout no me deja.
		 */
		JPanel tempPanel = new JPanel();
		jtText.setColumns(2);
		tempPanel.add(tempLabel);
		tempPanel.add(jtText);
		cont.add(tempPanel);
	}

	private void meterCheckBox(String texto, Container cont, JCheckBox jcCheck) {
		JLabel tempLabel = new JLabel(texto);
		JPanel tempPanel = new JPanel();
		tempPanel.add(jcCheck);
		tempPanel.add(tempLabel);
		cont.add(tempPanel);
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		if (botonPulsado == jbGuardar) {
			//Actualizamos todos los parámetros
			AccesoControles.setControl("ARRIBA", 1, jtArribaUno.getText()
					.charAt(0));
			AccesoControles.setControl("ABAJO", 1,
					jtAbajoUno.getText().charAt(0));
			AccesoControles.setControl("DERECHA", 1, jtDerechaUno.getText()
					.charAt(0));
			AccesoControles.setControl("IZQUIERDA", 1, jtIzquierdaUno.getText()
					.charAt(0));
			AccesoControles.setControl("BOMBA", 1,
					jtBombaUno.getText().charAt(0));
			AccesoControles.setControl("ARRIBA", 2, jtArribaDos.getText()
					.charAt(0));
			AccesoControles.setControl("ABAJO", 2,
					jtAbajoDos.getText().charAt(0));
			AccesoControles.setControl("DERECHA", 2, jtDerechaDos.getText()
					.charAt(0));
			AccesoControles.setControl("IZQUIERDA", 2, jtIzquierdaDos.getText()
					.charAt(0));
			AccesoControles.setControl("BOMBA", 2,
					jtBombaDos.getText().charAt(0));

			//También los del Jugador.
			ControlPrincipal.getJugadorUno().setArriba(
					jtArribaUno.getText().charAt(0));
			ControlPrincipal.getJugadorUno().setAbajo(
					jtAbajoUno.getText().charAt(0));
			ControlPrincipal.getJugadorUno().setDerecha(
					jtDerechaUno.getText().charAt(0));
			ControlPrincipal.getJugadorUno().setIzquierda(
					jtIzquierdaUno.getText().charAt(0));
			ControlPrincipal.getJugadorUno().setBomba(
					jtBombaUno.getText().charAt(0));
			//Los del Jugador2 en caso de que exista.
			if (ControlPrincipal.getJugadorDos() != null) {
				ControlPrincipal.getJugadorDos().setArriba(
						jtArribaDos.getText().charAt(0));
				ControlPrincipal.getJugadorDos().setAbajo(
						jtAbajoDos.getText().charAt(0));
				ControlPrincipal.getJugadorDos().setDerecha(
						jtDerechaDos.getText().charAt(0));
				ControlPrincipal.getJugadorDos().setIzquierda(
						jtIzquierdaDos.getText().charAt(0));
				ControlPrincipal.getJugadorDos().setBomba(
						jtBombaDos.getText().charAt(0));
			}
			
			//Actualizamos email y sonido en la BD.
			AccesoExtras.setExtra("email", jcEmail.isSelected());
			AccesoExtras.setExtra("sonido", jcSonido.isSelected());
			ControlPrincipal.getJugadorUno().setSonido(jcSonido.isSelected());
			ControlPrincipal.getJugadorUno().setQuiereEmail(jcEmail.isSelected());
			GestorVentana.ocultarVentana(VentanaControles.class);

		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaControles.class);
		}
	}

	public static void main(String[] args) {
		VentanaControles prueba = new VentanaControles();
		prueba.setVisible(true);
	}
}
