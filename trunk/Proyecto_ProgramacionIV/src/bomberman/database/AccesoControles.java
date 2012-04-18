package bomberman.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoControles {
/*Representará el acceso a la tabla CONTROLES y tendrá tres
métodos estáticos insertarControl(Control cont) que insertará un control en la tabla,
eliminarControl(Control cont) que eliminará un controlde la tabla y listaControles() que
listará todos los controles de la tabla. */
	
	
	public static void InsertarControl(String cod_accion,String nom_accion,String ascii,String tipo){
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("insert into CONTROLES values(" + "'" + cod_accion + "','" + nom_accion + "'," + ascii + "','"+tipo+"');");
		
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

public static void EliminarControl(String cod_accion,String nom_accion,String ascii,String tipo){
	Statement stat;
	try {
		stat = GestionBD.conexion.createStatement();
		ResultSet rs = stat.executeQuery("delete from CONTROLES where COD_ACCION='"+ cod_accion + "'");
	  } catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
}

public static ArrayList<String> ListarControles(){
	Statement stat;
	ArrayList<String> Ac = new ArrayList<String>();
	try {
		stat = GestionBD.conexion.createStatement();
		ResultSet rs = stat.executeQuery("select * from CONTROLES;");
		
		while(rs.next()){
			
			
			
		}
		
	  } catch (SQLException e) {
		
		e.printStackTrace();
	}
	return Ac;
	
	
}

}