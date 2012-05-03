package bomberman.database;

/**
 * Representa una fila de la tabla PUNTU_NIV_ESPECI.
 * Implementa la interfaz Comparable porque queremos
 * poder comparar puntuaciones.
 * @author David
 * @version 1.0
 */
public class PuntuEspe implements Comparable<PuntuEspe>{
	
	//Código de la puntuación específica
	private int cod_puntu_espe;
	//Código de la puntuación
	private int cod_puntu;
	//Puntuación especial
	private int puntu_espe;
	//Fecha
	private String fecha;
	//Nivel
	private int nivel;

	/**
	 * Constructor principal de la clase PuntuEspe.
	 * @param codPuntuEspe - int
	 * @param codPuntu - int
	 * @param puntuEspe - int
	 * @param date - String
	 * @param niv - int
	 */
	public PuntuEspe(int codPuntuEspe, int codPuntu, int puntuEspe,
			String date, int niv) {
		this.cod_puntu_espe = codPuntuEspe;
		this.cod_puntu = codPuntu;
		this.puntu_espe = puntuEspe;
		this.fecha = date;
		this.nivel = niv;
	}

	
	/**
	 * Devuelve el código de la puntuación específica
	 * @return cod_puntu_espe - int
	 */
	public int getCod_puntu_espe() {
		return cod_puntu_espe;
	}

	/**
	 * Modifica el código de la puntuación específica
	 * @param cod_puntu_espe - int
	 */
	public void setCod_puntu_espe(int cod_puntu_espe) {
		this.cod_puntu_espe = cod_puntu_espe;
	}

	/**
	 * Devuelve el código de la puntuación general
	 * a la que pertenece. Si no pertenece a ninguna
	 * porque se ha jugado en modo Master tendrá
	 * el código del jugador.
	 * @return cod_puntu - int
	 */
	public int getCod_puntu() {
		return cod_puntu;
	}

	/**
	 * Modifica el código de la puntuación general
	 * @param cod_puntu - int
	 */
	public void setCod_puntu(int cod_puntu) {
		this.cod_puntu = cod_puntu;
	}

	/**
	 * Devuelve la puntuación específica
	 * @return puntu_espe - int
	 */
	public int getPuntu_espe() {
		return puntu_espe;
	}

	/**
	 * Modifica la puntuación específica
	 * @param puntu_espe - int
	 */
	public void setPuntu_espe(int puntu_espe) {
		this.puntu_espe = puntu_espe;
	}

	/**
	 * Devuelve la fecha en la que se consiguio la puntuación
	 * @return fecha - String
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha en la que se consiguio la puntuación
	 * @param fecha - String
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el nivel en el que se consiguio
	 * la puntuación
	 * @return nivel - int
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Modifica el nivel en el que se consiguio
	 *  la puntuación.
	 * @param nivel - int
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * Método de la interfaz Comparable.
	 * Devuelve 1 si la PuntuEspe recibida
	 * por parámetro es menor, 0 si es igual y
	 * 1 si es mayor.
	 * @param tempGeneral - PuntuEspe
	 */
	public int compareTo(PuntuEspe tempGeneral) {
		if (this.getPuntu_espe() > tempGeneral.getPuntu_espe())
			return 1;
		else if (this.getPuntu_espe() == tempGeneral.getPuntu_espe())
			return 0;
		else
			return -1;
	}
}
