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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.managers.ControlPrincipal;

public class VentanaSuperado extends JDialog implements ActionListener{

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

		// Layouts
		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new BoxLayout(pSuperior, BoxLayout.Y_AXIS));
		pInferior.setLayout(new BoxLayout(pInferior, BoxLayout.Y_AXIS));
		pInfSur.setLayout(new FlowLayout(FlowLayout.CENTER));

		// Añadir componentes
		pSuperior.add(posicionaComp("Nick: "));
		pSuperior.add(posicionaComp("Nivel: "));
		pSuperior.add(posicionaComp("Puntuación: "));
		pSuperior.add(posicionaComp("Password: "));
		pInfNorte.add(jbGuardarPart);
		pInfSur.add(jbPasarNivel);
		pInfSur.add(jbVolverMenu);
		pInferior.add(pInfNorte);
		pInferior.add(pInfSur);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(jlMensaje, BorderLayout.CENTER);
		pPrincipal.add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pPrincipal);

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
	private JPanel posicionaComp(String nombre) {
		JPanel panelCompl = new JPanel();
		// Queremos que este en el centro.
		panelCompl.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompl.add(new JLabel(nombre));
		return panelCompl;
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber dónde se originó el evento creamos un Object con la
		 * dirección del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbGuardarPart){
			Calendar tempCalendar = Calendar.getInstance();
			AccesoPuntuGen.insertarPunt(new PuntuGeneral(ControlPrincipal.getJugadorUno().getCodPart(), 
					ControlPrincipal.getJugadorUno().getCodJugador(), true, 
					ControlPrincipal.getJugadorUno().getPuntuacion(),
					new String(tempCalendar.get(Calendar.YEAR) + "" + tempCalendar.get(Calendar.MONTH)
							+ "" + tempCalendar.get(Calendar.DAY_OF_MONTH))));
			GestorVentana.ocultarVentana(VentanaSuperado.class);
			GestorVentana.hacerVisible(VentanaSeguir.class, true);
		}else if (botonPulsado == jbPasarNivel){
			ControlPrincipal.crearEscenario(ControlPrincipal.getJugadorUno().getNivel() + 1);
			ControlPrincipal.getJugadorUno().setNivel(ControlPrincipal.getJugadorUno().getNivel());
			GestorVentana.hacerVisible(VentanaJuego.class, true);
		}else if(botonPulsado == jbVolverMenu){
			Calendar tempCalendar = Calendar.getInstance();
			AccesoPuntuGen.insertarPunt(new PuntuGeneral(ControlPrincipal.getJugadorUno().getCodPart(), 
					ControlPrincipal.getJugadorUno().getCodJugador(), false, 
					ControlPrincipal.getJugadorUno().getPuntuacion(),
					new String(tempCalendar.get(Calendar.YEAR) + "" + tempCalendar.get(Calendar.MONTH)
							+ "" + tempCalendar.get(Calendar.DAY_OF_MONTH))));
			GestorVentana.ocultarVentana(VentanaSuperado.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
		
	}
	public static void main(String[] args) {
		VentanaSuperado prueba = new VentanaSuperado();
		prueba.setVisible(true);
	}
}
