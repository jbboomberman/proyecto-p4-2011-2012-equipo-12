package bomberman.database;

/**
 * Clase que representa una linea de la tabla Nivel.
 * @author David
 * @version 1.0
 */
public class Nivel {
	
	//Código del nivel
	private int cod_nivel;
	//Nombre del nivel
	private String nom_nivel;
	//Tiempo del nivel
	private int tiempo;
	//Password del nivel
	private String pass;

	/**
	 * Constructor principal del nivel.
	 * @param cod_nivel - int
	 * @param nom_nivel - String
	 * @param tiempo - int
	 * @param pass - String
	 */
	public Nivel(int cod_nivel, String nom_nivel, int tiempo, String pass) {
		this.cod_nivel = cod_nivel;
		this.nom_nivel = nom_nivel;
		this.tiempo = tiempo;
		this.pass = pass;
	}
	
	/**
	 * Devuelve el código del nivel
	 * @return cod_nivel - int
	 */
	public int getCod_nivel() {
		return cod_nivel;
	}

	/**
	 * Modifica el código del nivel
	 * @param cod_nivel - int
	 */
	public void setCod_nivel(int cod_nivel) {
		this.cod_nivel = cod_nivel;
	}

	/**
	 * Devuelve el nombre del nivel
	 * @return nom_nivel - String
	 */
	public String getNom_nivel() {
		return nom_nivel;
	}

	/**
	 * Modifica el nombre del nivel
	 * @param nom_nivel - String
	 */
	public void setNom_nivel(String nom_nivel) {
		this.nom_nivel = nom_nivel;
	}

	/**
	 * Devuelve el tiempo del nivel
	 * @return tiempo - int
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * Modifica el tiempo del nivel
	 * @param tiempo - int
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Devuelve el password del nivel
	 * @return pass - String
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Modifica el password del nivel
	 * @param pass - String
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
}
