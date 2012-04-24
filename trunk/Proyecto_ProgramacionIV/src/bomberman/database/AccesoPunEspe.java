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
	 * Representar� el acceso a la tabla PUNTU_NIV_ESPECI y tendr� tres m�todos
	 * est�ticos insertarPunt(PuntuEspe punt) que insertar� una puntuaci�n
	 * espec�fica en la tabla, eliminarPunt(PuntuEspe punt) que eliminar� una
	 * puntuaci�n espec�fica de la tabla y listaPunt() que listar� todas las
	 * puntuaciones espec�ficas de tabla.
	 */

	public static void insertarPunt(PuntuEspe punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO PUNTU_NIV_ESPECI VALUES( ?, ?, ?, ?, ?);");
			stat.setInt(1, punt.getCod_puntu_espe());
			stat.setInt(2, punt.getCod_puntu());
			stat.setInt(3, punt.getPuntu_espe());
			stat.setDate(4, punt.getFecha());
			stat.setInt(5, punt.getNivel());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarPunt(int cod_punt_espe) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"DELETE FROM PUNTU_NIV_ESPECI WHERE COD_PUNTU_ESPE = ?");
			stat.setInt(1, cod_punt_espe);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
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
					"SELECT * FROM PUNTU_NIV_ESPECI WHERE COD_PUNT = ?;");
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
	 * M�todo que nos devolver� hasta que nivel m�ximo se ha llegado en una
	 * partida guardada o puntuaci�n.
	 * 
	 * @param codPuntuGeneral
	 * @return int - N�mero con el nivel m�s alto al que se ha llegado.
	 */
	public static int getNivelMasAlto(int codPuntuGeneral){
		int nivMax = 1;
		ArrayList<PuntuEspe> tempArrayPuntuEspe = AccesoPunEspe.listaPuntu(codPuntuGeneral);
		for(PuntuEspe tempPuntuEspe : tempArrayPuntuEspe)
			nivMax = Math.max(nivMax, tempPuntuEspe.getNivel());
		return nivMax;
	}
}
