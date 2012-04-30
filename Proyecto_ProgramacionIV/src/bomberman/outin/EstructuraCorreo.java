package bomberman.outin;

/**
 * Clase para constuir mensaje de email que se
 * enviar�n al usuario con su puntuaci�n.
 * @author David
 * @version 1.0
 */
public class EstructuraCorreo {

	/**
	 * Devuleve un mensaje con el nick y puntuaci�n del
	 * usuario integrados en �l.
	 * @param nomJug - String
	 * @return String
	 */
	public static String getEstructura(String nomJug){
		String texto = "<html> Estimado Sr/Sra " + nomJug + "Usted" +
		"jug� una partida al videojuego BombermanAddict  la fecha de FECHA	" +
		"en la que obtuvo la siguiente puntuaci�n. Gracias por particpar. </html>";
		
		return texto;
	}
}