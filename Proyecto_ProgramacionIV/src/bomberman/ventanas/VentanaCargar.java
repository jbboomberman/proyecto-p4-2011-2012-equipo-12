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

/**
 * Esta clase representa la ventana que ofrecer� las partidas a cargar.
 * 
 * @author David
 * @version 1.0
 */
public class VentanaCargar extends JDialog implements ActionListener {

	private static final long serialVersionUID = -7461719037402108362L;
	private JTable jtPuntu;
	private JLabel jlTexto;
	private JButton jbCargar;
	private JButton jbCancelar;
	private JPanel jpInferior;
	private TableModelCargar tmModel;
	private JScrollPane jsTabla;

	/**
	 * Constructor principal de la VentanaCargar
	 */
	public VentanaCargar() {
		// Inicializamos las variables
		jlTexto = new JLabel("Partidas guardadas");
		jbCargar = new JButton("Cargar partida");
		jbCancelar = new JButton("Cancelar");
		jpInferior = new JPanel();
		tmModel = new TableModelCargar(0, 8);
		// Columnas que habr�
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
		jpInferior.add(jbCargar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jlTexto, BorderLayout.NORTH);
		getContentPane().add(jsTabla, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);
		// Para mantener m�rgenes
		jlTexto.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

		// Escuchadores
		jbCargar.addActionListener(this);
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

		// Par�metros de la ventana.
		jlTexto.setFont(new Font("sansserif", Font.BOLD, 20));
		this.setSize(600, 400);
		this.setResizable(false);
		this.setTitle("Cargar partida");
		this.setModal(true);
		// Para que el hilo de Swing acabe cuando cerremos la ventana.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Para que la ventana aparezca centrada en pantalla.
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * M�todo dise�ado para especificar el tama�o preferido y m�ximo de cada
	 * columna del objeto JTable.
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
	 * M�todo de la interfaz ActionListener.
	 */
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();

		// En caso de que queramos cargar la partida seleccionada.
		if (botonPulsado == jbCargar) {
			int fila = jtPuntu.getSelectedRow();
			PuntuGeneral seleccionada;
			// Si se ha seleccionado alguna fila
			if (fila != -1) {
				seleccionada = tmModel.getFila(fila);
				// Solo cargamos si la partida tiene nivel inferior a 10.
				if (seleccionada.getNiv_guar() < 10)
					// Cargamos la partida
					ControlPrincipal.cargarPartida(seleccionada);

			} else {
				JOptionPane.showMessageDialog(new JDialog(),
						"No has seleccionado ninguna fila", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Si pulsas el bot�n cancelar.
		} else if (botonPulsado == jbCancelar) {
			GestorVentana.ocultarVentana(VentanaCargar.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}

	/**
	 * Cada vez que hagamos visible la ventana actualizamos las
	 * lineas con partidas cargadas.
	 */
	public void setVisible(boolean estado) {
		super.setVisible(estado);
		if(estado){
			    	tmModel.deleteAllRows();
					ArrayList<PuntuGeneral> partGuardas = AccesoPuntuGen
							.getPartidasGuardadas();
					for (PuntuGeneral tempPunt : partGuardas)
						tmModel.a�adirFila(tempPunt);
			    }
	}
}
