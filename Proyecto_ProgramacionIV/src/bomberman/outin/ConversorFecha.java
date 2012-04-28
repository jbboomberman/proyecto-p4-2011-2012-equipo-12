package bomberman.outin;

public class ConversorFecha {

	public static String parsearFecha(String fecha){
		String tempString;
		
		System.out.println("Fecha que recibimos: " + fecha);
		System.out.println("Primer trozo: " + fecha.substring(0, 4) + "/");
		System.out.println("Segundo trozo: " + fecha.substring(4, 6) + "/");
		System.out.println("Tercer trozo: " + fecha.substring(6, 8));
		
		//Cogemos el año
		tempString = fecha.substring(0, 4) + "/";
		tempString = tempString.concat(fecha.substring(4, 6) + "/");
		tempString = tempString.concat(fecha.substring(6, 8));
		
		System.out.println("Resultado: " + tempString);
		
		return tempString;
	}
}
