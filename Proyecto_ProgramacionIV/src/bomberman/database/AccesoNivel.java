package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar la tabla Nivel.
 * @author David
 * @version 1.0
 */
public class AccesoNivel {

	/**
	 * Inserta un nivel en la tabla.
	 * @param nivel - Nivel
	 */
	public static void insertarNivel(Nivel nivel) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO NIVEL VALUES ( ?, ?, ?, ?);");
			//Código del nivel
			stat.setInt(1, nivel.getCod_nivel());
			//Nombre del nivel
			stat.setString(2, nivel.getNom_nivel());
			//Tiempo del nivel
			stat.setInt(3, nivel.getTiempo());
			//Password del nivel
			stat.setString(4, nivel.getPass());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina el nivel que tenga el mismo código
	 * que el recibido por parámetro.
	 * @param cod_Nivel - int
	 */
	public static void eliminaNivel(int cod_Nivel) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"DELETE FROM NIVEL WHERE COD_NIVEL = ?;");
			stat.setInt(1, cod_Nivel);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Se encarga de comprobar si el password y número de nivel
	 * recibidos por parámetro concuerdan. Nos sirve para
	 * comprobar si el usuario ha introducido correctamente
	 * la contraseña.
	 * @param contr - String
	 * @param niv - int
	 * @return boolean
	 */
	public static boolean esCorrecto(String contr, int niv) {
		boolean correcto = false;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM NIVEL WHERE COD_NIVEL = ?;");
			stat.setInt(1, niv);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				//Si las contraseñas son las mismas -- true
				if (rs.getString(4).equals(contr))
					correcto = true;
			}
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
	}

	/**
	 * Devuelve el nivel que tenga el mismo código que
	 * el especificado por parámetro.
	 * @param codNivel - int
	 * @return Nivel
	 */
	public static Nivel getNivel(int codNivel) {
		Nivel tempNivel = null;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM NIVEL WHERE COD_NIVEL = ?;");
			stat.setInt(1, codNivel);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				tempNivel = new Nivel(codNivel, rs.getString(2), rs.getInt(3),
						rs.getString(4));
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempNivel;
	}
	
	/**
	 * Devuelve el password del nivel especificado
	 * por código de nivel. Sirve para mostrar el
	 * password al usuario cuando haya superado un
	 * nivel.
	 * @param codNivel - int
	 * @return String
	 */
	public static String getPass(int codNivel){
		String pass = null;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM NIVEL WHERE COD_NIVEL = ?;");
			stat.setInt(1, codNivel);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				pass = rs.getString(4);
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
	}

}
