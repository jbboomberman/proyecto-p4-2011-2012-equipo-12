package bomberman.outin;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Esta clase nos permite comprobar si una fecha tiene un formato correcto. El
 * formato que verifica es este: yyyy/mm/dd
 * 
 * @author David
 * @version 1.0
 */
public class VerificadorFecha {

	/*
	 * Utilizamos una expresión regular para comprobar que se cumple el formato.
	 * Queremos que tenga cuatro dígitos, luego una '/' seguida de dos dígitos y
	 * una '/' acabando con otros dos dígitos.
	 */
	private static final String REGEX = "\\d{4}/\\d{2}/\\d{2}";

	/**
	 * Valida que la fecha es correcta. En cuanto
	 * a números lógicos se refiere. Por ejemplo, no
	 * dejar poner un 34 como mes.
	 * @param strDate
	 *            - String
	 * @return boolean
	 */
	private static boolean validateDayMonth(String strDate) {
		boolean isValid = false;

		String[] dateArray = strDate.split("/");

		int year = Integer.valueOf(dateArray[0]).intValue();
		int month = Integer.valueOf(dateArray[1]).intValue();
		int day = Integer.valueOf(dateArray[2]).intValue();

		if ((day > 0 && day <= 31) && (month > 0 && month <= 12)) {
			/*
			 * Para casos como Febrero no es del todo correcto.
			 */
			isValid = true;
			try {
				GregorianCalendar cal = new GregorianCalendar();
				/*
				 * Para que nos lance IllegalArgumentException en caso de error.
				 */
				cal.setLenient(false);
				// Los meses se miden de 0 a 11.
				cal.set(year, (month - 1), day);
				cal.add(Calendar.SECOND, 1);
			} catch (IllegalArgumentException iae) {
				isValid = false;
			}
		}
		return isValid;
	}

	/**
	 * Esta clase recibe un parámetro de la clase String y devuelve un valor
	 * booleano según si la fecha es correcta o no.
	 * 
	 * @param fecha
	 *            - String
	 * @return - boolean
	 */
	public static boolean comprobarFecha(String fecha) {
		/*
		 * Para comprobar si la fecha cumple el
		 * formato.
		 */
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(fecha);

		/*
		 * Si la fecha cumple el formato y los valores son
		 * correctos entonces es correcta.
		 */
		if ((m.matches() == true) && validateDayMonth(fecha)) {
			return true;
		} else {
			return false;
		}
	}
}
