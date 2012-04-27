package bomberman.database;

public class Mapa {
	/*
	 * Esta clase representa la fila de la tabla MAPA, tiene estos atributos:
	 * COD_MAPA, COD_NIV.
	 */
	
	private int cod_mapa;
	private int cod_nivel;
	private char[] charArray;
	
	public Mapa(int mapa, int nivel, char[]array){
		this.cod_mapa = mapa;
		this.cod_nivel = nivel;
		this.charArray = array;
	}

	public int getCod_mapa() {
		return cod_mapa;
	}

	public void setCod_mapa(int cod_mapa) {
		this.cod_mapa = cod_mapa;
	}

	public int getCod_nivel() {
		return cod_nivel;
	}

	public void setCod_nivel(int cod_nivel) {
		this.cod_nivel = cod_nivel;
	}

	public char[]getCharArray() {
		return charArray;
	}

	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}
}
