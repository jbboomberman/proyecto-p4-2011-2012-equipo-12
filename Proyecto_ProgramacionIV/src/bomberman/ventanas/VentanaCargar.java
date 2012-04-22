package bomberman.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

//Fig 11  P�gina 15.  Tiene una JTable.
public class VentanaCargar extends JDialog implements ActionListener{

	private JTable jtPuntu;
	private JLabel jlTexto;
	private JButton jbCargar;
	private JButton jbCancelar;
	private JPanel jpInferior;
	private TableModelPuntuaciones tmModel;
	private TableRowSorter<TableModel> ordenarTabla;
	private JScrollPane jsTabla;

	public VentanaCargar() {
		//Inicializamos las variables
		jlTexto = new JLabel("Partidas guardadas");
		jbCargar = new JButton("Cargar partida");
		jbCancelar = new JButton("Cancelar");
		jpInferior = new JPanel();
		tmModel = new TableModelPuntuaciones(0, 5);
		tmModel.setColumnIdentifiers(new String[]{"Nombre", "Nick", "Fecha", "Nivel", "Puntuaci�n"});
		jtPuntu = new JTable(tmModel);
		jsTabla = new JScrollPane(jtPuntu);

		//Layouts
		getContentPane().setLayout(new BorderLayout());
		jpInferior.setLayout(new FlowLayout());
		jlTexto.setHorizontalAlignment(SwingConstants.CENTER);
		
		//A�adir componentes
		jpInferior.add(jbCargar);
		jpInferior.add(jbCancelar);
		getContentPane().add(jlTexto, BorderLayout.NORTH);
		getContentPane().add(jsTabla, BorderLayout.CENTER);
		getContentPane().add(jpInferior, BorderLayout.SOUTH);
		jlTexto.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		
		//Escuchadores
		jbCargar.addActionListener(this);
		jbCancelar.addActionListener(this);
		
		//Determinamos que tama�os tendr� cada columna
		dise�arColumnas("Nombre", 150, 250);
		dise�arColumnas("Nick", 100, 150);
		dise�arColumnas("Fecha", 100, 150);
		dise�arColumnas("Nivel", 100, 150);
		dise�arColumnas("Puntuaci�n", 100, 150);
		
		//Par�metros
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
	 * columana del objeto JTable.
	 * @param nom - String, nombre de la columna
	 * @param prefTam - int, tama�o preferido
	 * @param maxTam - int, tama�o m�ximo
	 */
	private void dise�arColumnas(String nom, int prefTam, int maxTam)
	{
		/*
		 * Manejamos la excepci�n IllegalArgumentException ya que si
		 * el par�metro nombre que se pasa a la entrada de la funci�n
		 * no es correcto se pueden generar problemas.
		 */
		try{
			jtPuntu.getColumn(nom).setPreferredWidth(prefTam);
			jtPuntu.getColumn(nom).setMaxWidth(maxTam);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(new JDialog(), "Error al intentar crear el JTable, el dise�o no ha sido predeterminado","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		/*
		 * Para saber d�nde se origin� el evento creamos un Object con la
		 * direcci�n del generador del evento.
		 */
		Object botonPulsado = e.getSource();
		
		if(botonPulsado == jbCargar){
			
		}else if(botonPulsado == jbCancelar){
			GestorVentana.ocultarVentana(VentanaCargar.class);
			GestorVentana.hacerVisible(VentanaInicial.class, true);
		}
	}
	
	public static void main (String[]args){
		VentanaCargar tempVent = new VentanaCargar();
		tempVent.setVisible(true);
	}
}