package bomberman.outin;

public class EstructuraCorreo {

	public static String getEstructura(String nomJug){
		String texto = "<html> Estimado Sr/Sra " + nomJug + "Usted" +
		"jug� una partida al videojuego BombermanAddict  la fecha de FECHA	" +
		"en la que obtuvo la siguiente puntuaci�n. Gracias por particpar. </html>";
		
		return texto;
	}
}
