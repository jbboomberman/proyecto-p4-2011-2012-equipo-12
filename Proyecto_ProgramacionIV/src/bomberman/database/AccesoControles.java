package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que se utilizará para el acceso a la table Controles que contiene los controles
 * de los personajes almacenados por tecla.
 * @author David
 * @version 1.0
 */
public class AccesoControles {

	/**
	 * Método estático que se encarga de insertar el objeto
	 * Control en la BD. Este método solo será utilizado
	 * por los programadores para diseñar la BD, nunca
	 * será llamado en ejecución.
	 * @param control - Controles
	 */
	public static void insertarControl(Controles control) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO CONTROLES VALUES( ?, ?, ?, ?);");
			//Código de la acción
			stat.setInt(1, control.getCod_accion());
			//Nombre de la acción
			stat.setString(2, control.getNom_accion());
			//Número ASCII de la tecla
			stat.setInt(3, control.getCod_ascii_tecla());
			//Tipo de jugador
			stat.setInt(4, control.getTipo_jug());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método estático se encarga de eliminar
	 * un control de la BD recibiendo como parámetro
	 * el código de la acción.
	 * @param cod_accion - int
	 */
	public static void eliminarControl(int cod_accion) {
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

	/**
	 * Este método se encarga de devolver el valor ASCII
	 * de una tecla recibiendo como parámetros el nombre
	 * de la acción y el tipo de jugador.
	 * @param nomAccion - String
	 * @param tipJug - int
	 * @return int - cod ASCII, devuelve -1 en caso de que
	 * no exista.
	 */
	public static int getControl(String nomAccion, int tipJug) {
		int cod = -1;
		try {
			PreparedStatement stat = GestionBD
					.conectar()
					.prepareStatement(
							"SELECT * FROM CONTROLES WHERE NOM_ACCION = ? AND TIPO_JUG = ?;");
			stat.setString(1, nomAccion);
			stat.setInt(2, tipJug);
			ResultSet rs = stat.executeQuery();
			if(rs.next())
				//Código ASCII
				cod = rs.getInt(3);
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}

	/**
	 * Cambia el código ASCII de un control.
	 * @param nomAccion - String
	 * @param tipJug - int
	 * @param codAscii - int. Código ASCII nuevo.
	 */
	public static void setControl(String nomAccion, int tipJug, int codAscii) {
		try {
			PreparedStatement stat = GestionBD
					.conectar()
					.prepareStatement(
							"UPDATE CONTROLES SET COD_ASCII_TECLA = ? WHERE NOM_ACCION = ? AND TIPO_JUG = ?;");
			stat.setInt(1, codAscii);
			stat.setString(2, nomAccion);
			stat.setInt(3, tipJug);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}