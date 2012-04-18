package bomberman.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoPunEspe {
/*Representará el acceso a la tabla PUNTU_NIV_ESPECI y tendrá
tres métodos estáticos insertarPunt(PuntuEspe punt) que insertará una puntuación
específica en la tabla, eliminarPunt(PuntuEspe punt) que eliminará una puntuación
específica de la tabla y listaPunt() que listará todas las puntuaciones específicas de 
tabla. */
	
	
	public static void insertarPunt(PuntuEspe punt){
		Statement stat;
		try {
		stat = GestionBD.conexion.createStatement();
		String s="insert into PUNTU_NIV_ESPECI VALUES ("+punt.getCOD_PUNTU()+" "+punt.getCOD_PUNTU_ESPE()+" "+punt.getPUNTU_ESPE()+" '"+punt.getFECHA()+"');" ;
		System.out.println("s");
		ResultSet rs = stat.executeQuery(s);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			}
	}
	public static void eliminarPunt(PuntuEspe punt){
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat.executeQuery("delete from PUNTU_NIV_ESPECI VALUES where COD_PUNTU_ESPE='"+ punt.getCOD_PUNTU_ESPE() + "'");
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public static void listaPunt(){
		
	}
	
	
	
	
}
