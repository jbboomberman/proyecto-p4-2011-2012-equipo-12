package bomberman.database;

/**
 * Representa una fila de la tabla Controles.
 * @author David
 * @version 1.0
 */
public class Controles {

	//Código
	private int cod_accion;
	//Nombre
	private String nom_accion;
	//Código ASCII
	private int cod_ascii_tecla;
	//Tipo jugador
	private int tipo_jug;

	/**
	 * Constructor principal de la clase Controles.
	 * @param accion - int
	 * @param nom - String
	 * @param ascii - int
	 * @param tipo - int
	 */
	public Controles(int accion, String nom, int ascii, int tipo) {
		this.cod_accion = accion;
		this.nom_accion = nom;
		this.cod_ascii_tecla = ascii;
		this.tipo_jug = tipo;
	}

	/**
	 * Devuelve el código de la acción
	 * @return cod_accion - int
	 */
	public int getCod_accion() {
		return cod_accion;
	}

	/**
	 * Modifica el código de la acción
	 * @param cod_accion - int
	 */
	public void setCod_accion(int cod_accion) {
		this.cod_accion = cod_accion;
	}

	/**
	 * Devuelve el nombre de la acción
	 * @return nom_accion - String
	 */
	public String getNom_accion() {
		return nom_accion;
	}

	/**
	 * Modifica el nombre de la acción.
	 * @param nom_accion - String
	 */
	public void setNom_accion(String nom_accion) {
		this.nom_accion = nom_accion;
	}

	/**
	 * Devuelve el código de la tecla ASCII.
	 * @return cod_ascii_tecla - int
	 */
	public int getCod_ascii_tecla() {
		return cod_ascii_tecla;
	}

	/**
	 * Modifica el código de la tecla ASCII
	 * @param cod_ascii_tecla - int
	 */
	public void setCod_ascii_tecla(int cod_ascii_tecla) {
		this.cod_ascii_tecla = cod_ascii_tecla;
	}

	/**
	 * Devuelve el tipo de jugador
	 * @return tipo_jug - int
	 */
	public int getTipo_jug() {
		return tipo_jug;
	}

	/**
	 * Modifica el tipo de jugador
	 * @param tipo_jug - int
	 */
	public void setTipo_jug(int tipo_jug) {
		this.tipo_jug = tipo_jug;
	}
}
