package bomberman.outin;

import java.util.Calendar;

/**
 * Clase que nos permitirá manipulador fechas para poder
 * gestionarlas adecuadamente en la visualización por 
 * pantalla después de sacarlas de la BD.
 * @author David
 * @version 1.0
 */
public class ManipuladorFecha {

	/**
	 * Este método recibe un String con este formato 'YYYYMMDD'
	 * y nos devuelve un String con este formato 'YYYY/MM/DD'.
	 * @param fecha - String
	 * @return String
	 */
	public static String parsearFecha(String fecha){
		String tempString;		
		//Cogemos el año y le añadimos una barra.
		tempString = fecha.substring(0, 4) + "/";
		//Cogemos el mes
		tempString = tempString.concat(fecha.substring(4, 6) + "/");
		//Cogemos el dia
		tempString = tempString.concat(fecha.substring(6));
		
		return tempString;
	}
	
	/**
	 * Nos devuelve la fecha actual en este formato: 'YYYYMMDD'
	 * @return String
	 */
	public static String getFecha(){
		/*
		 * Recibe un objeto calendario cuyos parámetros de tiempo
		 * se han inicializados a la fecha actual.
		 */
		Calendar tempCalendar = Calendar.getInstance();
		String mes, dia;
		//En caso de que el mes sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.MONTH) + 1 < 10)
			//Se suma uno porque los mese empiezan por 0.
			mes = "0" + (tempCalendar.get(Calendar.MONTH) + 1);
		else
			mes = "" + (tempCalendar.get(Calendar.MONTH) + 1);
		//En caso de que el día sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		else
			dia = "" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		
		return new String(
				tempCalendar.get(Calendar.YEAR) + mes
				+ dia);
	}
}