package bomberman.database;

import java.sql.ResultSet;
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

	public static void getJugador(String nombre) {
		// Statement stat = conn.createStatement();
		// ResultSet rs =
		// stat.executeQuery("SELECT * FROM JUGADOR WHERE NICK = '" + nombre +
		// "';");
		// while (rs.next()) {
		// Jugador j = new Jugador(rs.getString("nombre"),
		// rs.getString("apellidos"), rs.getString("nick"),
		// rs.getString("email"));
		// array.add(j);
		// }
		// rs.close();
		// stat.close();
	}

	public static void getJugadores() {
		// Statement stat = conn.createStatement();
		// ResultSet rs = stat.executeQuery("SELECT * FROM JUGADOR;");
		// rs.close();
		// stat.close();
	}

}
