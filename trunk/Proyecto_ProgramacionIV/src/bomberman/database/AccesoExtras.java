package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar los par�metros
 * de envio de email y activaci�n de sonido.
 * @author David
 * @version 1.0
 */
public class AccesoExtras {

	/**
	 * Crea un nuevo extra.
	 * @param cod - int
	 * @param nombre - String
	 * @param estado - boolean
	 */
	public static void insertarExtra(int cod, String nombre, boolean estado) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO EXTRAS VALUES( ?, ?, ?);");
			//C�digo del extra
			stat.setInt(1, cod);
			//Nombre del extra
			stat.setString(2, nombre);
			//Estado del extra
			stat.setBoolean(3, estado);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este m�todo est�tico se encarga de eliminar
	 * un extra de la BD recibiendo como par�metro
	 * el c�digo del extra.
	 * @param cod - int
	 */
	public static void eliminarControl(int cod) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"DELETE FROM EXTRAS WHERE CODIGO = ?;");
			stat.setInt(1, cod);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este m�todo se encarga de actualizar el estado de
	 * un extra.
	 * @param nom - String, nombre del extra
	 * @param estado - boolean
	 */
	public static void setExtra(String nom, boolean estado) {
		PreparedStatement stat;
		try {
				stat = GestionBD.conectar().prepareStatement(
						"UPDATE EXTRAS SET" + nom + " = ?;");
			stat.setBoolean(1, estado);
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el estado del extra especificado.
	 * @param nom - String
	 * @return boolean
	 */
	public static boolean getExtra(String nom) {
		PreparedStatement stat;
		boolean esta = false;
		try {
			stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM EXTRAS;");
			ResultSet rs = stat.executeQuery();
			if (nom.equals("email"))
				esta = rs.getBoolean(3);
			else
				esta = rs.getBoolean(2);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esta;
	}
}
