package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bomberman.jugador.*;

public class AccesoJugador {

	public static void insertarJugador(Jugador jug) {
		try{
		PreparedStatement stat = GestionBD.conectar().prepareStatement(
		"INSERT INTO JUGADOR VALUES(?, ?, ?, ?, ?);");
		stat.setInt(1, jug.getCod_jugador());
		stat.setString(2, jug.getNomJugador());
		stat.setString(3, jug.getApellJugador());
		stat.setString(4, jug.getNickJugador());
		stat.setString(5, jug.getEmail());
		stat.executeUpdate();
		stat.close();
		GestionBD.desconectar();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void eliminarJugador(int cod_jug) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM JUGADOR WHERE COD_JUGADOR = ?");
			stat.setInt(1, cod_jug);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Jugador getJugador(int codJugador) {
		Jugador tempJug = null;
		try{
		PreparedStatement stat = GestionBD.conectar().prepareStatement(
				"SELECT * FROM JUGADOR WHERE COD_JUGADOR = ?");
		stat.setInt(1, codJugador);
		ResultSet rs = stat.executeQuery();
		tempJug = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		 rs.close();
		 stat.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tempJug;
		//El que llame a este método tiene que comprobar que no recibe null
	}

	public static void getJugadores() {
		// Statement stat = conn.createStatement();
		// ResultSet rs = stat.executeQuery("SELECT * FROM JUGADOR;");
		// rs.close();
		// stat.close();
	}

}
