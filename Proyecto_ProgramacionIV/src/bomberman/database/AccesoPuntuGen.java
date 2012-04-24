package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AccesoPuntuGen {
	/*
	 * Representará el acceso a la tabla PUNTUACION_GENERAL y tendrá tres
	 * métodos estáticos insertarPunt(PuntuGeneral punt) que insertará una
	 * puntuación en la tabla, eliminarPunt(PuntuGeneral punt) que eliminará una
	 * puntuación de la tabla y listaPunt() que listará todos las puntuaciones
	 * de la tabla.
	 */
	public static void insertarPunt(PuntuGeneral punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO PUNTUACION_GENERAL VALUES( ?, ?, ?, ?, ?);");
			stat.setInt(1, punt.getCod_punt());
			stat.setInt(2, punt.getCod_jug());
//			stat.setBoolean(3, punt.ge);
			stat.setInt(4, punt.getPuntu());
			stat.setDate(5, punt.getFecha_ulti_nivel());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarPunt(int cod_punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM PUNTUACION_GENERAL WHERE COD_PUNT = ?;");
			stat.setInt(1, cod_punt);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getNumPunt() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL;");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				cont++;
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	public static void listaPunt() {

	}
}
