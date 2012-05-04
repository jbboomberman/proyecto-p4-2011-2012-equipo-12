package bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se encarga de la conexi�n y desconexi�n con la BD.
 * @author David
 * @version 1.0
 */
public class GestionBD {

	//La conexi�n
	public static Connection conexion;

	/**
	 * M�todo est�tico que nos sirve para conectarnos a la base de datos.
	 */
	public static Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Nos conectamos a la BD
			conexion = DriverManager
					.getConnection("jdbc:sqlite:database/bombermanSQL.s3db");
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
			if(conexion != null)
				conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
