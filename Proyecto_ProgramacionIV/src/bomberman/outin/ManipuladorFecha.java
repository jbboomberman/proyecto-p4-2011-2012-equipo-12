package bomberman.outin;

import java.util.Calendar;

/**
 * Clase que nos permitirá manipulador fechas para poder gestionarlas
 * adecuadamente en la visualización por pantalla después de sacarlas de la BD.
 * 
 * @author David
 * @version 1.0
 */
public class ManipuladorFecha {

	/**
	 * Este método recibe un String con este formato 'YYYYMMDD' y nos devuelve
	 * un String con este formato 'YYYY/MM/DD'.
	 * 
	 * @param fecha
	 *            - String
	 * @return String
	 */
	public static String parsearFecha(String fecha) {
		String tempString;
		// Cogemos el año y le añadimos una barra.
		tempString = fecha.substring(0, 4) + "/";
		// Cogemos el mes
		tempString = tempString.concat(fecha.substring(4, 6) + "/");
		// Cogemos el dia
		tempString = tempString.concat(fecha.substring(6));

		return tempString;
	}
	
	/**
	 * Convierte un String con formato 'YYYY/MM/DD' a un
	 * String con formato 'YYYYMMDD'.
	 * @param fecha - String
	 * @return String
	 */
	public static String desParsearFecha(String fecha){
		String tempString;
		//Cogemos el año
		tempString = fecha.substring(0, 4);
		//Cogemos el mes
		tempString = tempString.concat(fecha.substring(5, 7));
		//Cogemos el dia
		tempString = tempString.concat(fecha.substring(8));
		
		return tempString;
	}

	/**
	 * Este método recibe un String con este formato 'HHmm' y nos devuelve un
	 * String con este formato 'HH:mm'.
	 * 
	 * @param hora
	 *            - String
	 * @return String
	 */
	public static String parsearHora(String hora) {
		String tempString;
		// Cogemos la hora
		tempString = hora.substring(0, 2) + ":";
		// Cogemos los minutos
		tempString = tempString.concat(hora.substring(2));

		return tempString;
	}

	/**
	 * Nos devuelve la fecha actual en este formato: 'YYYYMMDD'
	 * 
	 * @return String
	 */
	public static String getFecha() {
		/*
		 * Recibe un objeto calendario cuyos parámetros de tiempo se han
		 * inicializados a la fecha actual.
		 */
		Calendar tempCalendar = Calendar.getInstance();
		String mes, dia;
		// En caso de que el mes sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.MONTH) + 1 < 10)
			// Se suma uno porque los mese empiezan por 0.
			mes = "0" + (tempCalendar.get(Calendar.MONTH) + 1);
		else
			mes = "" + (tempCalendar.get(Calendar.MONTH) + 1);
		// En caso de que el día sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		else
			dia = "" + tempCalendar.get(Calendar.DAY_OF_MONTH);

		return new String(tempCalendar.get(Calendar.YEAR) + mes + dia);
	}

	/**
	 * Nos devuelve la hora actual en formato 'HHmm'
	 * 
	 * @return String
	 */
	public static String getHora() {
		/*
		 * Recibe un objeto calendario cuyos parámetros de tiempo se han
		 * inicializados a la fecha actual.
		 */
		Calendar tempCalendar = Calendar.getInstance();
		String hora, minuto;
		// En caso de que el mes sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.HOUR_OF_DAY) < 10)
			// Se suma uno porque los mese empiezan por 0.
			hora = "0" + (tempCalendar.get(Calendar.HOUR_OF_DAY));
		else
			hora = "" + (tempCalendar.get(Calendar.HOUR_OF_DAY));
		// En caso de que el día sea menor de 10 se le añade un '0'.
		if (tempCalendar.get(Calendar.MINUTE) < 10)
			minuto = "0" + tempCalendar.get(Calendar.MINUTE);
		else
			minuto = "" + tempCalendar.get(Calendar.MINUTE);

		return new String(hora + minuto);
	}
}