package bomberman.database;

//Ben
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bomberman.managers.ControlPrincipal;

public class AccesoControles {
	/*
	 * Representará el acceso a la tabla CONTROLES y tendrá tres métodos
	 * estáticos insertarControl(Control cont) que insertará un control en la
	 * tabla, eliminarControl(Control cont) que eliminará un controlde la tabla
	 * y listaControles() que listará todos los controles de la tabla.
	 */

	public static void insertarControl(Controles control) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO CONTROLES VALUES( ?, ?, ?, ?);");
			stat.setInt(1, control.getCod_accion());
			stat.setString(2, control.getNom_accion());
			stat.setInt(3, control.getCod_ascii_tecla());
			stat.setInt(4, control.getTipo_jug());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarControl(int cod_accion) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM CONTROLES WHERE COD_ACCION = ?;");
			stat.setInt(1, cod_accion);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> listarControles() {
		Statement stat;
		ArrayList<String> Ac = new ArrayList<String>();
		String control;
		try {
			stat = GestionBD.conectar().createStatement();
			ResultSet rs = stat.executeQuery("select * from CONTROLES;");

			while (rs.next()) {
				control = Integer.toString(rs.getInt("COD_ASCII_TECLA"));

				Ac.add(control);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Ac;

	}
	
	public static int getControl(String nomAccion, int tipJug){
		int cod = -1;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM CONTROLES WHERE NOM_ACCION = ? AND TIPO_JUG = ?;");
			stat.setString(1, nomAccion);
			stat.setInt(2, tipJug);
			ResultSet rs = stat.executeQuery();
			cod = rs.getInt(3);
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cod;
	}
	
	public static void setControl(String nomAccion, int tipJug, int codAscii){
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"UPDATE CONTROLES SET COD_ASCII_TECLA = ? WHERE NOM_ACCION = ? AND TIPO_JUG = ?;");
			stat.setInt(1, codAscii);
			stat.setString(2, nomAccion);
			stat.setInt(3, tipJug);
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
//		//Controles c1=new Controles(22,"arriba",11,345);
//		Controles c2= new Controles(11,"abajo",22,435);
//		//AccesoControles.InsertarControl(c1);
//		//AccesoControles.InsertarControl(c2);
//		ArrayList<String> a1 =AccesoControles.ListarControles();
//		System.out.println("Lista de todos los controles");
//		for(int i=0;i<a1.size();i++){
//			System.out.println(a1.get(i));
//		}
//		System.out.println();
//		System.out.println("Lista eliminando c2");
//		AccesoControles.EliminarControl(c2.getCod_accion());
//		ArrayList<String> a2 =AccesoControles.ListarControles();
//		for(int i=0;i<a2.size();i++){
//			System.out.println(a2.get(i));
//		}
		
		
		
	}

}