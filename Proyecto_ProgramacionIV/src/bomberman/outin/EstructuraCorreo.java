package bomberman.outin;

import bomberman.managers.ControlPrincipal;

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
		String texto = "Estimado Sr/Sra " + nomJug + ". Usted" +
		" jug� una partida al videojuego BombermanAddict  en la fecha de " +
		ManipuladorFecha.parsearFecha(ManipuladorFecha.getFecha()) + " a la hora "
		+ ManipuladorFecha.parsearHora(ManipuladorFecha.getHora()) + " en la que obtuvo" +
				" la siguiente puntuaci�n: " + ControlPrincipal.getJugadorUno().getPuntuacion()
				+ ". Gracias por particpar.";
		
		return texto;
	}
}