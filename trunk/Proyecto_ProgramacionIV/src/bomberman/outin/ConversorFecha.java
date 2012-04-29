package bomberman.outin;

import java.util.Calendar;

public class ConversorFecha {

	public static String parsearFecha(String fecha){
		String tempString;		
		//Cogemos el año
		tempString = fecha.substring(0, 4) + "/";
		tempString = tempString.concat(fecha.substring(4, 6) + "/");
		tempString = tempString.concat(fecha.substring(6));
		
		return tempString;
	}
	
	public static String getFecha(){
		Calendar tempCalendar = Calendar.getInstance();
		String mes, dia;
		if (tempCalendar.get(Calendar.MONTH) + 1 < 10)
			mes = "0" + (tempCalendar.get(Calendar.MONTH) + 1);
		else
			mes = "" + (tempCalendar.get(Calendar.MONTH) + 1);
		if (tempCalendar.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		else
			dia = "" + tempCalendar.get(Calendar.DAY_OF_MONTH);
		
		return new String(
				tempCalendar.get(Calendar.YEAR) + mes
				+ dia);
	}
}
