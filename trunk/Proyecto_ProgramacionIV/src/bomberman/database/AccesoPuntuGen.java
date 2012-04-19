package bomberman.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoPuntuGen {
	/*
	 * Representará el acceso a la tabla PUNTUACION_GENERAL y tendrá tres
	 * métodos estáticos insertarPunt(PuntuGeneral punt) que insertará una
	 * puntuación en la tabla, eliminarPunt(PuntuGeneral punt) que eliminará una
	 * puntuación de la tabla y listaPunt() que listará todos las puntuaciones
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
