package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoMapa {
	/*
	 * Representará el acceso a la tabla MAPA y tendrá tres métodos estáticos
	 * insertarMapa(Mapa map) que insertará un mapa en la tabla,
	 * eliminarMapa(Mapa map) que eliminará un mapa de la tabla y listaMapas()
	 * que listará todos los mapas de la tabla.
	 */

	public static void insertarMapa(Mapa mapa) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO MAPA VALUES( ?, ?, ?);");
			stat.setInt(1, mapa.getCod_mapa());
			stat.setInt(2, mapa.getCod_nivel());
			stat.setString(3, new String(mapa.getCharArray()));
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarMapa(int cod_Mapa) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM MAPA WHERE COD_MAPA = ?;");
			stat.setInt(1, cod_Mapa);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Mapa getMapa(int cod_Mapa){
		Mapa temMapa = null;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"SELECT * FROM MAPA WHERE COD_MAPA = ?;");
			stat.setInt(1, cod_Mapa);
			ResultSet rs = stat.executeQuery();
			if(rs.next())
				temMapa = new Mapa(rs.getInt(1), rs.getInt(2), rs.getString(3).toCharArray());
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temMapa;
	}
	
	public static ArrayList<String> listarMapas() {
		Statement stat;
		ArrayList<String> Ac = new ArrayList<String>();
		String control;
		try {
			stat = GestionBD.conectar().createStatement();
			ResultSet rs = stat.executeQuery("select * from MAPA;");

			while (rs.next()) {
				control = Integer.toString(rs.getInt("COD_MAPA"));

				Ac.add(control);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Ac;
	}
	
	public static int getNumMapa() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM MAPA;");
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
	
	public static int getCodMapa(int nivel){
		int cod = 99;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"SELECT * FROM MAPA WHERE COD_NIV = ?;");
			stat.setInt(1, nivel);
			ResultSet rs = stat.executeQuery();
			cod = rs.getInt(1);
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod; 
	}
}
