package bomberman.outin;

import javax.swing.JTextField;
import javax.swing.text.*;

/**
 * Clase LimitadorCaracters diseñada para no permitIr que los datos introducidos
 * en un JTextField tengan más de un determinado número de caracteres. También
 * da la opción a elegir que tipo de caracteres quieres que se permitan. Hereda
 * de PlainDocument.
 * 
 * @author David
 * @version 1.0
 */
public class LimitadorCaracteres extends PlainDocument {

	private static final long serialVersionUID = -7461719037402108362L;
	private JTextField jTexto;
	private int numeroCaracteres;
	private boolean numSino;

	/**
	 * Constructor principal de la clase LimitadorCaracteres
	 * 
	 * @param texto
	 *            - JTextField. El JTextField al que hay que limitar la entrada
	 *            de caracteres.
	 * @param num
	 *            - int. Número máximo de caracteres admitidos.
	 * @param sino
	 *            - boolean. Se admiten números o no (true-sí false-no).
	 */
	public LimitadorCaracteres(JTextField texto, int num, boolean sino) {
		this.jTexto = texto;
		numeroCaracteres = num;
		numSino = sino;
	}

	/**
	 * Los objetos JTextField llamán a este método cada vez que quieren insertar
	 * un caracter. Este método comprueba que no se excede el número de
	 * caracteres escritos y también que tipo de caracteres se escriben.
	 * 
	 * tamano - int. Tamaño actual del texto. frase - String. Texto que queremos
	 * insertar. attribute - AttributeSet. Estilo del texto.
	 */
	public void insertString(int tamano, String frase, AttributeSet attribute)
			throws BadLocationException {
		for (int i = 0; i < frase.length(); i++) {
			// Comprobamos que no excedamos el límite de caracteres.
			if ((jTexto.getText().length() + frase.length()) > numeroCaracteres) {
				/*
				 * En caso de que haya llegado al límite pasamos el foco al
				 * siguiente componente.
				 */
				jTexto.transferFocus();
				return;
			} else if (!Character.isLetter(frase.charAt(i))
					&& !Character.isSpaceChar(frase.charAt(i))
					&& (Character.isDigit(frase.charAt(i)) && numSino == false)) {
				/*
				 * Si el caracter escrito es distinto de letra o número no se
				 * escribe en el JTextField. En caso de ser número habrá que ver
				 * si el valor booleano 'numSino' es verdadero, en caso de serlo
				 * sí se deja escribir, en caso contrario no.
				 */
				return;
			}
			// Llamamos al método de la clase padre para que escriba el
			// caracter.
			super.insertString(tamano, frase, attribute);
		}
	}
}
