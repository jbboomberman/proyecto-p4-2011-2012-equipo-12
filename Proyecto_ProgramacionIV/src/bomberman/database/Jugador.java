package bomberman.database;

public class Jugador {

	private int cod_jugador;
	private String nomJugador;
	private String apellJugador;
	private String nickJugador;
	private String email;
	
	public Jugador(int cod, String nom, String apell, String nick, String mail){
		this.cod_jugador = cod;
		this.nomJugador = nom;
		this.apellJugador = apell;
		this.nickJugador = nick;
		this.email = mail;
	}

	public int getCod_jugador() {
		return cod_jugador;
	}

	public void setCod_jugador(int cod_jugador) {
		this.cod_jugador = cod_jugador;
	}

	public String getNomJugador() {
		return nomJugador;
	}

	public void setNomJugador(String nomJugador) {
		this.nomJugador = nomJugador;
	}

	public String getApellJugador() {
		return apellJugador;
	}

	public void setApellJugador(String apellJugador) {
		this.apellJugador = apellJugador;
	}

	public String getNickJugador() {
		return nickJugador;
	}

	public void setNickJugador(String nickJugador) {
		this.nickJugador = nickJugador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
