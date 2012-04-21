package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bomberman.enumeraciones.ModoJuego;
import bomberman.jugador.Jugador;
import bomberman.managers.ControlPrincipal;

public class VentanaDirecto extends JDialog implements ActionListener {

	private JComboBox jcbNivel;
	private JPasswordField jtPass;
	private JLabel jlTexto;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel jpMedio;
	private Jugador jugador;
	private JPanel jpInferior;

	public VentanaDirecto(Jugador jug) {
		String[] arrayCombo = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10" };
		jcbNivel = new JComboBox(arrayCombo);
		jtPass = new JPasswordField(12);
		jlTexto = new JLabel("Seleccione el nivel y escriba la contrase�a:");
		btAceptar = new JButton("Aceptar");
		btCancelar = new JButton("Cancelar");
		jpMedio = new JPanel();
		jpInferior = new JPanel();
		this.jugador = jug;

		// Layout
		getContentPane().setLayout(new BorderLayout());
		jpMedio.setLayout(new BoxLayout(jpMedio, BoxLayout.Y_AXIS));
		jpInferior.setLayout(new FlowLayout());

		// A�adimos componentes
		jpInferior.add(btAceptar);
		jpInferior.add(btCancelar);
		posicionaComp(jlTexto, "North");
		posicionaComp(jpInferior, "South");
		jpMedio.add(posicionaLinea("Nivel: ", jcbNivel));
		jpMedio.add(posicionaLinea("Contrase�a: ", jtPass));
		getContentPane().add(jpMedio);
		
		//A�adir escuchador
		btAceptar.addActionListener(this);
		btCancelar.addActionListener(this);

		// btAceptar.setBorder( BorderFactory.createEmptyBorder( 3, 3, 3, 3 ) );

		// Determinamos las caracter�sticas de la ventana.
		this.setSize(280, 200);
		this.setTitle("Acceso a nivel de forma directa");
		/*
		 * Determinamos un tama�o m�nimo de la ventana aunque dejamos que tenga
		 * un tama�o m�ximo. De esta manera puede seguir funcionando a la
		 * perfecci�n en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(50, 50));
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
	 * Nos a�ade un componente al contenedor principal de forma centrada.
	 * 
	 * @param componente
	 *            - Component. Container donde hay que meter los componentes.
	 */
	private void posicionaComp(Component componente, String lado) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(componente);
		getContentPane().add(panelCompl, lado);
	}

	/**
	 * M�todo que nos sirve para meter en cada celda del Layout BoxLayout un
	 * panel. De esta forma nos evitamos tener que hacerlo todo manualmente.
	 * 
	 * @param cont
	 *            - Container. El contenedor donde hay que meter los
	 *            componentes.
	 * @param etiqueta
	 *            - String. Se crear� un JLabel con cada String.
	 * @param campo
	 *            - JTextField. Es un JTextField donde se meter�n los datos.
	 */
	private JPanel posicionaLinea(String etiqueta, Component campo) {
		JPanel tempPanel = new JPanel();
		// Queremos que este alineado hacia la izquierda.
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.add(new JLabel(etiqueta));
		tempPanel.add(campo);
		return tempPanel;
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		if (botonPulsado == btAceptar) {
			new Thread(
            		new Runnable() {
            			public void run() {
            				jugador.setModo(ModoJuego.Master);
            				ControlPrincipal.crearEscenario("mapa"
            						+ Integer.parseInt((String) jcbNivel.getSelectedItem())
            						+ ".txt");
            				//Falta la contrase�a
            				GestorVentana.hacerVisible(VentanaJuego.class, true);
            			}
            		}
            ).start();
		}else if(botonPulsado == btCancelar){
			new Thread(
            		new Runnable() {
            			public void run() {
            				GestorVentana.ocultarVentana(VentanaDirecto.class);
            				GestorVentana.hacerVisible(VentanaInicial.class, true);
            			}
            		}
            ).start();
		}
	}

	public static void main(String[] args) {
		VentanaDirecto prueba = new VentanaDirecto(null);
		prueba.setVisible(true);
	}
}
