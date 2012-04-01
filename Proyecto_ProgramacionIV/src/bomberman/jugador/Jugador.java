package bomberman.jugador;

import bomberman.managers.*;

/**
 * Clase Jugador que representa los datos de la persona que est� jugando.
 * Implementa el interfaz Nivel.
 * 
 * @author David
 * @version 1.1
 */
public class Jugador {
	private String nombre = null;
	private String apellidos = null;
	private String nick = null;
	private String email = null;
	private int puntuacion;
	private int vidas;

	/**
	 * Constructor simple de la clase Jugador.
	 */
	public Jugador() {
		puntuacion = 0;
		vidas = 3;
	}

	public Jugador(String nom, String ape, String ni, String mail) {
		this.nombre = nom;
		this.apellidos = ape;
		this.nick = ni;
		this.email = mail;
	}

	/**
	 * Devuelve el nombre del jugador.
	 * 
	 * @return nombre - String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificamos el nombre del jugador.
	 * 
	 * @param nombre
	 *            - String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve los apellidos del jugador.
	 * 
	 * @return apellidos - String
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Modificamos los apellidos del jugador.
	 * 
	 * @param apellidos
	 *            - String
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Devuelve el nick del jugador.
	 * 
	 * @return Nick - String
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Modificamos el nick del jugador.
	 * 
	 * @param nick
	 *            - String
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve la puntuaci�n del jugador.
	 * 
	 * @return puntuacion - int
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Modificamos la puntuaci�n del jugador.
	 * 
	 * @param puntuacion
	 *            - int
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	/**
	 * Pra comprobar si estamos muertos.
	 * 
	 * @return boolean, true si estamos muertos - false si no estamos muertos.
	 */
	public boolean estoyMuerto() {
		if (this.getVidas() <= 0)
			return true;
		else
			return false;
	}
}
