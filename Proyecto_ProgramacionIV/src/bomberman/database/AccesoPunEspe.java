package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bomberman.outin.ManipuladorFecha;
import bomberman.outin.VerificadorFecha;

/**
 * Clase que representa el acceso a la tabla PUNTU_NIV_ESPECI
 * @author David
 * @version 1.0
 */
public class AccesoPunEspe {

	/**
	 * Método que nos servirá para insertar una puntuación
	 * específica
	 * @param punt - PuntuEspe
	 */
	public static void insertarPunt(PuntuEspe punt) {
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"INSERT INTO PUNTU_NIV_ESPECI VALUES( ?, ?, ?, ?, ?);");
			//Código de la puntuación específica
			stat.setInt(1, punt.getCod_puntu_espe());
			//Código de la puntuación
			stat.setInt(2, punt.getCod_puntu());
			//Puntuación específica
			stat.setInt(3, punt.getPuntu_espe());
			//Fecha
			stat.setString(4, punt.getFecha());
			//Nivel
			stat.setInt(5, punt.getNivel());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina una puntuación específica. Recibe como
	 * parámetro el código de la puntuación específica.
	 * @param cod_punt_espe - int
	 */
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

	/**
	 * Devuelve una ArrayList con todas las puntuaciones específicas
	 * que tengan el mismo código de la puntuación general.
	 * @param codPuntuGene - int
	 * @return ArrayList<PuntuEspe>
	 */
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
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;
	}

	/**
	 * Método que nos devolverá hasta que nivel máximo se ha llegado en una
	 * partida guardada o puntuación.
	 * 
	 * @param codPuntuGeneral - int
	 * @return int - Número con el nivel más alto al que se ha llegado.
	 */
	public static int getNivelMasAlto(int codPuntuGeneral) {
		int nivMax = 1;
		ArrayList<PuntuEspe> tempArrayPuntuEspe = AccesoPunEspe
				.listaPuntu(codPuntuGeneral);
		for (PuntuEspe tempPuntuEspe : tempArrayPuntuEspe)
			nivMax = Math.max(nivMax, tempPuntuEspe.getNivel());
		return nivMax;
	}

	/**
	 * Nos devuelve el número de puntuaciones específicas que
	 * hay en la tabla.
	 * @return int
	 */
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
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}

	/**
	 * Nos devuelve un ArrayList con todas las puntuaciones
	 * específicas que cumplan los datos enviados por parámetro.
	 * En caso de que algún parámetro tenga valor null no se
	 * tendrá en cuenta a la hora de hacer la busqueda.
	 * @param nom - String
	 * @param fecha - String
	 * @param nivel - int
	 * @return ArrayList<PuntuEspe>
	 */
	public static ArrayList<PuntuEspe> getPuntDatos(String nom, String fecha,
			int nivel) {
		PreparedStatement stat = null;
		ArrayList<PuntuEspe> tempPuntu = new ArrayList<PuntuEspe>();
		try {
			/*
			 * En caso de que tanto nombre como fecha sean distintos de null.
			 */
			if (nom != null && fecha != null) {
				stat = GestionBD
						.conectar()
						.prepareStatement(
								"SELECT * FROM PUNTU_NIV_ESPECI WHERE COD_PUNT = ? AND FECHA = ? AND COD_NIVEL_PUNTU = ?;");
				ArrayList<Integer> codNom = AccesoJugador.getCodJugador(nom);
				ArrayList<Integer> codPunt = new ArrayList<Integer>();
				/*
				 * En caso de que no exista el código del Jugador o la fecha sea
				 * incorrecta se devuelve null.
				 */
				if (codNom == null || !VerificadorFecha.comprobarFecha(fecha))
					return null;
				else {
					for (Integer codJug : codNom) {
						/*
						 * Recogemos todas las puntuaciones que tengan
						 * como propietario al jugador recibido por
						 * parámetro.
						 */
						codPunt = AccesoPuntuGen.getCodPuntJug(codJug);
						for (Integer codPuntuacion : codPunt) {
							stat.setInt(1, codPuntuacion);
							stat.setString(2,
									ManipuladorFecha.desParsearFecha(fecha));
							stat.setInt(3, nivel);
							ResultSet rs = stat.executeQuery();
							//Si hay linea
							while (rs.next()) {
								tempPuntu.add(new PuntuEspe(rs.getInt(1), rs
										.getInt(2), rs.getInt(3), rs
										.getString(4), rs.getInt(5)));
							}
							rs.close();
							GestionBD.desconectar();
						}
					}
					return tempPuntu;
				}
				/*
				 * En caso de que fecha sea igual a null.
				 */
			} else if (nom != null && fecha == null) {
				stat = GestionBD
						.conectar()
						.prepareStatement(
								"SELECT * FROM PUNTU_NIV_ESPECI WHERE COD_PUNT = ? AND COD_NIVEL_PUNTU = ?;");
				ArrayList<Integer> codNom = AccesoJugador.getCodJugador(nom);
				ArrayList<Integer> codPunt = new ArrayList<Integer>();
				if (codNom == null)
					return null;
				else {
					for (Integer codJug : codNom) {
						codPunt = AccesoPuntuGen.getCodPuntJug(codJug);

						for (Integer codPuntuacion : codPunt) {
							/*
							 * Pedimos puntuaciones con el mismo
							 * jugador y nivel.
							 */
							stat.setInt(1, codPuntuacion);
							stat.setInt(2, nivel);
							ResultSet rs = stat.executeQuery();
							while (rs.next()) {
								tempPuntu.add(new PuntuEspe(rs.getInt(1), rs
										.getInt(2), rs.getInt(3), rs
										.getString(4), rs.getInt(5)));
							}
							rs.close();
							GestionBD.desconectar();
						}
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
								"SELECT * FROM PUNTU_NIV_ESPECI WHERE FECHA = ? AND COD_NIVEL_PUNTU = ?;");
				if (!VerificadorFecha.comprobarFecha(fecha))
					return null;
				stat.setString(1, ManipuladorFecha.desParsearFecha(fecha));
				stat.setInt(2, nivel);
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					tempPuntu.add(new PuntuEspe(rs.getInt(1), rs.getInt(2), rs
							.getInt(3), rs.getString(4), rs.getInt(5)));
				}
				rs.close();
				GestionBD.desconectar();
				return tempPuntu;
			//En caso de que sea null tanto jugador como fecha
			} else {
				stat = GestionBD
						.conectar()
						.prepareStatement(
								"SELECT * FROM PUNTU_NIV_ESPECI WHERE COD_NIVEL_PUNTU = ?;");
				stat.setInt(1, nivel);
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					tempPuntu.add(new PuntuEspe(rs.getInt(1), rs.getInt(2), rs
							.getInt(3), rs.getString(4), rs.getInt(5)));
				}
				rs.close();
				GestionBD.desconectar();
				return tempPuntu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempPuntu;
	}
}
