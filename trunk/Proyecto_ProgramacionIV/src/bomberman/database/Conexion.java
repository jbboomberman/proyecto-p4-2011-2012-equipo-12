/**Esta clase nos servirá para conectarnos a la base de datos. Todos los
métodos que tenga serán de tipo estático para poder así ser accedidos desde todas las
clases. Tendrá un método llamado conectar() que tendrá como objetivo conectarse a la
base de datos, como es lógico habrá un método desconectar() que nos servirá para
desconectarnos de la base de datos. También habrá otro método llamado consulta(String
sentencia) que recibirá como parámetro una sentencia SQL en forma de String el método
se encargará de ejecutarla y devolverá un objeto de tipo ResulSet con el resultado.
Finalmente, habrá otro método que tendrá como objetivo insertar líneas, ese método
tendrá el nombre de insertar(String linea) que insertará la sentencia SQL que se le envié
por parámetro. **/

package bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion;

	/**
	 * Método estático que nos sirve para conectarnos a la base de datos. No
	 * tenemos que crear ningún constructor porque es estático.
	 */
	public static Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager
					.getConnection("jdbc:sqlite:database/bombermanSQL.s3db");
			System.out.println("conecta a la base de datos");
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
			if (conexion != null)
				conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// public static consulta(String sentencia){
	// return ResultSet;
	// }
	public static void insertar(String linea) {
	}

	public static void main(String[] args) {
		Conexion.conectar();
	}
}
