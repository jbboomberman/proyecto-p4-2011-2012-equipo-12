package bomberman.jugador;

import bomberman.enumeraciones.ModoJuego;

/**
 * Clase Jugador que representa los datos de la persona que está jugando.
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
	private int puntuNivel;
	private int vidas;
	private int nivel;
	private ModoJuego modo;
	private int codJugador;
	private int codPart;
	private int arriba;
	private int abajo;
	private int derecha;
	private int izquierda;
	private int bomba;
	private boolean sonido;
	private boolean quiereEmail;

	/**
	 * Constructor simple de la clase Jugador.
	 */
	public Jugador() {
		this.puntuacion = 0;
		this.vidas = 3;
		this.nivel = 1;
	}

	public Jugador(String nom, String ape, String ni, String mail) {
		this.nombre = nom;
		this.apellidos = ape;
		this.nick = ni;
		this.email = mail;
	}

	public Jugador(String nom, String ape, String ni, String mail, int vid,
			int punt, int niv, ModoJuego mode, int codJug, int codPartida,
			int der, int izq, int arr, int abj, int bom, boolean son, boolean em) {
		this.nombre = nom;
		this.apellidos = ape;
		this.nick = ni;
		this.email = mail;
		this.vidas = vid;
		this.puntuacion = punt;
		this.nivel = niv;
		this.modo = mode;
		this.codJugador = codJug;
		this.codPart = codPartida;
		this.derecha = der;
		this.izquierda = izq;
		this.abajo = abj;
		this.arriba = arr;
		this.bomba = bom;
		this.sonido = son;
		this.quiereEmail = em;
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
	 * Devuelve la puntuación del jugador.
	 * 
	 * @return puntuacion - int
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Modificamos la puntuación del jugador.
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

	public int getPuntuNivel() {
		return puntuNivel;
	}

	public void setPuntuNivel(int puntuNivel) {
		this.puntuNivel = puntuNivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public ModoJuego getModo() {
		return modo;
	}

	public void setModo(ModoJuego modo) {
		this.modo = modo;
	}

	public int getCodPart() {
		return codPart;
	}

	public void setCodPart(int codPart) {
		this.codPart = codPart;
	}

	public int getCodJugador() {
		return codJugador;
	}

	public void setCodJugador(int codJugador) {
		this.codJugador = codJugador;
	}

	public int getArriba() {
		return arriba;
	}

	public void setArriba(int arriba) {
		this.arriba = arriba;
	}

	public int getAbajo() {
		return abajo;
	}

	public void setAbajo(int abajo) {
		this.abajo = abajo;
	}

	public int getDerecha() {
		return derecha;
	}

	public void setDerecha(int derecha) {
		this.derecha = derecha;
	}

	public int getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(int izquierda) {
		this.izquierda = izquierda;
	}

	public int getBomba() {
		return bomba;
	}

	public void setBomba(int bomba) {
		this.bomba = bomba;
	}

	public boolean isSonido() {
		return sonido;
	}

	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	public boolean isQuiereEmail() {
		return quiereEmail;
	}

	public void setQuiereEmail(boolean quiereEmail) {
		this.quiereEmail = quiereEmail;
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
