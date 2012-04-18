package bomberman.database;

public class PuntuEspe {
/* Esta clase representa la fila de la tabla PUNTU_NIV_ESPECI y por eso
tiene estos atributos: COD_PUNTU_ESPE, COD_PUNTU, PUNTU_ESPE y FECHA*/
	private int COD_PUNTU_ESPE;
	private int COD_PUNTU; 
	private int PUNTU_ESPE;
	private String FECHA;
	
	public PuntuEspe(int cOD_PUNTU_ESPE, int cOD_PUNTU, int pUNTU_ESPE,
			String fECHA) {
		super();
		COD_PUNTU_ESPE = cOD_PUNTU_ESPE;
		COD_PUNTU = cOD_PUNTU;
		PUNTU_ESPE = pUNTU_ESPE;
		FECHA = fECHA;
	}
	public int getCOD_PUNTU_ESPE() {
		return COD_PUNTU_ESPE;
	}
	public void setCOD_PUNTU_ESPE(int cOD_PUNTU_ESPE) {
		COD_PUNTU_ESPE = cOD_PUNTU_ESPE;
	}
	public int getCOD_PUNTU() {
		return COD_PUNTU;
	}
	public void setCOD_PUNTU(int cOD_PUNTU) {
		COD_PUNTU = cOD_PUNTU;
	}
	public int getPUNTU_ESPE() {
		return PUNTU_ESPE;
	}
	public void setPUNTU_ESPE(int pUNTU_ESPE) {
		PUNTU_ESPE = pUNTU_ESPE;
	}
	public String getFECHA() {
		return FECHA;
	}
	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}
	
}
