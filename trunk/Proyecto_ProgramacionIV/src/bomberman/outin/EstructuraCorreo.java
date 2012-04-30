package bomberman.outin;

import bomberman.managers.ControlPrincipal;

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
		String texto = "Estimado Sr/Sra " + nomJug + ". Usted" +
		" jugó una partida al videojuego BombermanAddict  en la fecha de " +
		ManipuladorFecha.parsearFecha(ManipuladorFecha.getFecha()) + " a la hora "
		+ ManipuladorFecha.parsearHora(ManipuladorFecha.getHora()) + " en la que obtuvo" +
				" la siguiente puntuación: " + ControlPrincipal.getJugadorUno().getPuntuacion()
				+ ". Gracias por particpar.";
		
		return texto;
	}
}