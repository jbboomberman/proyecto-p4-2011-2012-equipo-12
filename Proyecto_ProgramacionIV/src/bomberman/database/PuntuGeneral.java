package bomberman.database;

/**
 * Clase que representa una linea de la tabla PUNTUACION_GENERAL.
 * Implementa la interfaz Comparable porque queremos compararlas.
 * @author David
 * @version 1.0
 */
public class PuntuGeneral implements Comparable<PuntuGeneral> {

	//C�digo de la puntuaci�n general
	private int cod_punt;
	//C�digo del jugador
	private int cod_jug;
	//Partida guardad o no
	private boolean guardado;
	//Puntuaci�n general
	private int puntu;
	//Fecha en la que se jug� el �ltimo nivel
	private String fecha_ulti_nivel;
	//Vidas
	private int vidas;
	//Nivel guardado, s�lo para partidas guardadas
	private int niv_guar;

	/**
	 * Constructor principal de la clase PuntuGeneral
	 * @param cod_punt - int
	 * @param cod_jug - int
	 * @param guardado - boolean
	 * @param puntu - int
	 * @param fecha_ulti_nivel - String
	 * @param vida - int
	 * @param nivel - int
	 */
	public PuntuGeneral(int cod_punt, int cod_jug, boolean guardado, int puntu,
			String fecha_ulti_nivel, int vida, int nivel) {
		super();
		this.cod_punt = cod_punt;
		this.cod_jug = cod_jug;
		this.guardado = guardado;
		this.puntu = puntu;
		this.fecha_ulti_nivel = fecha_ulti_nivel;
		this.vidas = vida;
		this.niv_guar = nivel;
	}

	/**
	 * Devuelve el c�digo de la puntuaci�n general
	 * @return cod_punt - int
	 */
	public int getCod_punt() {
		return cod_punt;
	}

	/**
	 * Modifica el c�digo de la puntuaci�n.
	 * @param cod_punt - int
	 */
	public void setCod_punt(int cod_punt) {
		this.cod_punt = cod_punt;
	}

	/**
	 * Devuelve el c�digo del jugador
	 * @return cod_jug
	 */
	public int getCod_jug() {
		return cod_jug;
	}

	/**
	 * Modifica el c�digo del jugador
	 * @param cod_jug - int
	 */
	public void setCod_jug(int cod_jug) {
		this.cod_jug = cod_jug;
	}

	/**
	 * Para saber si esa linea representa una partida guardada
	 * o no.
	 * @return boolean
	 */
	public boolean isGuardado() {
		return guardado;
	}

	/**
	 * Modifica el estado de la puntuaci�n
	 * @param guardado - boolean
	 */
	public void setGuardado(boolean guardado) {
		this.guardado = guardado;
	}

	/**
	 * Devuelve la puntuaci�n de la puntuaci�n
	 * general
	 * @return puntu - int
	 */
	public int getPuntu() {
		return puntu;
	}

	/**
	 * Modifica la puntuaci�n de la puntuaci�n general
	 * @param puntu - int
	 */
	public void setPuntu(int puntu) {
		this.puntu = puntu;
	}

	/**
	 * Devuelve la fecha en la que se jug� el
	 * �ltimo nivel.
	 * @return fecha_ulti_nivel - String
	 */
	public String getFecha_ulti_nivel() {
		return fecha_ulti_nivel;
	}

	/**
	 * Modifica la fecha en la que se jug� el �ltimo
	 * nivel.
	 * @param fecha_ulti_nivel - String
	 */
	public void setFecha_ulti_nivel(String fecha_ulti_nivel) {
		this.fecha_ulti_nivel = fecha_ulti_nivel;
	}

	/**
	 * Devuelve el n�mero de vidas.
	 * Normalmente 0 pero si es guardada ser�
	 * mayor que cero.
	 * @return vidas - int
	 */
	public int getVidas() {
		return vidas;
	}

	/**
	 * Modifica el n�umero de vidas.
	 * @param vidas - int
	 */
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	/**
	 * Devuelve el nivel en el que se guard� la partida
	 * @return niv_guar - int
	 */
	public int getNiv_guar() {
		return niv_guar;
	}

	/**
	 * Modifica el nivel en el que se guard� la
	 * partida.
	 * @param niv_guar - int
	 */
	public void setNiv_guar(int niv_guar) {
		this.niv_guar = niv_guar;
	}

	/**
	 * M�todo obligado implementar si implementas la interfaz
	 * Comparable. Nos sirve para determinar que
	 * puntuaci�n general entre dos tiene una
	 * puntuaci�n mayor. 1 sila recibida por
	 * par�metro es menor, 0 si son iguales y -1
	 * si la recibida por par�metro es mayor.
	 */
	public int compareTo(PuntuGeneral tempGeneral) {
		if (this.getPuntu() > tempGeneral.getPuntu())
			return 1;
		else if (this.getPuntu() == tempGeneral.getPuntu())
			return 0;
		else
			return -1;
	}
}
