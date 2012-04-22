package bomberman.ventanas;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bomberman.database.AccesoJugador;
import bomberman.database.AccesoPunEspe;
import bomberman.database.PuntuEspe;
import bomberman.database.PuntuGeneral;

public class TableModelPuntuaciones extends DefaultTableModel {

	private static final long serialVersionUID = -7461719037402108362L;

	public TableModelPuntuaciones(int arg0, int arg1) {
		super(arg0, arg1);
	}

	public void añadirFila(PuntuGeneral punt) {

		this.addRow(new Object[] {
				AccesoJugador.getJugador(punt.getCod_jug()).getNomJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNickJugador(),
				punt.getFecha_ulti_nivel().toString(),
				AccesoPunEspe.getNivelMasAlto(punt.getCod_punt()), 
				punt.getPuntu() });
	}

	/**
	 * @override Sobreescribimos el método isCellEditable para así evitar que
	 *           las celdas del JTable se puedan editar.
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
