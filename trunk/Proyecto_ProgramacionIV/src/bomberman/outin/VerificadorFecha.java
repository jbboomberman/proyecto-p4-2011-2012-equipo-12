package bomberman.outin;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VerificadorFecha {

	 private static final String REGEX = "\\d{4}/\\d{2}/\\d{2}";
	  /*
	  * Matches the following date patterns
	  * yyyy/mm/dd
	  */

	  private static boolean validateDayMonth(String strDate){
	     boolean isValid = false;
	     
	     String[] dateArray = strDate.split("/");
	     
	     int year = Integer.valueOf(dateArray[0]).intValue();
	     int month = Integer.valueOf(dateArray[1]).intValue();
	     int day = Integer.valueOf(dateArray[2]).intValue();


	     if ( (day > 0 && day <= 31) && (month > 0 && month <= 12) ){
	        /*
	        * should be correct for most cases but still will not be correct in fringe cases like
	        * feb having 30 days or april having 31 days.
	        */

	        isValid = true;
	        try   {
	           GregorianCalendar cal = new GregorianCalendar();

	           /*
	           *  setLenient to false to force calendar to throw
	* IllegalArgumentException in case
	           *  any field, day, month or year is not valid (invalid year would be '00')
	           */
	           cal.setLenient(false);
	           // month - 1 is done because Calander uses 0-11 for months
	           cal.set(year, (month-1), day);            
	           /*
	           * add is called just to invoke the method
	* Calendar.complete(). complete() is the method
	           *  that throws the IllegalArgumentException.
	           *  
	           *  Note : Calendar.set() does not compute the date fields
	* only methods like add(),
	           *  roll() or getTime() force the Calendar object to calculate
	* field values
	           */
	           // done only to force Calendar to compute all fields
	           cal.add(Calendar.SECOND, 1);
	        }catch (IllegalArgumentException iae){
	           isValid = false;
	        }
	     }
	     return isValid;
	  } 
	public static boolean comprobarFecha(String fecha){
		     Pattern p = Pattern.compile(REGEX);
		     Matcher m = p.matcher(fecha);

		     if((m.matches() == true) && validateDayMonth(fecha)){
		        return true;
		     }else{
		        return false;
		     }
	}
}
