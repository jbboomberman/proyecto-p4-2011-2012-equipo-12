package bomberman.outin;

public class ConversorFecha {

	public static String parsearFecha(String fecha){
		String tempString;		
		//Cogemos el año
		tempString = fecha.substring(0, 4) + "/";
		tempString = tempString.concat(fecha.substring(4, 6) + "/");
		tempString = tempString.concat(fecha.substring(6, 8));
		
		return tempString;
	}
}
