package bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionBD {

	private static Connection conexion;

	/**
	 * M�todo est�tico que nos sirve para conectarnos a la base de datos.
	 *  No tenemos que crear ning�n constructor porque es est�tico.
	 */
	public static Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:database.db");
			System.out.println("conecta a la base de datos");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	/**
	 * M�todo est�tico para desconectarnos de la base de datos.
	 */
	public static void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
