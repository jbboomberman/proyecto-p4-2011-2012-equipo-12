package bomberman.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bomberman.outin.ManipuladorFecha;
import bomberman.outin.VerificadorFecha;

/**
 * Representa la tabla PUNTUACION_GENERAL y tiene métodos
 * para manejarla y acceder a ella.
 * @author David
 * @version 1.0
 */
public class AccesoPuntuGen {
	
	/**
	 * Inserta una puntuaciín general
	 * @param punt - PuntuGeneral
	 */
	public static void insertarPunt(PuntuGeneral punt) {
		try {
			PreparedStatement stat = GestionBD
					.conectar()
					.prepareStatement(
							"INSERT INTO PUNTUACION_GENERAL VALUES( ?, ?, ?, ?, ?, ?, ?);");
			//Código de la puntuación
			stat.setInt(1, punt.getCod_punt());
			//Código del jugador
			stat.setInt(2, punt.getCod_jug());
			//Es partida guardada
			stat.setBoolean(3, punt.isGuardado());
			//Puntuación general
			stat.setInt(4, punt.getPuntu());
			//Fecha en la que se superó el último nivel
			stat.setString(5, punt.getFecha_ulti_nivel());
			//Número de vidas
			stat.setInt(6, punt.getVidas());
			/*
			 * Nivel en que se guardó la partida
			 * en caso de que fuera guardada.
			 */
			stat.setInt(7, punt.getNiv_guar());
			stat.executeUpdate();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina una puntuación general recibiendo como
	 * parámetro su código.
	 * @param cod_punt - int
	 */
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

	/**
	 * Devuelve el número de puntuaciones generales
	 * que hay guardadas.
	 * @return int
	 */
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
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cont;
	}

	/**
	 * Devuelve un ArrayList con las diez mejores puntuaciones
	 * generales.
	 * @return ArrayList<PuntuGeneral>
	 */
	public static ArrayList<PuntuGeneral> getTopTen() {
		ArrayList<PuntuGeneral> tempArray = new ArrayList<PuntuGeneral>();
		ArrayList<PuntuGeneral> todasPunt = AccesoPuntuGen.listaPuntGen();

		if (todasPunt.size() > 10) {
			// Metemos las diez primeras puntuaciones
			for (int i = 0; i < 10; i++) {
				tempArray.add(todasPunt.get(i));
			}
			//Vamos buscando las diez mejores
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

	/**
	 * Nos devuelve la menor puntuación de las 10 que hay
	 * en la lista.
	 * @param tempLista ArrayList<PuntuGeneral>
	 * @return int
	 */
	private static int getMenor(ArrayList<PuntuGeneral> tempLista) {
		int numMin = tempLista.get(0).getPuntu();
		for (PuntuGeneral punTemp : tempLista)
			numMin = Math.min(numMin, punTemp.getPuntu());
		return numMin;
	}

	/**
	 * Nos encuentra la PuntuGeneral que tiene la menor puntuación
	 * y devuelve su índice en el ArrayList.
	 * @param tempLista - ArrayList<PuntuGeneral>
	 * @return int - índice
	 */
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

	/**
	 * Devuelve un ArrayList con las puntuaciones generales que
	 * cumplen los requisitos especificados por parámetro de entrada.
	 * @param nom - String
	 * @param fecha - String
	 * @return ArrayList<PuntuGeneral>
	 */
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
				if (AccesoJugador.getCodJugador(nom) == null
						|| !VerificadorFecha.comprobarFecha(fecha))
					return null;
				else {
					ArrayList<Integer> tempArray = AccesoJugador
							.getCodJugador(nom);
					for (Integer num : tempArray) {
						/*
						 * Buscamos puntuaciones generales con mismo
						 * jugador y fecha
						 */
						stat.setInt(1, num);
						stat.setString(2, ManipuladorFecha.desParsearFecha(fecha));
						ResultSet rs = stat.executeQuery();
						while (rs.next()) {
							// Si es 'true' es partida guardada y no queremos mostrarla.
							if (rs.getBoolean(3) == false)
								tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
										.getInt(2), rs.getBoolean(3), rs
										.getInt(4), rs.getString(5), rs
										.getInt(6), rs.getInt(7)));
						}
					}
					return tempPuntu;
				}
				/*
				 * En caso de que fecha sea igual a null.
				 */
			} else if (nom != null && fecha == null) {
				stat = GestionBD.conectar().prepareStatement(
						"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ?;");
				if (AccesoJugador.getCodJugador(nom) == null)
					return null;
				else {
					ArrayList<Integer> tempArray = AccesoJugador
							.getCodJugador(nom);
					for (Integer num : tempArray) {
						//Buscamos por código de jugador
						stat.setInt(1, num);
						ResultSet rs = stat.executeQuery();
						while (rs.next()) {
							//Si no es una partida guardada
							if (rs.getBoolean(3) == false)
								tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
										.getInt(2), rs.getBoolean(3), rs
										.getInt(4), rs.getString(5), rs
										.getInt(6), rs.getInt(7)));
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
								"SELECT * FROM PUNTUACION_GENERAL WHERE FECHA_ULTI_NIVEL = ?;");
				if (!VerificadorFecha.comprobarFecha(fecha))
					return null;
				stat.setString(1, ManipuladorFecha.desParsearFecha(fecha));
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					//Si no es partida guardada
					if (!rs.getBoolean(3))
						tempPuntu.add(new PuntuGeneral(rs.getInt(1), rs
								.getInt(2), rs.getBoolean(3), rs.getInt(4), rs
								.getString(5), rs.getInt(6), rs.getInt(7)));
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

	/**
	 * Devuelve una lista con todas las puntuaciones
	 * generales que hay en la tabla.
	 * @return ArrayList<PuntuGeneral>
	 */
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
						rs.getInt(6), rs.getInt(7));
				tempPuntu.add(tempGen);
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
	 * Devuelve un ArrayList<PuntuGeneral> con todas las puntuaciones generales
	 * que representen una partida guardada.
	 * @return ArrayList<PuntuGeneral>
	 */
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
						.getInt(6), rs.getInt(7)));
			}

			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempArray;
	}
	
	/**
	 * Recibimos un código de una puntuación general
	 * y nos devuelve el cñodigo del jugador
	 * que la jugó. -1 en caso de que no exista.
	 * @param codPunt - int
	 * @return codJug - int
	 */
	public static int getCodJugador(int codPunt){
		int codigo = -1;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL WHERE COD_PUNT = ?;");
			stat.setInt(1, codPunt);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				codigo = rs.getInt(2);
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigo;
	}
	
	/**
	 * Recibe el código de un jugador y nos devuelve el
	 * código de todas las puntuaciones generales que
	 * pertenecen a dicho jugador. Devuelve un ArrayList<Integer>.
	 * @param codJug - int
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> getCodPuntJug (int codJug){
		ArrayList<Integer> arrayCodPunt = new ArrayList<Integer>();
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL WHERE COD_JUG = ?;");
			stat.setInt(1, codJug);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				//Vamos añadiendo códigos
				arrayCodPunt.add(rs.getInt(1));
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayCodPunt;
	}
	
	/**
	 * Nos devuelve el número de nivel en que fue
	 * guardada la puntuación general que representa
	 * una partida guardada.
	 * @param codPunt - int
	 * @return int - nivel
	 */
	public static int getCodNivel(int codPunt){
		int codNivel = -1;
		try {
			PreparedStatement stat = GestionBD.conectar().prepareStatement(
					"SELECT * FROM PUNTUACION_GENERAL WHERE COD_PUNT = ?;");
			stat.setInt(1, codPunt);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				codNivel = rs.getInt(7);
			}
			rs.close();
			stat.close();
			GestionBD.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codNivel;
	}
}
