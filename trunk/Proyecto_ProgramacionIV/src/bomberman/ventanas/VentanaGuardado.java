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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.managers.ControlPrincipal;
import bomberman.outin.ManipuladorFecha;

//Fig 11  P�gina 15.  Tiene una JTable.
public class VentanaGuardado extends JDialog implements ActionListener {

	private JTable jtPuntu;
	private JLabel jlTexto;
	private JButton jbGuardar;
	private JButton jbSobreescribir;
	private JButton jbCancelar;
	private JPanel jpInferior;
	private TableModelCargar tmModel;
	private TableRowSorter<TableModel> ordenarTabla;
	private JScrollPane jsTabla;

	public VentanaGuardado() {
		// Inicializamos las variables
		jlTexto = new JLabel("Partidas guardadas");
		jbGuardar = new JButton("Guardar nueva partida");
		jbSobreescribir = new JButton("Guardar partida sobrescribiendo");
		jbCancelar = new JButton("Cancelar");
		jpInferior = new JPanel();
		tmModel = new TableModelCargar(0, 8);
		tmModel.setColumnIdentifiers(new String[] { "C�digo", "Nombre",
				"Apellido", "Nick", "Puntu", "Nivel", "Fecha", "Vidas" });
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

	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		// En caso de que queramos cargar la partida seleccionada.
		if (botonPulsado == jbGuardar) {
			AccesoPuntuGen.insertarPunt(new PuntuGeneral(ControlPrincipal
					.getJugadorUno().getCodPart(), ControlPrincipal
					.getJugadorUno().getCodJugador(), true, ControlPrincipal
					.getJugadorUno().getPuntuacion(), ManipuladorFecha
					.getFecha(), ControlPrincipal.getJugadorUno().getVidas()));
			GestorVentana.hacerVisible(VentanaSeguir.class, false);
			GestorVentana.ocultarVentana(VentanaGuardado.class);
		} else if (botonPulsado == jbSobreescribir) {
			int fila = jtPuntu.getSelectedRow();
			PuntuGeneral seleccionada;
			// Si se ha seleccionado alguna fila
			if (fila != -1) {
				seleccionada = tmModel.getFila(fila);
				// Cargamos la partida
				AccesoPuntuGen.eliminarPunt(seleccionada.getCod_punt());
				AccesoPuntuGen.insertarPunt(seleccionada);
			} else {
				JOptionPane.showMessageDialog(new JDialog(),
						"No has seleccionado ninguna fila", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaCargar.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}

	public void setVisible(boolean estado) {
		super.setVisible(estado);
		tmModel.deleteAllRows();
		ArrayList<PuntuGeneral> partGuardas = AccesoPuntuGen
				.getPartidasGuardadas();
		for (PuntuGeneral tempPunt : partGuardas)
			tmModel.a�adirFila(tempPunt);
	}
}