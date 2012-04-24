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

	public static void InsertarMapa(Mapa mapa) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO MAPA VALUES( ?, ?);");
			stat.setInt(1, mapa.getCod_mapa());
			stat.setInt(2, mapa.getCod_nivel());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void EliminarMapa(int cod_Mapa) {
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

	public static ArrayList<String> ListarMapas() {
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
	public static void main (String []args){
		Mapa m= new Mapa(78, 9);
		InsertarMapa(m);
		ArrayList<String> map= ListarMapas();
		System.out.println(map.get(0));
		
	}
	
}
