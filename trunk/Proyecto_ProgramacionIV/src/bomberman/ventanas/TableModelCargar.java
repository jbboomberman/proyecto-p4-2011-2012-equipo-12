package bomberman.ventanas;

import javax.swing.table.DefaultTableModel;

import bomberman.database.AccesoJugador;
import bomberman.database.AccesoPunEspe;
import bomberman.database.PuntuGeneral;
import bomberman.outin.ManipuladorFecha;

public class TableModelCargar extends DefaultTableModel {
	private static final long serialVersionUID = -7461719037402108362L;

	public TableModelCargar(int arg0, int arg1) {
		super(arg0, arg1);
	}

	public void añadirFila(PuntuGeneral punt) {

		this.addRow(new Object[] { punt.getCod_punt(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNomJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getApellJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNickJugador(),
				punt.getPuntu(),
				AccesoPunEspe.getNivelMasAlto(punt.getCod_punt()),
				ManipuladorFecha.parsearFecha(punt.getFecha_ulti_nivel()),
				AccesoJugador.getJugador(punt.getCod_jug()),
				punt.getVidas() });
	}

	public PuntuGeneral getFila(int row) {
		PuntuGeneral tempPunt = new PuntuGeneral((Integer) this.getValueAt(row,
				0), (Integer)this.getValueAt(row, 7), true, (Integer) this.getValueAt(row, 4),
				(String) this.getValueAt(row, 6), (Integer) this.getValueAt(
						row, 8));
		return tempPunt;
	}

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
