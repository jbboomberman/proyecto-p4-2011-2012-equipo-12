package bomberman.outin;

/**
 * Clase para constuir mensaje de email que se
 * enviarán al usuario con su puntuación.
 * @author David
 * @version 1.0
 */
public class EstructuraCorreo {

	/**
	 * Devuleve un mensaje con el nick y puntuación del
	 * usuario integrados en él.
	 * @param nomJug - String
	 * @return String
	 */
	public static String getEstructura(String nomJug){
		String texto = "<html> Estimado Sr/Sra " + nomJug + "Usted" +
		"jugó una partida al videojuego BombermanAddict  la fecha de FECHA	" +
		"en la que obtuvo la siguiente puntuación. Gracias por particpar. </html>";
		
		return texto;
	}
}