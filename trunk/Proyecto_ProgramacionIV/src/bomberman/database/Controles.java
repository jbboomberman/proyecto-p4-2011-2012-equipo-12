package bomberman.database;

public class Controles {
	/*
	 * Representa una fila de la tabla CONTROLES, tiene estos atributos:
	 * COD_ACCION, NOM_ACCION, COD_ASCII_TECLA y TIPO_JUG.
	 */

	private int cod_accion;
	private String nom_accion;
	private int cod_ascii_tecla;
	private int tipo_jug;

	public Controles(int accion, String nom, int ascii, int tipo) {
		this.cod_accion = accion;
		this.nom_accion = nom;
		this.cod_ascii_tecla = ascii;
		this.tipo_jug = tipo;
	}

	public int getCod_accion() {
		return cod_accion;
	}

	public void setCod_accion(int cod_accion) {
		this.cod_accion = cod_accion;
	}

	public String getNom_accion() {
		return nom_accion;
	}

	public void setNom_accion(String nom_accion) {
		this.nom_accion = nom_accion;
	}

	public int getCod_ascii_tecla() {
		return cod_ascii_tecla;
	}

	public void setCod_ascii_tecla(int cod_ascii_tecla) {
		this.cod_ascii_tecla = cod_ascii_tecla;
	}

	public int getTipo_jug() {
		return tipo_jug;
	}

	public void setTipo_jug(int tipo_jug) {
		this.tipo_jug = tipo_jug;
	}
}
