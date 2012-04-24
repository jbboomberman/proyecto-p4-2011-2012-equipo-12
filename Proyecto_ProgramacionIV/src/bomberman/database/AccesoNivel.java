package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoNivel {
	/*
	 * Representará el acceso a la tabla NIVEL y tendrá tres métodos estáticos
	 * insertarNivel(Nivel niv) que insertará un nivel en la tabla,
	 * eliminarNivel(Nivel niv) que eliminará un nivel de la tabla y listaNiv()
	 * que listará todos los niveles de la tabla.
	 */

	// COD_NIVEL, NOM_NIVEL, TIEMPO, PASS)

	public static void InsertarNivel(Nivel nivel) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO NIVEL VALUES ( ?, ?, ?, ?);");
			stat.setInt(1, nivel.getCod_nivel());
			stat.setString(2, nivel.getNom_nivel());
			stat.setInt(3, nivel.getTiempo());
			stat.setString(4, nivel.getPass());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void EliminaNivel(int cod_Nivel) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM NIVEL WHERE COD_NIVEL = ?;");
			stat.setInt(1, cod_Nivel);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> ListarNiveles() {
		Statement stat;
		ArrayList<String> Ac = new ArrayList<String>();
		String control;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("select * from NIVELES;");

			while (rs.next()) {
				// falta
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Ac;

	}

}
