package bomberman.database;

/**
 * Clase que representa una fila de la tabla Jugador.
 * No confundir con la clase Jugador del paquete
 * bomberman.jugador.
 * @author David
 * @version 1.0
 */
public class Jugador {

	//Código del jugador
	private int cod_jugador;
	//Nombre del jugador
	private String nomJugador;
	//Apellido del jugador
	private String apellJugador;
	//Nick del jugador
	private String nickJugador;
	//Email del jugador
	private String email;

	/**
	 * Constructor principal de la clase Jugador
	 * @param cod - int
	 * @param nom - String
	 * @param apell - Apellidos
	 * @param nick - Nick
	 * @param mail - Email
	 */
	public Jugador(int cod, String nom, String apell, String nick, String mail) {
		this.cod_jugador = cod;
		this.nomJugador = nom;
		this.apellJugador = apell;
		this.nickJugador = nick;
		this.email = mail;
	}

	/**
	 * Devuelve el código del jugador.
	 * @return cod_jugador - int
	 */
	public int getCod_jugador() {
		return cod_jugador;
	}

	/**
	 * Modifica es código del jugador
	 * @param cod_jugador - int
	 */
	public void setCod_jugador(int cod_jugador) {
		this.cod_jugador = cod_jugador;
	}
	
	/**
	 * Devuelve el nombre del jugador
	 * @return nomJugador
	 */
	public String getNomJugador() {
		return nomJugador;
	}

	/**
	 * Modifica el nombre del jugador
	 * @param nomJugador - String
	 */
	public void setNomJugador(String nomJugador) {
		this.nomJugador = nomJugador;
	}

	/**
	 * Devuelve el apellido del jugador
	 * @return apellJugador - String
	 */
	public String getApellJugador() {
		return apellJugador;
	}

	/**
	 * Modifica el apellido del jugador.
	 * @param apellJugador - String
	 */
	public void setApellJugador(String apellJugador) {
		this.apellJugador = apellJugador;
	}

	/**
	 * Devuelve el nick del jugador
	 * @return nickJugador - String
	 */
	public String getNickJugador() {
		return nickJugador;
	}

	/**
	 * Modifica el nick del jugador
	 * @param nickJugador - String
	 */
	public void setNickJugador(String nickJugador) {
		this.nickJugador = nickJugador;
	}

	/**
	 * Devuelve el email del jugador
	 * @return email - String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifica el email del jugador.
	 * @param email - String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
