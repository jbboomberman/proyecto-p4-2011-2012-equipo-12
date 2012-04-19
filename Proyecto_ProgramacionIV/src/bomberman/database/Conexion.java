/**Esta clase nos servir� para conectarnos a la base de datos. Todos los
m�todos que tenga ser�n de tipo est�tico para poder as� ser accedidos desde todas las
clases. Tendr� un m�todo llamado conectar() que tendr� como objetivo conectarse a la
base de datos, como es l�gico habr� un m�todo desconectar() que nos servir� para
desconectarnos de la base de datos. Tambi�n habr� otro m�todo llamado consulta(String
sentencia) que recibir� como par�metro una sentencia SQL en forma de String el m�todo
se encargar� de ejecutarla y devolver� un objeto de tipo ResulSet con el resultado.
Finalmente, habr� otro m�todo que tendr� como objetivo insertar l�neas, ese m�todo
tendr� el nombre de insertar(String linea) que insertar� la sentencia SQL que se le envi�
por par�metro. **/

package bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion;

	/**
	 * M�todo est�tico que nos sirve para conectarnos a la base de datos. No
	 * tenemos que crear ning�n constructor porque es est�tico.
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
	 * M�todo est�tico para desconectarnos de la base de datos.
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
