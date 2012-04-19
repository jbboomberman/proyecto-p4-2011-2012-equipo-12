package bomberman.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Esta clase tiene como objetivo hacer consultas complejas de más de un
 * atributo y con varias tablas.
 * 
 * @author David
 * 
 */
public class BusquedaCompleja {

	private static ResultSet rs;
	private static ArrayList<PuntuEspe> resulBusqueda;

	/*
	 * El parámetro de entrada tiene 'Nombre atributo', 'Atributo' y 'Nombre
	 * tabla'.
	 */

	// public static ArrayList<PuntuEspe> busquedaCombinada(String nomTabla,
	// String[][] valorBusqueda){//Quizás array bidimensional
	// try{
	// resulBusqueda = new ArrayList<PuntuEspe>();
	// String atributos = getAtributos(valorBusqueda);
	// Statement stat = Conexion.conectar().createStatement();
	//
	// rs = stat.executeQuery("SELECT * FROM " + nomTabla + " WHERE " +
	// atributos + ";");
	// System.out.println("SELECT * FROM " + nomTabla + " WHERE " + atributos +
	// ";");
	// while (rs.next()) {
	// // PuntuEspe j = new PuntuEspe(rs.getInt("COD_PUNTU_ESPE"),
	// // rs.getInt("COD_PUNTU"), rs.getInt("PUNTU_ESPE"),
	// // rs.getDate("FECHA"));
	// // resulBusqueda.add(j);
	// // }
	// rs.close();
	// Conexion.desconectar();
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// return resulBusqueda;
	// }

	// /*
	// * arrayDatos
	// * - Primera fila nombre tablas
	// * - Segunda fila nombre atributo
	// * - Tercera fila atributo
	// */
	//
	// private static String getNombreTablas(String[][] arrayDatos){
	// ArrayList<String> tempTablas = new ArrayList<String>();
	// String tablas = arrayDatos[0][0];
	// tempTablas.add(arrayDatos[0][0]);
	//
	// for(int i = 0; i < arrayDatos[0].length; i++){
	// if(!tempTablas.contains((arrayDatos)[0][i])){
	// tempTablas.add((arrayDatos)[0][i]);
	// tablas = tablas + " ," + (arrayDatos)[0][i];
	// }
	// }
	// return tablas;
	// }

	private static String getAtributos(String[][] arrayDatos) {
		String tempAtri = new String();

		tempAtri = arrayDatos[0][0] + " = " + arrayDatos[0][1];

		for (int i = 1; i < arrayDatos.length; i++) {
			tempAtri = tempAtri + " AND " + arrayDatos[i][0] + " = "
					+ arrayDatos[i][1];
		}
		return tempAtri;
	}

	public static void main(String[] args) {
		String[][] array = new String[2][2];
		array[0][0] = "Nombre";
		array[1][0] = "Puntuaciones";
		array[0][1] = "Aitor";
		array[1][1] = "2000";
		// busquedaCombinada("PUNTU_NIVEL", array);

	}
}
