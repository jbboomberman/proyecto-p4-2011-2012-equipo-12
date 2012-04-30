package bomberman.outin;

/**
 * Excepción para los casos en que el los minutos o segundos
 * del reloj esten fuera de rango.
 * @author David
 * @version 1.0
 */
public class RelojException extends Exception {
	
	private static final long serialVersionUID = -7461719037402108362L;
	
	/**
	 * Constructor principal de la excepción RelojException
	 * @param s - String
	 */
	public RelojException(String s) {
		super(s);
	}
}
