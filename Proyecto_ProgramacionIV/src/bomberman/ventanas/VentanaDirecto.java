package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import bomberman.database.AccesoMapa;
import bomberman.database.AccesoNivel;
import bomberman.enumeraciones.ModoJuego;
import bomberman.managers.ControlPrincipal;

/**
 * Ventana que nos permitir� ir directamente al nivel
 * que seleccionemos en caso de que escribamos la contrase�a
 * correctamente.
 * @author David
 * @version 1.0
 */
public class VentanaDirecto extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JComboBox jcbNivel;
	private JPasswordField jtPass;
	private JLabel jlTexto;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel jpMedio;
	private JPanel jpInferior;

	/**
	 * Constructor principal de la clase VentanaDirecto
	 */
	public VentanaDirecto() {
		String[] arrayCombo = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10" };
		jcbNivel = new JComboBox(arrayCombo);
		jtPass = new JPasswordField(12);
		jlTexto = new JLabel("Seleccione el nivel y escriba la contrase�a:");
		btAceptar = new JButton("Aceptar");
		btCancelar = new JButton("Cancelar");
		jpMedio = new JPanel();
		jpInferior = new JPanel();

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

		// A�adir escuchador
		btAceptar.addActionListener(this);
		btCancelar.addActionListener(this);

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

	/**
	 * Implementamos el m�todo 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. El evento que se ha producido.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		//Si pulsamos el bot�n Aceptar
		if (botonPulsado == btAceptar) {
			/*
			 * Creamos un nuevo Thread para que el b�ton
			 * y Swing no se queden paralizados mientras
			 * hacemos las operaciones.
			 */
			new Thread(new Runnable() {
				public void run() {
					//Si el password es correcto se deja pasar
					if (AccesoNivel.esCorrecto(
							new String(jtPass.getPassword()), Integer
									.parseInt((String) jcbNivel
											.getSelectedItem()))) {
						ControlPrincipal.getJugadorUno().setModo(
								ModoJuego.Master);
						ControlPrincipal.getJugadorUno().setNivel(
								Integer.parseInt((String) jcbNivel
										.getSelectedItem()));
						ControlPrincipal.crearEscenario(AccesoMapa
								.getCodMapa(Integer.parseInt((String) jcbNivel
										.getSelectedItem())));
						GestorVentana.ocultarVentana(VentanaDirecto.class);
						GestorVentana.hacerVisible(VentanaDatos.class, false);
						jtPass.setText("");
					//Si no es correcto mensaje error
					} else {
						JOptionPane.showMessageDialog(new JDialog(),
								"La contrase�a no es correcta", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}).start();
		//Volvemos al men�
		} else if (botonPulsado == btCancelar) {
			new Thread(new Runnable() {
				public void run() {
					GestorVentana.ocultarVentana(VentanaDirecto.class);
					GestorVentana.hacerVisible(VentanaInicial.class, true);
				}
			}).start();
		}
	}
}
