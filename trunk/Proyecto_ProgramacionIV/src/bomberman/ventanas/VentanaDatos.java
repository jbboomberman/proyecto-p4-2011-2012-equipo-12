package bomberman.ventanas;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import bomberman.managers.ControlPrincipal;
import bomberman.managers.ManagerSonido;
import bomberman.outin.*;
import bomberman.database.*;

/**
 * Clase VentanaDatos que hereda de JDialog y su proposito es dejar al usuario
 * meter sus datos personales.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaDatos extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JPanel panelMedio;
	private JPanel panelInferior;
	private JLabel jlTexto;
	private JTextField nom;
	private JTextField ape;
	private JTextField email;
	private JTextField nic;
	private JButton botonAceptar;
	private JButton botonCancelar;

	/**
	 * Constructor principal de la clase VentanaDatos.
	 */
	public VentanaDatos() {
		// Inicializamos los objetos.
		panelMedio = new JPanel();
		panelInferior = new JPanel();
		jlTexto = new JLabel(
				"Inserte la información para que podamos guardar los datos de sus partidas: ");
		nom = new JTextField(12);
		ape = new JTextField(20);
		email = new JTextField(20);
		nic = new JTextField(12);
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		// Mensaje al poner el ratón encima del botonAceptar.
		botonAceptar.setToolTipText("¿Te atreves a jugar?");

		/*
		 * Layouts
		 */
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
		/*
		 * La primera frase del panel estará alineada hacia el centro.
		 */
		panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));
		// Queremos que los componentes esten de arriba a abajo y desplazados a
		// la izquierda.
		posicionaLinea(panelMedio, "Nombre: ", nom);
		posicionaLinea(panelMedio, "Apellidos: ", ape);
		posicionaLinea(panelMedio, "Email: ", email);
		posicionaLinea(panelMedio, "Nick: ", nic);

		// El botón Aceptar debe escuchar eventos.
		botonAceptar.addActionListener(this);
		botonCancelar.addActionListener(this);

		/*
		 * Limitamos el número de caracteres que puede tener el JTextField,
		 * también decidimos si queremos dejar poner números o no.
		 */
		// Sin números
		nom.setDocument(new LimitadorCaracteres(nom, 12, false));
		ape.setDocument(new LimitadorCaracteres(ape, 20, false));
		// Con números
		nic.setDocument(new LimitadorCaracteres(nic, 12, true));
		email.setDocument(new LimitadorCaracteres(email, 30, true));

		/*
		 * Añadimos el panel al Contenedor principal.
		 */
		panelInferior.add(botonAceptar);
		panelInferior.add(botonCancelar);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelMedio, "Center");
		posicionaComp(panelInferior, "South");
		posicionaComp(jlTexto, "North");
		posicionaComp(Box.createRigidArea(new Dimension(50, 20)), "West");
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(350, 200));
		this.setSize(500, 270);
		this.setTitle("Introducir datos");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Implementamos el método 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. El evento que se ha producido.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		// Si el boton pulsado erá botonAceptar
		if (botonPulsado == botonAceptar) {
			if (nom.getText().equals("") || ape.getText().equals("")
					|| nic.getText().equals("") || email.equals("")) {
				// No queremos que quede ningún campo se quede sin rellenar.
				JOptionPane.showMessageDialog(this,
						"No ha rellenado todos los campos", "Error",
						JOptionPane.WARNING_MESSAGE);
				// Si el formato del correo no es correcto
			} else if (!CompCorreo.isEmail(email.getText())) {
				JOptionPane.showMessageDialog(this,
						"El formato de email es incorrecto", "Error",
						JOptionPane.WARNING_MESSAGE);
			} else {
				//Si ese jugador no está en la BD lo metemos
				if (AccesoJugador.getJugador(nom.getText(), ape.getText(),
						nic.getText(), email.getText()) == null) {
					//Añadimos un Jugador a la BD.
					bomberman.jugador.Jugador tempJug = new bomberman.jugador.Jugador(
							nom.getText(), ape.getText(), nic.getText(),
							email.getText(), 3, 0, ControlPrincipal
									.getJugadorUno().getNivel(),
							ControlPrincipal.getJugadorUno().getModo(),
							AccesoJugador.getNumJug(),
							AccesoPuntuGen.getNumPunt(),
							AccesoControles.getControl("DERECHA", 1),
							AccesoControles.getControl("IZQUIERDA", 1),
							AccesoControles.getControl("ARRIBA", 1),
							AccesoControles.getControl("ABAJO", 1),
							AccesoControles.getControl("BOMBA", 1),
							AccesoExtras.getExtra("sonido"),
							AccesoExtras.getExtra("email"));
					/*
					 * Actualizamos el Jugador al cual se le actualizará
					 * la puntuación.
					 */
					ControlPrincipal.setJugadorUno(tempJug);
					AccesoJugador
							.insertarJugador(new bomberman.database.Jugador(
									AccesoJugador.getNumJug(), nom.getText(),
									ape.getText(), nic.getText(), email
											.getText()));
				//En caso de que el Jugador exista en la BD.
				} else {
					bomberman.database.Jugador jugBase = AccesoJugador
							.getJugador(nom.getText(), ape.getText(),
									nic.getText(), email.getText());
					bomberman.jugador.Jugador tempJug = new bomberman.jugador.Jugador(
							jugBase.getNomJugador(), jugBase.getApellJugador(),
							jugBase.getNickJugador(), jugBase.getEmail(), 3, 0,
							ControlPrincipal.getJugadorUno().getNivel(),
							ControlPrincipal.getJugadorUno().getModo(),
							jugBase.getCod_jugador(),
							AccesoPuntuGen.getNumPunt(),
							AccesoControles.getControl("DERECHA", 1),
							AccesoControles.getControl("IZQUIERDA", 1),
							AccesoControles.getControl("ARRIBA", 1),
							AccesoControles.getControl("ABAJO", 1),
							AccesoControles.getControl("BOMBA", 1),
							AccesoExtras.getExtra("sonido"),
							AccesoExtras.getExtra("email"));
					/*
					 * Actualizamos el Jugador al cual se le actualizará
					 * la puntuación.
					 */
					ControlPrincipal.setJugadorUno(tempJug);
					// Limpiamos los datos
					nom.setText("");
					ape.setText("");
					nic.setText("");
					email.setText("");
				}
				GestorVentana.hacerVisible(VentanaJuego.class, true);
			}
		} else if (botonPulsado == botonCancelar) {
			if (((VentanaControles) GestorVentana
					.getVentana(VentanaControles.class)).getSonido()) {
				try {
					ManagerSonido.pararLoop();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			GestorVentana.ocultarVentana(VentanaDatos.class);
		}
	}

	/**
	 * Método que nos sirve para meter en cada celda del Layout BoxLayout un
	 * panel. De esta forma nos evitamos tener que hacerlo todo manualmente.
	 * 
	 * @param cont
	 *            - Container. El contenedor donde hay que meter los
	 *            componentes.
	 * @param etiqueta
	 *            - String. Se creará un JLabel con cada String.
	 * @param campo
	 *            - JTextField. Es un JTextField donde se meterán los datos.
	 */
	private void posicionaLinea(Container cont, String etiqueta,
			JTextField campo) {
		JPanel tempPanel = new JPanel();
		// Queremos que este alineado hacia la izquierda.
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.add(new JLabel(etiqueta));
		tempPanel.add(campo);
		cont.add(tempPanel);
	}

	/**
	 * Nos añade un componente al contenedor principal de forma centrada.
	 * 
	 * @param componente
	 *            - Component. Container donde hay que meter los componentes.
	 */
	private void posicionaComp(Component componente, String sitio) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(componente);
		getContentPane().add(panelCompl, sitio);
	}
}
