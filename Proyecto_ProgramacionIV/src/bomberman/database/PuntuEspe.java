package bomberman.database;

import java.sql.Date;

public class PuntuEspe {
	/*
	 * Esta clase representa la fila de la tabla PUNTU_NIV_ESPECI y por eso
	 * tiene estos atributos: COD_PUNTU_ESPE, COD_PUNTU, PUNTU_ESPE y FECHA
	 */
	private int cod_puntu_espe;
	private int cod_puntu;
	private int puntu_espe;
	private String fecha;
	private int nivel;

	public PuntuEspe(int codPuntuEspe, int codPuntu, int puntuEspe,
			String date, int niv) {
		super();
		this.cod_puntu_espe = codPuntuEspe;
		this.cod_puntu = codPuntu;
		this.puntu_espe = puntuEspe;
		this.fecha = date;
		this.nivel = niv;
	}

	public int getCodPuntuEspe() {
		return cod_puntu_espe;
	}

	public void setCodPuntuEspe(int codPuntuEs) {
		this.cod_puntu_espe = codPuntuEs;
	}

	public int getCod_puntu_espe() {
		return cod_puntu_espe;
	}

	public void setCod_puntu_espe(int cod_puntu_espe) {
		this.cod_puntu_espe = cod_puntu_espe;
	}

	public int getCod_puntu() {
		return cod_puntu;
	}

	public void setCod_puntu(int cod_puntu) {
		this.cod_puntu = cod_puntu;
	}

	public int getPuntu_espe() {
		return puntu_espe;
	}

	public void setPuntu_espe(int puntu_espe) {
		this.puntu_espe = puntu_espe;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
