package bomberman.database;

public class PuntuGeneral {
	/*Esta clase representa la fila de la tabla PUNTUACION_GENERAL y
	por ese motivo tendr� estos atributos: COD_PUNT (C�digo de la puntaci�n), COD_JUG
	(C�digo del jugador), GUARDADO (Valor booleano para saber si es de tipo guardado o no),
	PUNTU (Puntuaci�n) y FECHA_ULTI_NIVEL (La fecha en la que se obtuvo esa puntuaci�n).
	Tendr� un constructor en el que deber� recibir todos estos par�metros y getters y setters
	de todos los par�metros.
*/

	private int cod_punt;
	private int cod_jug;
	private boolean guardado;
	private int puntu;
	private String fecha_ulti_nivel;
	
	public PuntuGeneral(int cod_punt, int cod_jug, boolean guardado, int puntu,
			String fecha_ulti_nivel) {
		super();
		this.cod_punt = cod_punt;
		this.cod_jug = cod_jug;
		this.guardado = guardado;
		this.puntu = puntu;
		this.fecha_ulti_nivel = fecha_ulti_nivel;
	}
	public int getCod_punt() {
		return cod_punt;
	}
	public void setCod_punt(int cod_punt) {
		this.cod_punt = cod_punt;
	}
	public int getCod_jug() {
		return cod_jug;
	}
	public void setCod_jug(int cod_jug) {
		this.cod_jug = cod_jug;
	}
	public boolean isGuardado() {
		return guardado;
	}
	public void setGuardado(boolean guardado) {
		this.guardado = guardado;
	}
	public int getPuntu() {
		return puntu;
	}
	public void setPuntu(int puntu) {
		this.puntu = puntu;
	}
	public String getFecha_ulti_nivel() {
		return fecha_ulti_nivel;
	}
	public void setFecha_ulti_nivel(String fecha_ulti_nivel) {
		this.fecha_ulti_nivel = fecha_ulti_nivel;
	}
	
}
