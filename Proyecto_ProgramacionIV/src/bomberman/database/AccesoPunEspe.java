package bomberman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bomberman.jugador.Jugador;

public class AccesoPunEspe {
	/*
	 * Representará el acceso a la tabla PUNTU_NIV_ESPECI y tendrá tres métodos
	 * estáticos insertarPunt(PuntuEspe punt) que insertará una puntuación
	 * específica en la tabla, eliminarPunt(PuntuEspe punt) que eliminará una
	 * puntuación específica de la tabla y listaPunt() que listará todas las
	 * puntuaciones específicas de tabla.
	 */

	public static void insertarPunt(PuntuEspe punt) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			String s = "insert into PUNTU_NIV_ESPECI VALUES ("
					+ punt.getCod_puntu() + " " + punt.getCod_puntu_espe()
					+ " " + punt.getPuntu_espe() + " '" + punt.getFecha()
					+ "');";
			System.out.println("s");
			ResultSet rs = stat.executeQuery(s);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void eliminarPunt(PuntuEspe punt) {
		Statement stat;
		try {
			stat = GestionBD.conexion.createStatement();
			ResultSet rs = stat
					.executeQuery("delete from PUNTU_NIV_ESPECI VALUES where COD_PUNTU_ESPE='"
							+ punt.getCod_puntu_espe() + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// public static ArrayList<PuntuEspe> listaPunt() {
	//
	// }

	public static ArrayList<PuntuEspe> listaPuntu(int codPuntuGene) {
		PuntuEspe tempJug = null;
		ArrayList<PuntuEspe> tempPuntu = new ArrayList<PuntuEspe>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTU_NIV_ESPECI WHERE COD_PUNT = ?");
			stat.setInt(1, codPuntuGene);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				tempJug = new PuntuEspe(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), rs.getDate(4), rs.getInt(5));
				tempPuntu.add(tempJug);
			}
			rs.close();
			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;
	}

	/**
	 * Método que nos devolverá hasta que nivel máximo se ha llegado en una
	 * partida guardada o puntuación.
	 * 
	 * @param codPuntuGeneral
	 * @return int - Número con el nivel más alto al que se ha llegado.
	 */
	public static int getNivelMasAlto(int codPuntuGeneral){
		int nivMax = 1;
		ArrayList<PuntuEspe> tempArrayPuntuEspe = AccesoPunEspe.listaPuntu(codPuntuGeneral);
		for(PuntuEspe tempPuntuEspe : tempArrayPuntuEspe)
			nivMax = Math.max(nivMax, tempPuntuEspe.getNivel());
		return nivMax;
	}
}
