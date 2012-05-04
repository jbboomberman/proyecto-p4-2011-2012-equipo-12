package bomberman.ventanas;

import javax.swing.table.DefaultTableModel;
import bomberman.database.AccesoJugador;
import bomberman.database.AccesoPunEspe;
import bomberman.database.AccesoPuntuGen;
import bomberman.database.PuntuEspe;
import bomberman.database.PuntuGeneral;
import bomberman.outin.ManipuladorFecha;

/**
 * Nos servirá para la ventana VentanaPuntuaciones.
 * Se le añadirá a la JTable.
 * @author David
 * @version 1.0
 */
public class TableModelPuntuaciones extends DefaultTableModel {

	private static final long serialVersionUID = -7461719037402108362L;

	/**
	 * Constructor principal de la clase TableModelPuntuaciones
	 * @param arg0 - int - Filas
	 * @param arg1 - int - Columnas
	 */
	public TableModelPuntuaciones(int arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * Añade una fila a la tabla. Recibe como parámetro
	 * un objeto de la clase PuntuGeneral.
	 * @param punt - PuntuGeneral
	 */
	public void añadirFila(PuntuGeneral punt) {

		this.addRow(new Object[] {
				AccesoJugador.getJugador(punt.getCod_jug()).getNomJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getApellJugador(),
				AccesoJugador.getJugador(punt.getCod_jug()).getNickJugador(),
				punt.getPuntu(),
				AccesoPunEspe.getNivelMasAlto(punt.getCod_punt()),
				ManipuladorFecha.parsearFecha(punt.getFecha_ulti_nivel()) });
	}
	
	/**
	 * Añade una fila a la tabla. Recibe como parámetro
	 * un objeto de la clase PuntuEspe.
	 * @param punt - PuntuEspe
	 */
	public void añadirFila(PuntuEspe punt) {
		/*
		 * Caso específico para las puntuaciones específicas.
		 * EXPLICACIÓN: Cuando jugamos una partida en modo
		 * Master tenemos el problema de que no tiene PuntuGeneral y
		 * por ende no sabemos el propietario.
		 * Por ello la código de la puntuación será realmente en estos
		 * casos el código del Jugador en negativo.
		 */
		if(punt.getCod_puntu() < 0){
			this.addRow(new Object[] {
					AccesoJugador.getJugador(Math.abs(punt.getCod_puntu())).getNomJugador(),
							AccesoJugador.getJugador(Math.abs(punt.getCod_puntu()))
							.getApellJugador(),
									AccesoJugador.getJugador(Math.abs(punt.getCod_puntu()))
									.getNickJugador(),
					punt.getPuntu_espe(),
					punt.getNivel(),
					ManipuladorFecha.parsearFecha(punt.getFecha()) });
		}else{
			//Puntuación específica normal
			this.addRow(new Object[] {
					AccesoJugador.getJugador(AccesoPuntuGen
							.getCodJugador(punt.getCod_puntu())).getNomJugador(),
							AccesoJugador.getJugador(AccesoPuntuGen
									.getCodJugador(punt.getCod_puntu())).getApellJugador(),
									AccesoJugador.getJugador(AccesoPuntuGen
											.getCodJugador(punt.getCod_puntu())).getNickJugador(),
					punt.getPuntu_espe(),
					punt.getNivel(),
					ManipuladorFecha.parsearFecha(punt.getFecha()) });
		}
	}

	/**
	 * Devuelve una PuntuGeneral de la fila que se envie
	 * por parámetro.
	 * @param row - int
	 * @return PuntuGeneral
	 */
	public PuntuGeneral getFila(int row) {
		PuntuGeneral tempPunt = new PuntuGeneral((Integer) this.getValueAt(row,
				0), (Integer) this.getValueAt(row, 1),
				(Boolean) this.getValueAt(row, 2), (Integer) this.getValueAt(
						row, 3), (String) this.getValueAt(row, 4),
				(Integer) this.getValueAt(row, 5), AccesoPuntuGen.
				getCodNivel((Integer) this.getValueAt(row, 0)));
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
	 * Sobreescribimos el método isCellEditable para así evitar que
	 * las celdas del JTable se puedan editar.
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
