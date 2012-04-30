package bomberman.outin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase comprueba que la direcci�n de un email tiene el formato correcto.
 * 
 * @author David
 * @version 1.0
 */
public class CompCorreo {

	/**
	 * Recibe una direcci�n de email en formato String y devuelve un valor
	 * booleano seg�n si es correcta o no.
	 * 
	 * @param correo
	 *            - String
	 * @return boolean
	 */
	public static boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		/*
		 * Compilamos la expresi�n regular para comprobar que el formato es el
		 * correcto.
		 */
		pat = Pattern
				.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		// Comprobamos la expresi�n con el email.
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}
}
