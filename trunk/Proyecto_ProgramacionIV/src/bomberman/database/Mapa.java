package bomberman.database;

/**
 * Representa una fila de la tabla Mapa.
 * @author David
 * @version 1.0
 */
public class Mapa {

	//Código del mapa
	private int cod_mapa;
	//Código del nivel
	private int cod_nivel;
	//Array de char que contiene el mapa.
	private char[] charArray;

	/**
	 * Constructor principal de la clase Mapa.
	 * @param mapa - int
	 * @param nivel - int
	 * @param array - char[]
	 */
	public Mapa(int mapa, int nivel, char[] array) {
		this.cod_mapa = mapa;
		this.cod_nivel = nivel;
		this.charArray = array;
	}

	/**
	 * Devuelve el código del mapa.
	 * @return cod_mapa - int
	 */
	public int getCod_mapa() {
		return cod_mapa;
	}

	/**
	 * Modifica el código del mapa.
	 * @param cod_mapa - int
	 */
	public void setCod_mapa(int cod_mapa) {
		this.cod_mapa = cod_mapa;
	}

	/**
	 * Devuelve el código del nivel
	 * @return cod_nivel - int
	 */
	public int getCod_nivel() {
		return cod_nivel;
	}

	/**
	 * Modifica el código del nivel.
	 * @param cod_nivel - int
	 */
	public void setCod_nivel(int cod_nivel) {
		this.cod_nivel = cod_nivel;
	}

	/**
	 * Devuelve el array de char del mapa
	 * @return charArray - char[]
	 */
	public char[] getCharArray() {
		return charArray;
	}

	/**
	 * Modifica el array de char del mapa.
	 * @param charArray - char[]
	 */
	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}
}
