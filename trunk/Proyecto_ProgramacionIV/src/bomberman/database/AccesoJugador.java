package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bomberman.jugador.*;

public class AccesoJugador {

	public static void insertarJugador(Jugador jug) {
		// Statement stat = conn.createStatement();
		// stat.executeUpdate("INSERT INTO JUGADOR VALUES ('" + jug.getNombre()
		// + "', '" + jug.getApellidos() + "', '" + jug.getNick() + "', '"+
		// jug.getEmail() + "');");
		// rs.close();
		// stat.close();
	}

	public static Jugador getJugador(int codJugador) {
		Jugador tempJug = null;
		try{
		PreparedStatement stat = GestionBD.conectar().prepareStatement(
				"SELECT * FROM JUGADOR WHERE COD_JUGADOR = ?");
		stat.setInt(1, codJugador);
		ResultSet rs = stat.executeQuery();
		tempJug = new Jugador(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
