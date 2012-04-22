package bomberman.database;

//Ben
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoControles {
	/*
	 * Representará el acceso a la tabla CONTROLES y tendrá tres métodos
	 * estáticos insertarControl(Control cont) que insertará un control en la
	 * tabla, eliminarControl(Control cont) que eliminará un controlde la tabla
	 * y listaControles() que listará todos los controles de la tabla.
	 */

	public static void InsertarControl(Controles control) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO CONTROLES VALUES( ?, ?, ?, ?);");
			stat.setInt(1, control.getCod_accion());
			stat.setString(2, control.getNom_accion());
			stat.setInt(3, control.getCod_ascii_tecla());
			stat.setInt(4, control.getTipo_jug());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void EliminarControl(int cod_accion) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM CONTROLES WHERE COD_ACCION = ?;");
			stat.setInt(1, cod_accion);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> ListarControles() {
		Statement stat;
		ArrayList<String> Ac = new ArrayList<String>();
		String control;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("select * from CONTROLES;");

			while (rs.next()) {
				control = Integer.toString(rs.getInt("COD_ASCII_TECLA"));

				Ac.add(control);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Ac;

	}

}