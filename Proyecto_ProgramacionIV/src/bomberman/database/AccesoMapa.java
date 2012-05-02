package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona el acceso a la tabla Mapa.
 * @author David
 * @version 1.0
 */
public class AccesoMapa {

	/**
	 * Este método estático se encarga de insertar un
	 * Mapa.
	 * @param mapa - Mapa
	 */
	public static void insertarMapa(Mapa mapa) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO MAPA VALUES( ?, ?, ?);");
			stat.setInt(1, mapa.getCod_mapa());
			stat.setInt(2, mapa.getCod_nivel());
			stat.setString(3, new String(mapa.getCharArray()));
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Se encarga de eliminar el Mapa que contenga el
	 * mismo código que el recibido por parámetro de
	 * entrada.
	 * @param cod_Mapa - int
	 */
	public static void eliminarMapa(int cod_Mapa) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"DELETE FROM MAPA WHERE COD_MAPA = ?;");
			stat.setInt(1, cod_Mapa);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el mapa que tenga el mismo código
	 * que el recibido por parámetro. Devuelve null
	 * en caso de no haber ninguno.
	 * @param cod_Mapa - int
	 * @return Mapa
	 */
	public static Mapa getMapa(int cod_Mapa) {
		Mapa temMapa = null;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM MAPA WHERE COD_MAPA = ?;");
			stat.setInt(1, cod_Mapa);
			ResultSet rs = stat.executeQuery();
			if (rs.next())
				temMapa = new Mapa(rs.getInt(1), rs.getInt(2), rs.getString(3)
						.toCharArray());
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temMapa;
	}

	/**
	 * Devuelve el número de mapas que hay en la tabla.
	 * @return int
	 */
	public static int getNumMapa() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM MAPA;");
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

	/**
	 * Devuelve el código del mapa que pertenezca al
	 * nivel que recibimos por parámetro.
	 * @param nivel - int
	 * @return int
	 */
	public static int getCodMapa(int nivel) {
		int cod = -1;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM MAPA WHERE COD_NIV = ?;");
			stat.setInt(1, nivel);
			ResultSet rs = stat.executeQuery();
			cod = rs.getInt(1);
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
}
