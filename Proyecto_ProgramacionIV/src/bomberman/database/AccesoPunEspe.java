package bomberman.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bomberman.jugador.Jugador;
import bomberman.outin.VerificadorFecha;

public class AccesoPunEspe {
	/*
	 * Representará el acceso a la tabla PUNTU_NIV_ESPECI y tendrá tres métodos
	 * estáticos insertarPunt(PuntuEspe punt) que insertará una puntuación
	 * específica en la tabla, eliminarPunt(PuntuEspe punt) que eliminará una
	 * puntuación específica de la tabla y listaPunt() que listará todas las
	 * puntuaciones específicas de tabla.
	 */

	public static void insertarPunt(PuntuEspe punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
			"INSERT INTO PUNTU_NIV_ESPECI VALUES( ?, ?, ?, ?, ?);");
			stat.setInt(1, punt.getCod_puntu_espe());
			stat.setInt(2, punt.getCod_puntu());
			stat.setInt(3, punt.getPuntu_espe());
			stat.setString(4, punt.getFecha());
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
						rs.getInt(3), rs.getString(4), rs.getInt(5));
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
		System.out.println(nivMax);
		return nivMax;
	}
	
	public static int getNumPunt() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTU_NIV_ESPECI;");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				cont++;
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	public static ArrayList<PuntuGeneral> getPuntDatos(String nom, String fecha, int nivel) {
		PreparedStatement stat = null;
		ArrayList<PuntuGeneral> tempPuntu = new ArrayList<PuntuGeneral>();
		try {
			/*
			 * En caso de que tanto nombre como fecha sean distintos de null.
			 */
			if (nom != null && fecha != null) {
				stat = GestionBD
						.conectar()
						.prepareStatement(
								"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ? AND FECHA_ULTI_NIVEL = ? AND COD_NIVEL_PUNTU = ?;");
				/*
				 * En caso de que no exista el código del Jugador o la fecha sea
				 * incorrecta se devuelve null.
				 */
				if (AccesoJugador.getCodJugador(nom) == -1 || !VerificadorFecha.comprobarFecha(fecha))
					return null;
				else {
						stat.setInt(1, AccesoJugador.getCodJugador(nom));
						stat.setString(2, fecha);
						stat.setInt(3, nivel);
						ResultSet rs = stat.executeQuery();
						if(!rs.next())return null;
						while (rs.next()) {
							//Si es 'true' es partida guardada.
							if (!rs.getBoolean(3))
								tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
										.getInt(2), rs.getBoolean(3), rs
										.getInt(4), rs.getString(5), rs.getInt(6)));
						}
						return tempPuntu;
				}
			/*
			 * En caso de que fecha sea igual a null.
			 */
			} else if (nom != null && fecha == null) {
				stat = GestionBD.conectar().prepareStatement(
						"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ? AND COD_NIVEL_PUNTU = ?;");
				if (AccesoJugador.getCodJugador(nom) == -1)
					return null;
				else {
					stat.setInt(1, AccesoJugador.getCodJugador(nom));
					stat.setInt(2, nivel);
					ResultSet rs = stat.executeQuery();
					while (rs.next()) {
						if (!rs.getBoolean(3))
							tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
									.getInt(2), rs.getBoolean(3), rs.getInt(4),
									rs.getString(5), rs.getInt(6)));
					}
					return tempPuntu;
				}
			/*
			 * En caso de que nombre del Jugador sea igual a null.
			 */
			} else if (nom == null && fecha != null) {
				stat = GestionBD
						.conectar()
						.prepareStatement(
								"SELECT * FROM PUNTUACION_GENERAL WHERE FECHA_ULTI_NIVEL = ? AND COD_NIVEL_PUNTU = ?;");
					if (!VerificadorFecha.comprobarFecha(fecha))
						return null;
					stat.setString(1, fecha);
					stat.setInt(2, nivel);
					ResultSet rs = stat.executeQuery();
					while (rs.next()) {
						if (!rs.getBoolean(3))
							tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
									.getInt(2), rs.getBoolean(3), rs.getInt(4),
									rs.getString(5), rs.getInt(6)));
					}
					return tempPuntu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;
	}
}
