package bomberman.database;

public class Nivel {
	/*
	 * Esta clase representa la fila de la tabla NIVEL, tiene estos atributos:
	 * COS_NIVEL, NOM_NIVEL, TIEMPO y PASS.
	 */
	private int cod_nivel;
	private String nom_nivel;
	private int tiempo;
	private String pass;

	public int getCod_nivel() {
		return cod_nivel;
	}

	public void setCod_nivel(int cod_nivel) {
		this.cod_nivel = cod_nivel;
	}

	public String getNom_nivel() {
		return nom_nivel;
	}

	public void setNom_nivel(String nom_nivel) {
		this.nom_nivel = nom_nivel;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Nivel(int cod_nivel, String nom_nivel, int tiempo, String pass) {
		super();
		this.cod_nivel = cod_nivel;
		this.nom_nivel = nom_nivel;
		this.tiempo = tiempo;
		this.pass = pass;
	}
}
