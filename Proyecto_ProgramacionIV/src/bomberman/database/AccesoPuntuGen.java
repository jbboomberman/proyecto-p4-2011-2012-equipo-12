package bomberman.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoPuntuGen {
	/*
	 * Representar� el acceso a la tabla PUNTUACION_GENERAL y tendr� tres
	 * m�todos est�ticos insertarPunt(PuntuGeneral punt) que insertar� una
	 * puntuaci�n en la tabla, eliminarPunt(PuntuGeneral punt) que eliminar� una
	 * puntuaci�n de la tabla y listaPunt() que listar� todos las puntuaciones
	 * de la tabla.
	 */
	public static void insertarPunt(PuntuGeneral punt) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			String s = "insert into PUNTUACION_GENERAL (" + punt.getCod_punt()
					+ " " + punt.getCod_jug() + " " + punt.isGuardado() + " "
					+ punt.getPuntu() + " '" + punt.getFecha_ulti_nivel()
					+ "');";
			ResultSet rs = stat.executeQuery(s);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void eliminarPunt(PuntuGeneral punt) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat
					.executeQuery("delete from PUNTUACION_GENERAL VALUES where COD_PUNT='"
							+ punt.getPuntu() + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void listaPunt() {

	}

}
