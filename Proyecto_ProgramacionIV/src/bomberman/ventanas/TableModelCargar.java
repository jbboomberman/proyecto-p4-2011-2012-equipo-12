package bomberman.ventanas;

import javax.swing.table.DefaultTableModel;
import bomberman.database.AccesoJugador;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuGeneral;
import bomberman.outin.ManipuladorFecha;

/**
 * Nos servirá para las ventanas VentanaCargar y VentanaGuardar.
 * Sera la clase para los JTable de estas ventanas.
 * @author David
 * @version 1.0
 */
public class TableModelCargar extends DefaultTableModel {
	private static final long serialVersionUID = -7461719037402108362L;

	/**
	 * Constructor principal de la clase TableModelCargar
	 * @param arg0 - int - Filas
	 * @param arg1 - int - Columnas
	 */
	public TableModelCargar(int arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * Añade una fila a la tabla
	 * @param punt - PuntuGeneral
	 */
	public void añadirFila(PuntuGeneral punt) {

		this.addRow(new Object[] { punt.getCod_punt(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNomJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getApellJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNickJugador(),
				punt.getPuntu(),
				punt.getNiv_guar(),
				ManipuladorFecha.parsearFecha(punt.getFecha_ulti_nivel()),
				AccesoJugador.getJugador(punt.getCod_jug()).getCod_jugador(),
				punt.getVidas() });
	}

	/**
	 * Devuelve un objeto de la clase PuntuGeneral
	 * que represente a una fila de la tabla.
	 * @param row - int
	 * @return PuntuGeneral
	 */
	public PuntuGeneral getFila(int row) {
		PuntuGeneral tempPunt = new PuntuGeneral((Integer) this.getValueAt(row,
				0), (Integer)this.getValueAt(row, 7), true, (Integer) this.getValueAt(row, 4),
				(String) this.getValueAt(row, 6), (Integer) this.getValueAt(
						row, 8), AccesoPuntuGen.getCodNivel((Integer) this.getValueAt(row, 0)));
		return tempPunt;
	}

	/**
	 * Borra todas las filas de la tabla.
	 */
	public void deleteAllRows() {
		while (this.getRowCount() > 0) {
			this.removeRow(0);
		}
	}

	/**
	 * @override Sobreescribimos el método isCellEditable para así evitar que
	 *           las celdas del JTable se puedan editar.
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
