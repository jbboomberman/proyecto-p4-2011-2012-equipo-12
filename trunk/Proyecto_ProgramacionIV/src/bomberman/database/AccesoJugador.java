package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta clase gestiona la tabla Jugador de la BD.
 * 
 * @author David
 * @version 1.0
 */
public class AccesoJugador {

	/**
	 * Inserta un nuevo jugador en la BD.
	 * 
	 * @param jug
	 *            - Jugador
	 */
	public static void insertarJugador(Jugador jug) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO JUGADOR VALUES(?, ?, ?, ?, ?);");
			// Código jugador
			stat.setInt(1, jug.getCod_jugador());
			// Nombre jugador
			stat.setString(2, jug.getNomJugador());
			// Apellidos jugador
			stat.setString(3, jug.getApellJugador());
			// Nick jugador
			stat.setString(4, jug.getNickJugador());
			// Email jugador
			stat.setString(5, jug.getEmail());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina el jugador que tenga el mismo código que el recibido por
	 * parámetro.
	 * 
	 * @param cod_jug
	 *            - int
	 */
	public static void eliminarJugador(int cod_jug) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"DELETE FROM JUGADOR WHERE COD_JUGADOR = ?;");
			stat.setInt(1, cod_jug);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el jugador que tenga el mismo código que el recibido por
	 * parámetro. Null si no existe.
	 * 
	 * @param codJugador
	 *            - int
	 * @return Jugador
	 */
	public static Jugador getJugador(int codJugador) {
		Jugador tempJug = null;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM JUGADOR WHERE COD_JUGADOR = ?;");
			stat.setInt(1, codJugador);
			ResultSet rs = stat.executeQuery();
			tempJug = new Jugador(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5));
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempJug;
	}

	/**
	 * Devuelve un ArrayList con los códigos de los jugadores que tengan el
	 * mismo nombre que el recibido por parámetro de entrada. Útil para cuando
	 * busquemos un jugador por nombre.
	 * 
	 * @param nom
	 *            - String
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> getCodJugador(String nom) {
		ArrayList<Integer> codAlma = new ArrayList<Integer>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM JUGADOR WHERE NOM_JUG = ?;");
			stat.setString(1, nom);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				codAlma.add(rs.getInt(1));
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codAlma;
	}

	/**
	 * Devuelve un jugador que tenga el mismo valor en los atributos recibidos
	 * por parámetros de entrada. Para saber si crear un nuevo jugador o no.
	 * 
	 * @param nombre
	 *            - String
	 * @param apellido
	 *            - String
	 * @param nick
	 *            - String
	 * @param email
	 *            - String
	 * @return Jugador
	 */
	public static Jugador getJugador(String nombre, String apellido,
			String nick, String email) {
		Jugador tempJug = null;
		try {
			PreparedStatement stat = GestionBD
					.conectar()
					.prepareStatement(
							"SELECT * FROM JUGADOR WHERE NOM_JUG = ? AND APELL_JUG = ? AND NICK_JUG = ? AND EMAIL = ?;");
			stat.setString(1, nombre);
			stat.setString(2, apellido);
			stat.setString(3, nick);
			stat.setString(4, email);
			ResultSet rs = stat.executeQuery();
			if (rs.next())
				tempJug = new Jugador(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempJug;
	}

	/**
	 * Devuelve el número de jugadores que hay en la BD. Útil para crear el
	 * código de jugador.
	 * 
	 * @return int
	 */
	public static int getNumJug() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM JUGADOR;");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				cont++;
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}

	/**
	 * Devuelve una lista con todos los jugadores del juego.
	 * 
	 * @return ArrayList<String>
	 */
	public static ArrayList<Jugador> getJugadores() {
		ArrayList<Jugador> arLisJug = new ArrayList<Jugador>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM JUGADOR;");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				arLisJug.add(new Jugador(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5)));
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arLisJug;
	}
}
