package bomberman.jugador;

import bomberman.enumeraciones.ModoJuego;

/**
 * Clase Jugador que representa los datos de la persona que está jugando.
 * 
 * @author David
 * @version 1.1
 */
public class Jugador {
	// Nombre
	private String nombre = null;
	// Apelidos
	private String apellidos = null;
	// Nick
	private String nick = null;
	// Email
	private String email = null;
	// Puntuación global
	private int puntuacion;
	// Puntuación del nivel
	private int puntuNivel;
	// Vidas
	private int vidas;
	// Nivel
	private int nivel;
	// Modo de Juego
	private ModoJuego modo;
	// Código de jugador
	private int codJugador;
	// Código de partida
	private int codPart;
	/*
	 * Guardamos el código ASCII de las teclas que acción el personaje para no
	 * tener que cogerlas todo el rato de la BD.
	 */
	private int arriba;
	private int abajo;
	private int derecha;
	private int izquierda;
	private int bomba;
	// Si queremos sonido
	private boolean sonido;
	// Si queremos envio de emails
	private boolean quiereEmail;

	/**
	 * Constructor simple de la clase Jugador.
	 */
	public Jugador() {
		this.puntuacion = 0;
		this.vidas = 3;
		this.nivel = 1;
	}

	/**
	 * Constructor más sofisticado de la clase Jugador.
	 * 
	 * @param nom
	 *            - String
	 * @param ape
	 *            - String
	 * @param ni
	 *            - String
	 * @param mail
	 *            - String
	 */
	public Jugador(String nom, String ape, String ni, String mail) {
		this.nombre = nom;
		this.apellidos = ape;
		this.nick = ni;
		this.email = mail;
	}

	/**
	 * Constructor complejo de la clase Jugador que recibe todos los parámetros
	 * posibles.
	 * 
	 * @param nom
	 *            - String
	 * @param ape
	 *            - String
	 * @param ni
	 *            - String
	 * @param mail
	 *            - String
	 * @param vid
	 *            - int
	 * @param punt
	 *            - int
	 * @param niv
	 *            - int
	 * @param mode
	 *            - ModoJuego
	 * @param codJug
	 *            - int
	 * @param codPartida
	 *            - int
	 * @param der
	 *            - int
	 * @param izq
	 *            - int
	 * @param arr
	 *            - int
	 * @param abj
	 *            - int
	 * @param bom
	 *            - int
	 * @param son
	 *            - boolean, sonido
	 * @param em
	 *            - boolean, email
	 */
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

	/**
	 * Devuelve e email del jugador
	 * 
	 * @return email - String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modificamos el email del jugador
	 * 
	 * @param email
	 *            - String
	 */
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

	/**
	 * Devuelve las vidas del jugador
	 * 
	 * @return vidas - int
	 */
	public int getVidas() {
		return vidas;
	}

	/**
	 * Modificamos las vidas del jugador
	 * 
	 * @param vidas
	 *            - int
	 */
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	/**
	 * Devuelve la puntuación del jugadr en el nivel en el que esta.
	 * 
	 * @return - int
	 */
	public int getPuntuNivel() {
		return puntuNivel;
	}

	/**
	 * Modificamos la puntuación del nivel del jugador.
	 * 
	 * @param puntuNivel
	 *            - int
	 */
	public void setPuntuNivel(int puntuNivel) {
		this.puntuNivel = puntuNivel;
	}

	/**
	 * Devuelve el nivel en el que está el jugador.
	 * 
	 * @return nivel - int
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Modifica el nivel en el que está el jugador.
	 * 
	 * @param nivel
	 *            - int
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Devuelve el ModoJuego del jugador.
	 * 
	 * @return modo - ModoJuego
	 */
	public ModoJuego getModo() {
		return modo;
	}

	/**
	 * Modificamos el ModoJuego del jugador.
	 * 
	 * @param modo
	 *            - ModoJuego
	 */
	public void setModo(ModoJuego modo) {
		this.modo = modo;
	}

	/**
	 * Devuelve el código de la partida
	 * 
	 * @return codPart - int
	 */
	public int getCodPart() {
		return codPart;
	}

	/**
	 * Modificamos el código de partida del jugador.
	 * 
	 * @param codPart
	 *            - int
	 */
	public void setCodPart(int codPart) {
		this.codPart = codPart;
	}

	/**
	 * Devuelve el código de jugador del jugador.
	 * 
	 * @return codJugador - int
	 */
	public int getCodJugador() {
		return codJugador;
	}

	/**
	 * Modificamos el código de jugador del jugador.
	 * 
	 * @param codJugador
	 */
	public void setCodJugador(int codJugador) {
		this.codJugador = codJugador;
	}

	/**
	 * Devuelve el código ASCII de la acción ir para arriba.
	 * 
	 * @return arriba - int
	 */
	public int getArriba() {
		return arriba;
	}

	/**
	 * Modifica el código ASCII de la acción ir para arriba.
	 * 
	 * @param arriba
	 *            - int
	 */
	public void setArriba(int arriba) {
		this.arriba = arriba;
	}

	/**
	 * Devuelve el código ASCII de la acción ir para abajo.
	 * 
	 * @return abajo - int
	 */
	public int getAbajo() {
		return abajo;
	}

	/**
	 * Modifica el código ASCII de la acción ir para abajo.
	 * 
	 * @param abajo
	 *            - int
	 */
	public void setAbajo(int abajo) {
		this.abajo = abajo;
	}

	/**
	 * Devuelve el código ASCII de la acción ir para la derecha.
	 * 
	 * @return derecha - int
	 */
	public int getDerecha() {
		return derecha;
	}

	/**
	 * Modifica el código ASCII de la acción ir para la derecha.
	 * 
	 * @param derecha
	 *            - int
	 */
	public void setDerecha(int derecha) {
		this.derecha = derecha;
	}

	/**
	 * Devuelve el código ASCII de la acción ir para la izquierda.
	 * 
	 * @return izquierda - int
	 */
	public int getIzquierda() {
		return izquierda;
	}

	/**
	 * Modifica el código ASCII de la acción ir para la izquierda.
	 * 
	 * @param izquierda
	 *            - int
	 */
	public void setIzquierda(int izquierda) {
		this.izquierda = izquierda;
	}

	/**
	 * Devuelve el código ASCII de la acción tirar bomba.
	 * 
	 * @return bomba - int
	 */
	public int getBomba() {
		return bomba;
	}

	/**
	 * Modifica el código ASCII de la acción tirar bomba.
	 * 
	 * @param bomba
	 *            - int
	 */
	public void setBomba(int bomba) {
		this.bomba = bomba;
	}

	/**
	 * Devuelve si el jugador quiere sonido o no.
	 * 
	 * @return sonido - boolean
	 */
	public boolean isSonido() {
		return sonido;
	}

	/**
	 * Modificamos el valor de sonido.
	 * 
	 * @param sonido
	 *            - boolean
	 */
	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	/**
	 * Devuelve si el jugador quiere recibir mensajes.
	 * 
	 * @return quiereEmail - boolean
	 */
	public boolean isQuiereEmail() {
		return quiereEmail;
	}

	/**
	 * Modifica si el jugador quiere recibir emails.
	 * 
	 * @param quiereEmail
	 *            - boolean
	 */
	public void setQuiereEmail(boolean quiereEmail) {
		this.quiereEmail = quiereEmail;
	}

	/**
	 * Para comprobar si estamos muertos.
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
