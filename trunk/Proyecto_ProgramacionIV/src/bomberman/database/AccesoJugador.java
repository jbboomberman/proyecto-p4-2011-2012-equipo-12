package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import bomberman.jugador.*;

public class AccesoJugador {

	public static void insertarJugador(Jugador jug) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempJug;
		// El que llame a este método tiene que comprobar que no recibe null
	}

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
			tempJug = new Jugador(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5));
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempJug;
	}

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}

        public static ArrayList<String> getJugadores() {
                                 Statement stat;
                                        ArrayList<String> Ac = new ArrayList<String>();
                                        String jugador;
                                        try {
                                                stat = GestionBD.conectar().createStatement();
                                                ResultSet rs = stat.executeQuery("select * from JUGADOR;");

                                                while (rs.next()) {
                                                        jugador = Integer.toString(rs.getInt("COD_JUGADOR"));

                                                        Ac.add(jugador);

                                                }

                                        } catch (SQLException e) {

                                                e.printStackTrace();
                                        }
                                        return Ac;
        }

        public static void main (String[] args ){
        Jugador ju=new Jugador(12331, "beñat", "bravo", "beñaaaaat", "benatb@opendeusto.es");
        insertarJugador(ju);
        ArrayList<String> jus=getJugadores();
        for(int i=0;i<jus.size();i++){
                System.out.println(jus.get(i));
        }
       
        }
}
       

