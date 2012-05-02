package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import bomberman.outin.ManipuladorFecha;

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

	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		if (botonPulsado == jbGuardarPart) {
			GestorVentana.hacerVisible(VentanaGuardado.class, false);
		} else if (botonPulsado == jbPasarNivel) {
			ControlPrincipal.getJugadorUno().setNivel(
					ControlPrincipal.getJugadorUno().getNivel() + 1);
			ControlPrincipal.crearEscenario(ControlPrincipal.getJugadorUno()
					.getNivel());
			GestorVentana.hacerVisible(VentanaJuego.class, true);
		} else if (botonPulsado == jbVolverMenu) {
			Calendar tempCalendar = Calendar.getInstance();
			if(ControlPrincipal.getJugadorUno().getModo() == ModoJuego.Historia){
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

	public JLabel getJlNick() {
		return jlNick;
	}

	public void setJlNick(String jlNick) {
		this.jlNick.setText(jlNick);
	}

	public JLabel getJlNivel() {
		return jlNivel;
	}

	public void setJlNivel(String jlNivel) {
		System.out.println("Entre");
		this.jlNivel.setText(jlNivel);
	}

	public JLabel getJlPuntuacion() {
		return jlPuntuacion;
	}

	public void setJlPuntuacion(String jlPuntuacion) {
		this.jlPuntuacion.setText(jlPuntuacion);
	}

	public String getJlPassword() {
		return jlPassword.getText();
	}

	public void setJlPassword(String jlPassword) {
		this.jlPassword.setText(jlPassword);
	}
	
	
	
	public void setBotonPasarEnabled(boolean estado){
		this.jbPasarNivel.setEnabled(estado);
	}
	
	public void setBotonGuardarEnabled(boolean estado){
		this.jbGuardarPart.setEnabled(estado);
	}

	public static void main(String[] args) {
		VentanaSuperado prueba = new VentanaSuperado();
		prueba.setBotonPasarEnabled(false);
		prueba.setVisible(true);
	}
}
