package bomberman.outin;

import javax.swing.JTextField;
import javax.swing.text.*;

/**
 * Clase LimitadorCaracters dise�ada para no permitIr que los datos introducidos
 * en un JTextField tengan m�s de un determinado n�mero de caracteres. Tambi�n
 * da la opci�n a elegir que tipo de caracteres quieres que se permitan. Hereda
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
	 *            - int. N�mero m�ximo de caracteres admitidos.
	 * @param sino
	 *            - boolean. Se admiten n�meros o no (true-s� false-no).
	 */
	public LimitadorCaracteres(JTextField texto, int num, boolean sino) {
		this.jTexto = texto;
		numeroCaracteres = num;
		numSino = sino;
	}

	/**
	 * Los objetos JTextField llam�n a este m�todo cada vez que quieren insertar
	 * un caracter. Este m�todo comprueba que no se excede el n�mero de
	 * caracteres escritos y tambi�n que tipo de caracteres se escriben.
	 * 
	 * tamano - int. Tama�o actual del texto. frase - String. Texto que queremos
	 * insertar. attribute - AttributeSet. Estilo del texto.
	 */
	public void insertString(int tamano, String frase, AttributeSet attribute)
			throws BadLocationException {
		for (int i = 0; i < frase.length(); i++) {
			// Comprobamos que no excedamos el l�mite de caracteres.
			if ((jTexto.getText().length() + frase.length()) > numeroCaracteres) {
				/*
				 * En caso de que haya llegado al l�mite pasamos el foco al
				 * siguiente componente.
				 */
				jTexto.transferFocus();
				return;
			} else if (!Character.isLetter(frase.charAt(i))
					&& !Character.isSpaceChar(frase.charAt(i))
					&& (Character.isDigit(frase.charAt(i)) && numSino == false)) {
				/*
				 * Si el caracter escrito es distinto de letra o n�mero no se
				 * escribe en el JTextField. En caso de ser n�mero habr� que ver
				 * si el valor booleano 'numSino' es verdadero, en caso de serlo
				 * s� se deja escribir, en caso contrario no.
				 */
				return;
			}
			// Llamamos al m�todo de la clase padre para que escriba el
			// caracter.
			super.insertString(tamano, frase, attribute);
		}
	}
}
