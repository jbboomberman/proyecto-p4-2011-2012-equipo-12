package bomberman.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoMapa {
/*Representar� el acceso a la tabla MAPA y tendr� tres m�todos
est�ticos insertarMapa(Mapa map) que insertar� un mapa en la tabla, eliminarMapa(Mapa
map) que eliminar� un mapa de la tabla y listaMapas() que listar� todos los mapas de la
tabla.*/
	
	
	public static void InsertarMapa(int cod_Mapa,int cod_Nivel){
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("insert into MAPA values(" + cod_Mapa + "," + cod_Nivel+ ");");
		
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public static void EliminarMapa(int cod_Mapa){
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("delete from CONTROLES where COD_MAPA="+ cod_Mapa + ");");
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public static ArrayList<String> ListarMapas(){
		Statement stat;
		ArrayList<String> Ac = new ArrayList<String>();
		String control;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("select * from MAPA;");
			
			while(rs.next()){
				control=Integer.toString(rs.getInt("COD_MAPA"));
				
				Ac.add(control);
			}
			
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Ac;
		
		
	}
	
}
