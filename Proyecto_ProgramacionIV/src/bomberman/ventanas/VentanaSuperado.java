package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.enumeraciones.ModoJuego;
import bomberman.managers.ControlPrincipal;

/**
 * Clase que representa la ventana VentanaSuperado que aprecerá
 * cuando superemos un nivel.
 * @author David
 * @version 1.0
 */
public class VentanaSuperado extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JButton jbGuardarPart;
	private JButton jbPasarNivel;
	private JButton jbVolverMenu;
	private JPanel pSuperior;
	private JPanel pInferior;
	private JPanel pInfNorte;
	private JPanel pInfSur;
	private JPanel pPrincipal;
	private JLabel jlMensaje;
	private JLabel jlNick;
	private JLabel jlNivel;
	private JLabel jlPuntuacion;
	private JLabel jlPassword;

	/**
	 * Constructor principal de la clase VentanaSuperado.
	 */
	public VentanaSuperado() {
		jbGuardarPart = new JButton("Guardar partida");
		jbPasarNivel = new JButton("Pasar al siguiente nivel");
		jbVolverMenu = new JButton("Volver al menú");
		pSuperior = new JPanel();
		pInferior = new JPanel();
		pInfNorte = new JPanel();
		pInfSur = new JPanel();
		pPrincipal = new JPanel();
		jlMensaje = new JLabel(
				"<html>¡Enhorabuena, has conseguido superar el nivel!<P>");
		jlNick = new JLabel();
		jlNivel = new JLabel();
		jlPuntuacion = new JLabel();
		jlPassword = new JLabel();

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new BoxLayout(pInferior, BoxLayout.Y_AXIS));
		pInfSur.setLayout(new FlowLayout(FlowLayout.CENTER));

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: ", jlNick));
		pSuperior.add(posicionaComp("Nivel: ", jlNivel));
		pSuperior.add(posicionaComp("Puntuación: ", jlPuntuacion));
		pSuperior.add(posicionaComp("Password: ", jlPassword));
		pInfNorte.add(jbGuardarPart);
		pInfSur.add(jbPasarNivel);
		pInfSur.add(jbVolverMenu);
		pInferior.add(pInfNorte);
		pInferior.add(pInfSur);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 200)),
				BorderLayout.WEST);
		pPrincipal.add(Box.createRigidArea(new Dimension(20, 200)),
				BorderLayout.EAST);
		getContentPane().add(pPrincipal);

		// Escuchadores
		jbGuardarPart.addActionListener(this);
		jbPasarNivel.addActionListener(this);
		jbVolverMenu.addActionListener(this);

		// Carcterísticas de la ventana
		/*
		 * Determinamos un tamaño mínimo de la ventana aunque dejamos que tenga
		 * un tamaño máximo. De esta manera puede seguir funcionando a la
		 * perfección en distintas resoluciones.
		 */
		this.setMinimumSize(new Dimension(200, 200));
		this.setSize(300, 250);
		this.setTitle("Nivel superado");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		Insets insets = this.getInsets();
		pPrincipal.setBorder(BorderFactory.createEmptyBorder(insets.top,
				((insets.left >= 2) ? insets.left - 2 : insets.left),
				insets.bottom, insets.right));
	}

	/**
	 * Nos añade un componente al contenedor principal de forma centrada.
	 * 
	 * @param componente
	 *            - Component. Container donde hay que meter los componentes.
	 */
	private JPanel posicionaComp(String nombre, JLabel jlDato) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(new JLabel(nombre));
		panelCompl.add(jlDato);
		return panelCompl;
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

		//Guardar partida
		if (botonPulsado == jbGuardarPart) {
			GestorVentana.hacerVisible(VentanaGuardado.class, false);
		//Pasar al siguiente nivel
		} else if (botonPulsado == jbPasarNivel) {
			ControlPrincipal.getJugadorUno().setNivel(
					ControlPrincipal.getJugadorUno().getNivel() + 1);
			ControlPrincipal.crearEscenario(ControlPrincipal.getJugadorUno()
					.getNivel());
			GestorVentana.hacerVisible(VentanaJuego.class, true);
		//Volver al menú
		} else if (botonPulsado == jbVolverMenu) {
			Calendar tempCalendar = Calendar.getInstance();
			if(ControlPrincipal.getJugadorUno().getModo() == ModoJuego.Historia){
			//Guardamos la puntuación general
			AccesoPuntuGen.insertarPunt(new PuntuGeneral(ControlPrincipal
					.getJugadorUno().getCodPart(), ControlPrincipal
					.getJugadorUno().getCodJugador(), false, ControlPrincipal
					.getJugadorUno().getPuntuacion(), new String(tempCalendar
					.get(Calendar.YEAR)
					+ ""
					+ tempCalendar.get(Calendar.MONTH)
					+ "" + tempCalendar.get(Calendar.DAY_OF_MONTH)),
					ControlPrincipal.getJugadorUno().getVidas(), -1));
			}
			GestorVentana.ocultarVentana(VentanaSuperado.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}

	}

	/**
	 * Nos devuelve el JLabel del nick.
	 * @return jlNick - JLabel
	 */
	public JLabel getJlNick() {
		return jlNick;
	}

	/**
	 * Modifica el JLabel del nick. Recibe como
	 * parámetro un String.
	 * @param jlNick - String
	 */
	public void setJlNick(String jlNick) {
		this.jlNick.setText(jlNick);
	}

	/**
	 * Devuelve el JLabel de nivel.
	 * @return jlNivel - JLabel
	 */
	public JLabel getJlNivel() {
		return jlNivel;
	}

	/**
	 * Modifica el JLabel de nivel. Recibe un String
	 * como parámetro.
	 * @param jlNivel
	 */
	public void setJlNivel(String jlNivel) {
		this.jlNivel.setText(jlNivel);
	}

	/**
	 * Devuelve el JLabel de la puntuación.
	 * @return jlPuntuacion - JLabel
	 */
	public JLabel getJlPuntuacion() {
		return jlPuntuacion;
	}

	/**
	 * Modifica el JLabel de puntuaciones.
	 * Recibe como parámetro un String.
	 * @param jlPuntuacion - String
	 */
	public void setJlPuntuacion(String jlPuntuacion) {
		this.jlPuntuacion.setText(jlPuntuacion);
	}

	/**
	 * Devuelve el valor de el JTextField de password.
	 * @return String - pass
	 */
	public String getJlPassword() {
		return jlPassword.getText();
	}

	/**
	 * Modifica el texto del JTextField de password.
	 * @param jlPassword - String
	 */
	public void setJlPassword(String jlPassword) {
		this.jlPassword.setText(jlPassword);
	}
	
	/**
	 * Habilita o deshabilita el botón 'Pasar'
	 * @param estado - boolean
	 */
	public void setBotonPasarEnabled(boolean estado){
		this.jbPasarNivel.setEnabled(estado);
	}
	
	/**
	 * Habilita o deshabilita el botón 'Guardar'
	 * @param estado - boolean
	 */
	public void setBotonGuardarEnabled(boolean estado){
		this.jbGuardarPart.setEnabled(estado);
	}
}
