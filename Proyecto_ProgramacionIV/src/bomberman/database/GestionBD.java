package bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionBD {

	private static Connection conexion;

	/**
	 * Método estático que nos sirve para conectarnos a la base de datos No
	 * tenemos que crear ningún constructor porque es estático.
	 */
	public static Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:bomberman.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	/**
	 * Método estático para desconectarnos de la base de datos.
	 */
	public static void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
