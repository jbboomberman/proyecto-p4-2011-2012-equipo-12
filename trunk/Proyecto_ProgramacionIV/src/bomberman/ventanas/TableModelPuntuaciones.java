package bomberman.ventanas;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que se encarga de manejar el contenido del objeto JTable que estará en
 * la ventana 'VentanaScore'. Hereda de DefaultTableModel.
 * 
 * @author David
 * @version 1.0
 */
public class TableModelPuntuaciones extends DefaultTableModel {

	private static final long serialVersionUID = -7461719037402108362L;
	private String[] aCincoCampos;

	/**
	 * Constructor principal de la clase TableModePuntuaciones. Los parámetros
	 * de entrada indican el número de columnas y filas que tendra el objeto
	 * JTable.
	 * 
	 * @param arg0
	 *            - int
	 * @param arg1
	 *            - int
	 */
	public TableModelPuntuaciones(int arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * Método que se encarga de añadir una fila al objeto de la clase
	 * TableModePuntuaciones.
	 * 
	 * @param sPuntu
	 *            - String, tendrá el formato dato<tab>dato<tab>...dato<tab>
	 */
	public void añadirFila(String sPuntu) {
		aCincoCampos = chequearLinea(sPuntu);
		this.addRow(new Object[] { aCincoCampos[0], aCincoCampos[1],
				aCincoCampos[2], aCincoCampos[3], aCincoCampos[4] });
	}

	/**
	 * Método que se encarga de dividir la linea que se envie por parámetro de
	 * entrada en cinco partes: nick, puntuación, nivel, nombre y apellidos.
	 * Además de chequear que el formato es correcto.
	 * 
	 * @param linea
	 *            - String
	 * @return aTemp - String[], array donde se devolverán los datos
	 *         descompuestos.
	 */
	private String[] chequearLinea(String linea) {
		String[] aTemp = new String[5];
		int indice;
		for (int i = 0; i < 5; i++) {
			// Buscamos el primer tabulador
			indice = linea.indexOf("\t");
			/*
			 * Desde el inicio de la linea hasta el primer tabulador(sin
			 * incluirlo) será uno de los datos del jugador.
			 */
			try {
				aTemp[i] = linea.substring(0, indice);
				// Nos ponemos en la posición del siguiente dato.
				linea = linea.substring(indice + 1, linea.length());
			} catch (IndexOutOfBoundsException e) {
				// En caso de que el formato no sea el correcto.
				JOptionPane
						.showMessageDialog(new JDialog(),
								"El contenido del fichero de puntuaciones no es el correcto");
			}
		}
		return aTemp;
	}

	/**
	 * @override Sobreescribimos el método isCellEditable para así evitar que
	 *           las celdas del JTable se puedan editar.
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	// public static void main (String [] args)
	// {
	// TableModelPuntuaciones table = new TableModelPuntuaciones(0, 5);
	// table.añadirFila("hola" + "\t" + "1902" + "\t" + "Avanzado" + "\t" +
	// "David" + "\t" + "Orive" + "\t");
	// System.out.println(table.getValueAt(0, 2));
	// }
}
