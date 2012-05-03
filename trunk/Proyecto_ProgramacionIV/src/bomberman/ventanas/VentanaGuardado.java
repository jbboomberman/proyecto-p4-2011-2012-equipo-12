package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.managers.ControlPrincipal;
import bomberman.outin.ManipuladorFecha;

/**
 * Ventana que nos servir� para guardar las partidas.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaGuardado extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JTable jtPuntu;
	private JLabel jlTexto;
	private JButton jbGuardar;
	private JButton jbSobreescribir;
	private JButton jbCancelar;
	private JPanel jpInferior;
	private TableModelCargar tmModel;
	private JScrollPane jsTabla;

	/**
	 * Constructor principal de la clase VentanaGuardado
	 */
	public VentanaGuardado() {
		// Inicializamos las variables
		jlTexto = new JLabel("Partidas guardadas");
		jbGuardar = new JButton("Guardar nueva partida");
		jbSobreescribir = new JButton("Guardar partida sobrescribiendo");
		jbCancelar = new JButton("Cancelar");
		jpInferior = new JPanel();
		tmModel = new TableModelCargar(0, 8);
		tmModel.setColumnIdentifiers(new String[] { "C�digo", "Nombre",
				"Apellido", "Nick", "Puntu", "Nivel", "Fecha", "CodJugador",
				"Vidas" });
		jtPuntu = new JTable(tmModel);
		jsTabla = new JScrollPane(jtPuntu);

		// Layouts
		getContentPane().setLayout(new BorderLayout());
		jpInferior.setLayout(new FlowLayout());
		jlTexto.setHorizontalAlignment(SwingConstants.CENTER);

		// A�adir componentes
		jpInferior.add(jbGuardar);
		jpInferior.add(jbSobreescribir);
		jpInferior.add(jbCancelar);
		getContentPane().add(jlTexto, BorderLayout.NORTH);
		getContentPane().add(jsTabla, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);
		jlTexto.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

		// Escuchadores
		jbGuardar.addActionListener(this);
		jbSobreescribir.addActionListener(this);
		jbCancelar.addActionListener(this);

		// Determinamos que tama�os tendr� cada columna
		dise�arColumnas("C�digo", 150, 250);
		dise�arColumnas("Nombre", 150, 250);
		dise�arColumnas("Apellido", 100, 150);
		dise�arColumnas("Nick", 150, 250);
		dise�arColumnas("Puntu", 100, 150);
		dise�arColumnas("Nivel", 50, 75);
		dise�arColumnas("Fecha", 150, 200);
		dise�arColumnas("CodJugador", 50, 75);
		dise�arColumnas("Vidas", 50, 75);

		// Par�metros
		jlTexto.setFont(new Font("sansserif", Font.BOLD, 20));
		this.setSize(600, 400);
		this.setResizable(false);
		this.setTitle("Guardar partida");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);

	}

	/**
	 * M�todo dise�ado para especificar el tama�o preferido y m�ximo de cada
	 * columana del objeto JTable.
	 * 
	 * @param nom
	 *            - String, nombre de la columna
	 * @param prefTam
	 *            - int, tama�o preferido
	 * @param maxTam
	 *            - int, tama�o m�ximo
	 */
	private void dise�arColumnas(String nom, int prefTam, int maxTam) {
		/*
		 * Manejamos la excepci�n IllegalArgumentException ya que si el
		 * par�metro nombre que se pasa a la entrada de la funci�n no es
		 * correcto se pueden generar problemas.
		 */
		try {
			jtPuntu.getColumn(nom).setPreferredWidth(prefTam);
			jtPuntu.getColumn(nom).setMaxWidth(maxTam);
		} catch (IllegalArgumentException e) {
			JOptionPane
					.showMessageDialog(
							new JDialog(),
							"Error al intentar crear el JTable, el dise�o no ha sido predeterminado",
							"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Implementamos el m�todo 'actionPerformed' del interface ActionListener.
	 * 
	 * @param e
	 *            - ActionEvent. Que evento ha tenido lugar.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		// En caso de que queramos guardar la partida seleccionada.
		if (botonPulsado == jbGuardar) {
			AccesoPuntuGen.insertarPunt(new PuntuGeneral(ControlPrincipal
					.getJugadorUno().getCodPart(), ControlPrincipal
					.getJugadorUno().getCodJugador(), true, ControlPrincipal
					.getJugadorUno().getPuntuacion(), ManipuladorFecha
					.getFecha(), ControlPrincipal.getJugadorUno().getVidas(),
					ControlPrincipal.getJugadorUno().getNivel()));
			GestorVentana.hacerVisible(VentanaSeguir.class, false);
			GestorVentana.ocultarVentana(VentanaGuardado.class);
			// Sobreescibir una partida
		} else if (botonPulsado == jbSobreescribir) {
			int fila = jtPuntu.getSelectedRow();
			PuntuGeneral seleccionada;
			// Si se ha seleccionado alguna fila
			if (fila != -1) {
				// Obtenemos la fila seleccionada
				seleccionada = tmModel.getFila(fila);
				// Guardamos el c�digo de la partida a eliminar
				int codPart = seleccionada.getCod_punt();
				// Eliminamos la puntuaci�n anterior
				AccesoPuntuGen.eliminarPunt(seleccionada.getCod_punt());
				// Insertamos la nueva puntuaci�n con el mismo c�digo
				AccesoPuntuGen.insertarPunt((new PuntuGeneral(codPart,
						ControlPrincipal.getJugadorUno().getCodJugador(), true,
						ControlPrincipal.getJugadorUno().getPuntuacion(),
						ManipuladorFecha.getFecha(), ControlPrincipal
								.getJugadorUno().getVidas(), ControlPrincipal
								.getJugadorUno().getNivel())));
				GestorVentana.hacerVisible(VentanaSeguir.class, false);
				GestorVentana.ocultarVentana(VentanaGuardado.class);
			} else {
				JOptionPane.showMessageDialog(new JDialog(),
						"No has seleccionado ninguna fila", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Volver al men�
		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaCargar.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}

	/**
	 * Sobrescribimos el m�todo visible que borrar�
	 * las filas anteriores y actualizar� el contenido
	 * actual.
	 */
	public void setVisible(boolean estado) {
		super.setVisible(estado);
		if (estado) {
			//Borramos las filas anteriores
			tmModel.deleteAllRows();
			//Cargamos las de ahora
			ArrayList<PuntuGeneral> partGuardas = AccesoPuntuGen
					.getPartidasGuardadas();
			for (PuntuGeneral tempPunt : partGuardas)
				tmModel.a�adirFila(tempPunt);
		}
	}
}
