package bomberman.database;

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

	public static void InsertarNivel(int cod_nivel, String nom_nivel,
			int tiempo, String pass) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("insert into CONTROLES values("
					+ cod_nivel + ",'" + nom_nivel + "'," + tiempo + ",'"
					+ pass + "');");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void EliminaNivel(int cod_Nivel) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat
					.executeQuery("delete from CONTROLES where COD_NIVEL="
							+ cod_Nivel + ");");
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
