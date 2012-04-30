package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bomberman.outin.VerificadorFecha;

public class AccesoPuntuGen {
	/*
	 * Representará el acceso a la tabla PUNTUACION_GENERAL y tendrá tres
	 * métodos estáticos insertarPunt(PuntuGeneral punt) que insertará una
	 * puntuación en la tabla, eliminarPunt(PuntuGeneral punt) que eliminará una
	 * puntuación de la tabla y listaPunt() que listará todos las puntuaciones
	 * de la tabla.
	 */
	public static void insertarPunt(PuntuGeneral punt) {
		try {
			PreparedStatement stat = GestionBD
					.conectar()
					.prepareStatement(
							"INSERT INTO PUNTUACION_GENERAL VALUES( ?, ?, ?, ?, ?, ?);");
			stat.setInt(1, punt.getCod_punt());
			stat.setInt(2, punt.getCod_jug());
			stat.setBoolean(3, punt.isGuardado());
			stat.setInt(4, punt.getPuntu());
			stat.setString(5, punt.getFecha_ulti_nivel());
			stat.setInt(6, punt.getVidas());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarPunt(int cod_punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"DELETE FROM PUNTUACION_GENERAL WHERE COD_PUNT = ?;");
			stat.setInt(1, cod_punt);
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getNumPunt() {
		// ResulSet no tiene contador
		int cont = 0;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL;");
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

	public static ArrayList<PuntuGeneral> getTopTen() {
		ArrayList<PuntuGeneral> tempArray = new ArrayList<PuntuGeneral>();
		ArrayList<PuntuGeneral> todasPunt = AccesoPuntuGen.listaPuntGen();

		if (todasPunt.size() > 10) {
			// Metemos las diez primeras puntuaciones
			for (int i = 0; i < 10; i++) {
				tempArray.add(todasPunt.get(i));
			}
			for (int j = 10; j < todasPunt.size(); j++) {
				if ((AccesoPuntuGen.getMenor(tempArray)) < (todasPunt.get(j)
						.getPuntu())) {
					tempArray.remove(getIndiceMenor(tempArray));
					tempArray.add(todasPunt.get(j));
				}
			}
		} else {
			return todasPunt;
		}
		return tempArray;

	}

	private static int getMenor(ArrayList<PuntuGeneral> tempLista) {
		int numMin = tempLista.get(0).getPuntu();
		for (PuntuGeneral punTemp : tempLista)
			numMin = Math.min(numMin, punTemp.getPuntu());
		return numMin;
	}

	private static int getIndiceMenor(ArrayList<PuntuGeneral> tempLista) {
		int numMin = tempLista.get(0).getPuntu();
		int indiceMenor = 0;
		for (int i = 0; i < tempLista.size(); i++) {
			if (numMin > tempLista.get(i).getPuntu()) {
				numMin = tempLista.get(i).getPuntu();
				indiceMenor = i;
			}
		}
		return indiceMenor;
	}

	public static ArrayList<PuntuGeneral> getPuntDatos(String nom, String fecha) {
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
								"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ? AND FECHA_ULTI_NIVEL = ?;");
				/*
				 * En caso de que no exista el código del Jugador o la fecha sea
				 * incorrecta se devuelve null.
				 */
				if (AccesoJugador.getCodJugador(nom) == -1
						|| !VerificadorFecha.comprobarFecha(fecha))
					return null;
				else {
					stat.setInt(1, AccesoJugador.getCodJugador(nom));
					stat.setString(2, fecha);
					ResultSet rs = stat.executeQuery();
					if (!rs.next())
						return null;
					while (rs.next()) {
						// Si es 'true' es partida guardada.
						if (!rs.getBoolean(3))
							tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
									.getInt(2), rs.getBoolean(3), rs.getInt(4),
									rs.getString(5), rs.getInt(6)));
					}
					return tempPuntu;
				}
				/*
				 * En caso de que fecha sea igual a null.
				 */
			} else if (nom != null && fecha == null) {
				stat = GestionBD.conectar().prepareStatement(
						"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ?;");
				if (AccesoJugador.getCodJugador(nom) == -1)
					return null;
				else {
					stat.setInt(1, AccesoJugador.getCodJugador(nom));
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
								"SELECT * FROM PUNTUACION_GENERAL WHERE FECHA_ULTI_NIVEL = ?;");
				if (!VerificadorFecha.comprobarFecha(fecha))
					return null;
				stat.setString(1, fecha);
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					if (!rs.getBoolean(3))
						tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
								.getInt(2), rs.getBoolean(3), rs.getInt(4), rs
								.getString(5), rs.getInt(6)));
				}
				return tempPuntu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;
	}

	public static ArrayList<PuntuGeneral> listaPuntGen() {
		PuntuGeneral tempGen = null;
		ArrayList<PuntuGeneral> tempPuntu = new ArrayList<PuntuGeneral>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL;");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				tempGen = new PuntuGeneral(rs.getInt(1), rs.getInt(2),
						rs.getBoolean(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6));
				tempPuntu.add(tempGen);
			}

			rs.close();
			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;

	}

	public static ArrayList<PuntuGeneral> getPartidasGuardadas() {
		ArrayList<PuntuGeneral> tempArray = new ArrayList<PuntuGeneral>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL WHERE GUARDADO = ?;");
			stat.setBoolean(1, true);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				tempArray.add(new PuntuGeneral(rs.getInt(1), rs.getInt(2), rs
						.getBoolean(3), rs.getInt(4), rs.getString(5), rs
						.getInt(6)));
			}

			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempArray;
	}
}
